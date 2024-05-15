package ru.stepup.online.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/corporate-settlement-instance")
public class TppProductController {
    @Autowired
    private TppProductService tppProductService;
    @Autowired
    private AgreementService agreementService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody TppProductDtoRequest tppProductDto,
                                                   BindingResult bindingResult) throws BindException {
        TppProductDtoResponse tppProduct;
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        }
        if (tppProductDto.getInstanceId() == null) {

            ErrorParams errorParams = tppProductService.checkDuplicateTppProduct(tppProductDto);
            if (errorParams.getHttpStatus() != HttpStatus.OK)
                return ResponseEntity.status(errorParams.getHttpStatus()).body(errorParams.getParam());

            List<String> listErrorParams =  agreementService.checkDuplicateAgreement(tppProductDto);
            if (!listErrorParams.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listErrorParams);

            errorParams = tppProductService.checkProductCode(tppProductDto);
            if (errorParams.getHttpStatus() != HttpStatus.OK)
                return ResponseEntity.status(errorParams.getHttpStatus()).body(errorParams.getParam());

            tppProduct = tppProductService.insertTppProduct(tppProductDto);
            return ResponseEntity.ok(new DataResponse(tppProduct));
        }
        List<String> listErrorParams =  agreementService.checkDuplicateAgreement(tppProductDto);
        if (!listErrorParams.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listErrorParams);

        tppProduct = tppProductService.insertTppProduct(tppProductDto, tppProductDto.getInstanceId());
        return ResponseEntity.ok(new DataResponse(tppProduct));
    }

}
