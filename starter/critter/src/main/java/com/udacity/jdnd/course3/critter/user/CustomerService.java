package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;

import java.util.List;

public interface CustomerService {

    public Customer saveCustomer(Customer customer);

    public List<Customer> getAllCustomers();

    public Customer findCustomerById(Long id);

    public Customer findCustomerByPet(Pet pet);
}
