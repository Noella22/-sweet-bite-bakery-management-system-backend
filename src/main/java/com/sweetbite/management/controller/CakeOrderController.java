package com.sweetbite.management.controller;

import com.sweetbite.management.domain.CakeOrder;
import com.sweetbite.management.service.CakeOrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

        service.save(order);

        return ResponseEntity.status(HttpStatus.CREATED).body("Cake order saved successfully");
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

    // Delete a cake order by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        if (service.delete(id)) {
            return ResponseEntity.ok("Cake order deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The cake order with that id does not exist");
    }
}