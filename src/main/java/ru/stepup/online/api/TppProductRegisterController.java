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
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stepup.online.dto.response.DataResponse;
import ru.stepup.online.dto.request.TppProductRegisterDtoRequest;
import ru.stepup.online.dto.response.TppProductRegisterDtoResponse1;
import ru.stepup.online.services.TppProductRegisterService;
import ru.stepup.online.services.errors.ErrorParams;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/corporate-settlement-account")
public class TppProductRegisterController {
    @Autowired
    MessageSource messageSource;
    @Autowired
    private TppProductRegisterService tppProductRegisterService;


    @PostMapping(value = "/create")
    public ResponseEntity<?> createProductRegister(@Valid @RequestBody TppProductRegisterDtoRequest tppProductRegisterDto,
                                                   BindingResult bindingResult, Locale locale) throws BindException {
        if (bindingResult.hasErrors()) {
            if(bindingResult instanceof BindException exception){
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        }
        ProblemDetail problemDetail400 = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                this.messageSource.getMessage("errors.400.title", new Object[0], "errors.400.title", locale));
        ProblemDetail problemDetail404 = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                this.messageSource.getMessage("errors.404.title", new Object[0], "errors.404.title", locale));

        ErrorParams errorParams = tppProductRegisterService.checkValidCreateTppProductRegisterDto(tppProductRegisterDto);
        if(errorParams.getHttpStatus()==HttpStatus.BAD_REQUEST){
            problemDetail400.setProperty("errors",errorParams.getParam());
            return ResponseEntity.badRequest().body(problemDetail400);
        }
        if(errorParams.getHttpStatus()==HttpStatus.NOT_FOUND){
            problemDetail404.setProperty("errors",errorParams.getParam());
            return ResponseEntity.status(errorParams.getHttpStatus()).body(problemDetail404);
        }

        TppProductRegisterDtoResponse1 tppProductRegister = tppProductRegisterService.insertTppProductRegister(tppProductRegisterDto);
        return ResponseEntity.ok(new DataResponse(tppProductRegister));

    }

}
