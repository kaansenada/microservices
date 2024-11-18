package com.kaansenada.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AccountDto {

    @NotEmpty(message = "Account number should not be empty")
    @Length(min = 10, max = 10, message = "Account number should be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account type should not be empty")
    private String accountType;

    @NotEmpty(message = "Branch address should not be empty")
    private String branchAddress;
}
