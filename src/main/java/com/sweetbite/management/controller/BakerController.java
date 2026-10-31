package com.sweetbite.management.controller;

import com.sweetbite.management.domain.Baker;
import com.sweetbite.management.service.BakerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/bakers")
public class BakerController {

    private final BakerService service;

    public BakerController(BakerService service) {
        this.service = service;
    }

    // Save a baker
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Baker baker) {
        service.save(baker);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Baker saved successfully");
    }

    // Get all bakers
    @GetMapping("/all")
    public List<Baker> findAll() {
        return service.findAll();
    }

    // Get one baker by id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {

        Optional<Baker> baker = service.findById(id);

        if (baker.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The baker with that id does not exist");
        }

        return ResponseEntity.ok(baker.get());
    }

    // Update a baker by id
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Baker details) {

        Optional<Baker> baker = service.update(id, details);

        if (baker.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The baker with that id does not exist");
        }

        return ResponseEntity.ok(baker.get());
    }

    // Delete a baker by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        if (service.delete(id)) {
            return ResponseEntity.ok("Baker deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The baker with that id does not exist");
    }

    // Find bakers by baking skill
    @GetMapping("/by-baking-skill")
    public List<Baker> findByBakingSkill(@RequestParam String name) {
          return service.findByBakingSkill(name);
     }

     @PutMapping("/{bakerId}/baking-skills/{bakingSkillId}")
     public ResponseEntity<String> addBakingSkill( @PathVariable UUID bakerId, @PathVariable UUID bakingSkillId) {

          String result = service.addBakingSkill(bakerId, bakingSkillId);

    if (result.equals("Baker not found") || result.equals("Baking skill not found")) {
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    if (result.equals("Baker already has this baking skill")) {
       
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

         return ResponseEntity.ok(result);
   }

   @PatchMapping("/{bakerId}/oven")
    
    public ResponseEntity<String> changeOven(
        @PathVariable UUID bakerId,
        @RequestParam int ovenNumber) {

         String result = service.changeOven(bakerId, ovenNumber);

    if (result.equals("Baker not found") || result.equals("Oven not found")) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    if (result.equals("Oven is already assigned to a baker")) {
       
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

       return ResponseEntity.ok(result);

   }
}