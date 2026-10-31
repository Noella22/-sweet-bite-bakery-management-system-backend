package com.sweetbite.management.controller;

import com.sweetbite.management.domain.Payment;
import com.sweetbite.management.service.PaymentService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save( @RequestParam UUID cakeOrderId, @RequestBody Payment payment) {

        String result = service.savePayment(payment, cakeOrderId);

        if (result.equals("Cake order not found")) {
           
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        if (result.equals("Payments are only for READY cake orders")) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/customer/{customerCode}")
     public List<Payment> findByCustomerCode(@PathVariable String customerCode) {
         
             return service.findByCustomerCode(customerCode);
    }


    @DeleteMapping("/delete/{id}")
     
    public ResponseEntity<String> delete(@PathVariable UUID id) {

    if (service.delete(id)) {
        
        return ResponseEntity.ok("Payment deleted successfully");
    }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found");
    
    }

}