package com.kaansenada.accounts.controller;

import com.kaansenada.accounts.dto.CustomerDto;
import com.kaansenada.accounts.dto.ResponseDto;
import com.kaansenada.accounts.enums.AccountEnum;
import com.kaansenada.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountController {
    private IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountEnum.STATUS_201.toString(), AccountEnum.MESSAGE_201.toString()));
    }
}

