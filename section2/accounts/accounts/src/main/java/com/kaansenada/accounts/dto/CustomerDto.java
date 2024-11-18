package com.kaansenada.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CustomerDto {
    @NotEmpty(message = "Name should not be empty")
    @Size(min=3,max=30, message = "Name should be between 3 and 30 characters")
    private String name;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits") // this validation explains how to validate mobile number using regexp
    private String mobileNumber;

    private AccountDto accountDto;
}
