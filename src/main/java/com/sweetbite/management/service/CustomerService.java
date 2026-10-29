package com.sweetbite.management.service;

import com.sweetbite.management.domain.Customer;
import com.sweetbite.management.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    // save a Customer
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    // get all Customers
    public List<Customer> findAll() {
        return repository.findAll();
    }

    // get one Customer by ID
    public Optional<Customer> findById(UUID id) {
        return repository.findById(id);
    }

    // Update a Customer
    public Optional<Customer> update(UUID id, Customer details) {

        Optional<Customer> result = repository.findById(id);

        if (result.isEmpty()) {
            return Optional.empty();
        }


       Customer customer = result.get();

       customer.setFirstName(details.getFirstName());
       customer.setLastName(details.getLastName());
       customer.setCustomerCode(details.getCustomerCode());
       customer.setPhone(details.getPhone());
       customer.setEmail(details.getEmail());

       return Optional.of(repository.save(customer));
    }

    // deleting  a Customer using ID
    public boolean delete(UUID id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }

    // Find customers by last name
     public List<Customer> findByLastName(String lastName) {
         return repository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
}
}