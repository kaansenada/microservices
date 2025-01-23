package com.kaansenada.accounts.controller;

import com.kaansenada.accounts.dto.CustomerDto;
import com.kaansenada.accounts.dto.ResponseDto;
import com.kaansenada.accounts.enums.AccountEnum;
import com.kaansenada.accounts.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class AccountController  {

    @Autowired
    private IAccountService accountService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

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

    @GetMapping("/build-info")
    public ResponseEntity<String> getVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @GetMapping("/java-info")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("MAVEN_HOME"));
    }

}

