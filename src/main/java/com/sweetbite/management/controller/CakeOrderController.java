package com.sweetbite.management.controller;

import com.sweetbite.management.domain.CakeOrder;
import com.sweetbite.management.service.CakeOrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.format.annotation.DateTimeFormat;
import com.sweetbite.management.domain.OrderStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/cake-orders")
public class CakeOrderController {

    private final CakeOrderService service;

    public CakeOrderController(CakeOrderService service) {
        this.service = service;
    }

    // Save a cake order
   @PostMapping("/save")
    
    public ResponseEntity<String> save(@RequestBody CakeOrder order) {

     String result = service.saveValidatedOrder(order);

    if (result.equals("Cake order date cannot be in the past")) {
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    if (result.equals("Baker is required")) {
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    if (result.equals("Baker already has a cake order on that date")) {
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(result);
}

    // Get all cake orders
    @GetMapping("/all")
    public List<CakeOrder> findAll() {
        return service.findAll();
    }

    // Get one cake order by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {

        Optional<CakeOrder> order = service.findById(id);

        if (order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The cake order with that id does not exist");
        }

        return ResponseEntity.ok(order.get());
    }

    // Update a cake order by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody CakeOrder details) {

        Optional<CakeOrder> order = service.update(id, details);

        if (order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The cake order with that id does not exist");
        }

        return ResponseEntity.ok(order.get());
    }

    
  // Delete a cake order only when it has no payments
@DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable UUID id) {

    // First, we check whether the cake order exists
    if (service.findById(id).isEmpty()) {
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The cake order with that id does not exist");
    }

    
    if (service.hasPayments(id)) {
       
        return ResponseEntity.status(HttpStatus.CONFLICT).body("That cake order has payments, delete them first");
    }

    // delete the cake order
    service.delete(id);

    return ResponseEntity.ok("Cake order deleted successfully");
}

    // Find cake orders by status
    @GetMapping("/by-status")
    public List<CakeOrder> findByStatus(@RequestParam OrderStatus status) {
        return service.findByStatus(status);
}

// Find cake orders between two dates
    @GetMapping("/between")
     public List<CakeOrder> findBetweenDates(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

           return service.findBetweenDates(start, end);
      }

      @PatchMapping("/{id}/status")
      public ResponseEntity<String> changeStatus(  @PathVariable UUID id, @RequestParam OrderStatus status) {

             String result = service.changeStatus(id, status);

    if (result.equals("The cake order with that id does not exist")) {
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    if (result.startsWith("Cannot change status")) {
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

         return ResponseEntity.ok(result);
   }



}