package ru.stepup.online.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stepup.online.dto.response.DataResponse;
import ru.stepup.online.dto.request.TppProductRegisterDtoRequest;
import ru.stepup.online.dto.response.TppProductRegisterDtoResponse1;
import ru.stepup.online.services.TppProductRegisterService;
import ru.stepup.online.services.errors.ErrorParams;

@RestController
@RequiredArgsConstructor
@RequestMapping("/corporate-settlement-account")
public class TppProductRegisterController {
    @Autowired
    private TppProductRegisterService tppProductRegisterService;


    @PostMapping(value = "/create")
    public ResponseEntity<?> createProductRegister(@Valid @RequestBody TppProductRegisterDtoRequest tppProductRegisterDto,
                                                   BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if(bindingResult instanceof BindException exception){
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        }
        ErrorParams errorParams = tppProductRegisterService.checkValidCreateTppProductRegisterDto(tppProductRegisterDto);
        if(errorParams.getHttpStatus()!=HttpStatus.OK){
            return ResponseEntity.status(errorParams.getHttpStatus()).body(errorParams.getParam());
        }
        else {
            TppProductRegisterDtoResponse1 tppProductRegister = tppProductRegisterService.insertTppProductRegister(tppProductRegisterDto);
            return ResponseEntity.ok(new DataResponse(tppProductRegister));
        }
    }

    @PostMapping(value = "/create_test")
    public ResponseEntity<?> createProductRegister(@Validated @RequestBody TppProductRegisterDtoRequest tppProductRegisterDto) {

        return ResponseEntity.ok("");

    }






}
