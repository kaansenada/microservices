package com.kaansenada.loans.controller;

import com.kaansenada.loans.dto.ErrorResponseDto;
import com.kaansenada.loans.dto.LoanDto;
import com.kaansenada.loans.dto.ResponseDto;
import com.kaansenada.loans.entity.Loan;
import com.kaansenada.loans.enums.LoanEnum;
import com.kaansenada.loans.service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Loans in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE loan details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoanController {
    private ILoanService loanService;

    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new loan inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                  String mobileNumber) {
        loanService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoanEnum.STATUS_201.toString(), LoanEnum.MESSAGE_201.toString()));
    }

    @Operation(
            summary = "Fetch Loan Details REST API",
            description = "REST API to fetch loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/details")
    public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam
                                                     @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                     String mobileNumber) {
        LoanDto loanDto = loanService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loanDto);
    }

    @Operation(
            summary = "Update Loan Details REST API",
            description = "REST API to update loan details based on a loan number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoanDto loanDto) {
        boolean isUpdated = loanService.updateLoan(loanDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoanEnum.STATUS_200.toString(), LoanEnum.MESSAGE_200.toString()));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoanEnum.STATUS_417.toString(), LoanEnum.MESSAGE_417_UPDATE.toString()));
        }
    }

    @Operation(
            summary = "Delete Loan Details REST API",
            description = "REST API to delete Loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam
                                                         @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                         String mobileNumber) {
        boolean isDeleted = loanService.deleteLoan(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoanEnum.STATUS_200.toString(), LoanEnum.MESSAGE_200.toString()));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoanEnum.STATUS_417.toString(), LoanEnum.MESSAGE_417_DELETE.toString()));
        }
    }
}
