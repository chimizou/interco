package com.soat.loan;

import com.soat.book.BookDTO;
import com.soat.customer.CustomerDTO;

import java.time.LocalDate;


public class LoanDTO implements Comparable<LoanDTO> {


    private BookDTO bookDTO = new BookDTO();


    private CustomerDTO customerDTO = new CustomerDTO();

    private LocalDate loanBeginDate;

    private LocalDate loanEndDate;

    public LocalDate getLoanBeginDate() {
        return loanBeginDate;
    }

    public void setLoanBeginDate(LocalDate loanBeginDate) {
        this.loanBeginDate = loanBeginDate;
    }

    public LocalDate getLoanEndDate() {
        return loanEndDate;
    }

    public void setLoanEndDate(LocalDate loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    @Override
    public int compareTo(LoanDTO o) {
        // ordre decroissant
        return o.getLoanBeginDate().compareTo(this.loanBeginDate);
    }

}
