package com.soat.loan;

import java.time.LocalDate;
import java.util.List;

public interface ILoanService {

    List<Loan> findAllLoansByEndDateBefore(LocalDate maxEndDate);

    List<Loan> getAllOpenLoansOfThisCustomer(String email, LoanStatus status);

    Loan getOpenedLoan(SimpleLoanDTO simpleLoanDTO);

    boolean checkIfLoanExists(SimpleLoanDTO simpleLoanDTO);

    Loan saveLoan(Loan loan);

    void closeLoan(Loan loan);

}
