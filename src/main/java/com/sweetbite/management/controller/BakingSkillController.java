package com.sweetbite.management.controller;

import com.sweetbite.management.domain.BakingSkill;
import com.sweetbite.management.service.BakingSkillService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/baking-skills")
public class BakingSkillController {

    private final BakingSkillService service;

    public BakingSkillController(BakingSkillService service) {
        this.service = service;
    }

    
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody BakingSkill skill) {
        service.save(skill);
        return ResponseEntity.status(HttpStatus.CREATED) .body("Baking skill saved successfully");
    }

    
    @GetMapping("/all")
    public List<BakingSkill> findAll() {
        return service.findAll();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {

        Optional<BakingSkill> skill = service.findById(id);

        if (skill.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) .body("The baking skill with that id does not exist");
        }

        return ResponseEntity.ok(skill.get());
    }

    
    @PutMapping("/update/{id}")
   
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody BakingSkill details){
    
     
        Optional<BakingSkill> skill = service.update(id, details);

        if (skill.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) .body("The baking skill with that id does not exist");
        }

        return ResponseEntity.ok(skill.get());
    }

    
    @DeleteMapping("/delete/{id}")
      
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        if (service.delete(id)) {
            
            return ResponseEntity.ok("Baking skill deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND) .body("The baking skill with that id does not exist");
    }

   @GetMapping("/unused")
       
       public List<BakingSkill> findUnusedSkills() {
       
           return service.findUnusedSkills();
   }
}
