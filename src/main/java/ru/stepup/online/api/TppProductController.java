package ru.stepup.online.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stepup.online.dto.request.TppProductDtoRequest;
import ru.stepup.online.dto.request.TppProductRegisterDtoRequest;
import ru.stepup.online.dto.response.DataResponse;
import ru.stepup.online.dto.response.TppProductDtoResponse;
import ru.stepup.online.services.AgreementService;
import ru.stepup.online.services.TppProductService;
import ru.stepup.online.services.errors.ErrorParams;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/corporate-settlement-instance")
public class TppProductController {
    @Autowired
    MessageSource messageSource;
    @Autowired
    private TppProductService tppProductService;
    @Autowired
    private AgreementService agreementService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody TppProductDtoRequest tppProductDto,
                                           BindingResult bindingResult, Locale locale) throws BindException {
        TppProductDtoResponse tppProduct;
        List<String> listErrorParams;

        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        }
        ProblemDetail problemDetail400 = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                this.messageSource.getMessage("errors.400.title", new Object[0], "errors.400.title", locale));
        ProblemDetail problemDetail404 = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                this.messageSource.getMessage("errors.404.title", new Object[0], "errors.404.title", locale));

        if (tppProductDto.getInstanceId() == null) {
            ErrorParams errorParams = tppProductService.checkDuplicateTppProduct(tppProductDto);
            if (errorParams.getHttpStatus() == HttpStatus.BAD_REQUEST) {
                problemDetail400.setProperty("errors", errorParams.getParam());
                return ResponseEntity.status(errorParams.getHttpStatus()).body(problemDetail400);
            }

            listErrorParams =  agreementService.checkDuplicateAgreement(tppProductDto);
            if (!listErrorParams.isEmpty()) {
                problemDetail400.setProperty("errors", listErrorParams);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail400);
            }

            errorParams = tppProductService.checkProductCode(tppProductDto);
            if (errorParams.getHttpStatus() != HttpStatus.OK){
                problemDetail404.setProperty("errors", errorParams.getParam());
                return ResponseEntity.status(errorParams.getHttpStatus()).body(problemDetail404);
            }

            tppProduct = tppProductService.insertTppProduct(tppProductDto);
            return ResponseEntity.ok(new DataResponse(tppProduct));
        }
        listErrorParams =  agreementService.checkDuplicateAgreement(tppProductDto);
        if (!listErrorParams.isEmpty()) {
            problemDetail400.setProperty("errors", listErrorParams);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail400);
        }
        tppProduct = tppProductService.insertTppProduct(tppProductDto, tppProductDto.getInstanceId());
        return ResponseEntity.ok(new DataResponse(tppProduct));
    }

}
