package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Long id) {
        if(customerRepository.findById(id).isPresent()) {
            return customerRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Customer findCustomerByPet(Pet pet) {
        return customerRepository.getOwnerByPet(pet);
    }
}
