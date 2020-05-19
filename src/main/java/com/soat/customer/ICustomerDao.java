package com.soat.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerDao extends JpaRepository<Customer, Integer> {

    Customer findCustomerByEmailIgnoreCase(String email);

    List<Customer> findCustomerByLastNameIgnoreCase(String lastName);

}
