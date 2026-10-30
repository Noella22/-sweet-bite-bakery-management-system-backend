package com.sweetbite.management.controller;

import com.sweetbite.management.domain.Customer;
import com.sweetbite.management.service.CustomerService;
import com.sweetbite.management.repository.BakerRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

   private final CustomerService service;
   private final BakerRepository bakerRepository;

   public CustomerController(CustomerService service, BakerRepository bakerRepository) {
            this.service = service;
            this.bakerRepository = bakerRepository;
}

   // Save a Customer
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Customer customer) {

    service.save(customer);

    return ResponseEntity.status(HttpStatus.CREATED).body("Customer saved successfully");
}

    // Get all Customers
    @GetMapping("/all")
    public List<Customer> findAll() {
        
        return service.findAll();
    }

    // Get one Customer by id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {

        Optional<Customer> customer = service.findById(id);

        if (customer.isEmpty()) {
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer with that id does not exist");
        }

        return ResponseEntity.ok(customer.get());
    }

    // Update a Customer by id
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Customer details) {

        Optional<Customer> customer = service.update(id, details);

        if (customer.isEmpty()) {
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer with that id does not exist");
        }

        return ResponseEntity.ok(customer.get());
    }

    // Delete a Customer by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        if (service.delete(id)) {
            return ResponseEntity.ok("Customer deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer with that id does not exist");
    }

    // Find customers by last name
      @GetMapping("/by-last-name")
      
      public List<Customer> findByLastName(@RequestParam String lastName) {
         
             return service.findByLastName(lastName);
      }

      @GetMapping("/of-baker/{bakerId}")
       public ResponseEntity<?> findCustomersOfBaker(@PathVariable UUID bakerId) {

          if (!bakerRepository.existsById(bakerId)) {
                   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The baker with that id does not exist");
    }

              return ResponseEntity.ok(service.findCustomersOfBaker(bakerId));
     }
}