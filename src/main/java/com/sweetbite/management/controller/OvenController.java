package com.sweetbite.management.controller;

import com.sweetbite.management.domain.Oven;
import com.sweetbite.management.service.OvenService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/ovens")
public class OvenController {

    private final OvenService ovenService;

    public OvenController(OvenService ovenService) {
        this.ovenService = ovenService;
    }

    // create oven
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Oven oven) {
        ovenService.save(oven);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Oven saved successfully");
    }

    // get all ovens
    @GetMapping("/all")
    public ResponseEntity<List<Oven>> findAll() {
        return ResponseEntity.ok(ovenService.findAll());
    }

    // get one oven by id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {

        Optional<Oven> oven = ovenService.findById(id);

        if (oven.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The oven with that id does not exist");
        }

        return ResponseEntity.ok(oven.get());
    }

    // update part
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody Oven details) {

        Optional<Oven> oven = ovenService.update(id, details);

        if (oven.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The oven with that id does not exist");
        }

        return ResponseEntity.ok(oven.get());
    }

    // delete part
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        boolean deleted = ovenService.delete(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The oven with that id does not exist");
        }

        return ResponseEntity.ok("Oven deleted successfully");
    }

    // Get all free ovens
    @GetMapping("/free")
    public List<Oven> findFreeOvens() {
     return ovenService.findFreeOvens();
   }
}