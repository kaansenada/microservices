package com.kaansenada.accounts.controller;

import com.kaansenada.accounts.dto.CustomerDto;
import com.kaansenada.accounts.dto.ResponseDto;
import com.kaansenada.accounts.enums.AccountEnum;
import com.kaansenada.accounts.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountController  {
    private IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid@RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountEnum.STATUS_201.toString(), AccountEnum.MESSAGE_201.toString()));
    }

    @GetMapping("/details")
    public ResponseEntity<CustomerDto>fetchAccountDetails( @RequestParam
                                                               @Pattern(regexp = "(^?|[0-9]{10})", message = "Mobile number should be 10 digits")
                                                               String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccountDetails(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid@RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccountDetails(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountEnum.STATUS_200.toString(), AccountEnum.MESSAGE_200.toString()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountEnum.STATUS_500.toString(), AccountEnum.MESSAGE_500.toString()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
                                                         String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountEnum.STATUS_200.toString(), AccountEnum.MESSAGE_200.toString()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountEnum.STATUS_500.toString(), AccountEnum.MESSAGE_500.toString()));
        }
    }


}

