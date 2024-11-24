package com.kaansenada.loans.mapper;


import com.kaansenada.loans.dto.LoanDto;
import com.kaansenada.loans.entity.Loan;

public class LoanMapper {
    public static LoanDto mapToLoansDto(Loan loan, LoanDto loansDto) {
        loansDto.setLoanNumber(loan.getLoanNumber());
        loansDto.setLoanType(loan.getLoanType());
        loansDto.setMobileNumber(loan.getMobileNumber());
        loansDto.setTotalLoan(loan.getTotalLoan());
        loansDto.setAmountPaid(loan.getAmountPaid());
        loansDto.setOutstandingAmount(loan.getOutstandingAmount());
        return loansDto;
    }

    public static Loan mapToLoans(LoanDto loansDto, Loan loan) {
        loan.setLoanNumber(loansDto.getLoanNumber());
        loan.setLoanType(loansDto.getLoanType());
        loan.setMobileNumber(loansDto.getMobileNumber());
        loan.setTotalLoan(loansDto.getTotalLoan());
        loan.setAmountPaid(loansDto.getAmountPaid());
        loan.setOutstandingAmount(loansDto.getOutstandingAmount());
        return loan;
    }
}
