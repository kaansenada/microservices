package com.kaansenada.loans.service;

import com.kaansenada.loans.dto.LoanDto;

public interface ILoanService {
    void createLoan(String mobileNumber);


    LoanDto fetchLoan(String mobileNumber);


    boolean updateLoan(LoanDto loanDto);


    boolean deleteLoan(String mobileNumber);
}
