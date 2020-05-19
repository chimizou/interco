package com.soat.customer;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Integer customerId);

    boolean checkIfIdexists(Integer id);

    Customer findCustomerByEmail(String email);

    List<Customer> findCustomerByLastName(String lastName);

    Customer findCustomerById(Integer customerId);

    Page<Customer> getPaginatedCustomersList(int begin, int end);

}
