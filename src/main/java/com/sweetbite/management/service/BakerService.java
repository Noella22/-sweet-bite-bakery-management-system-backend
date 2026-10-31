package com.sweetbite.management.service;

import com.sweetbite.management.domain.Baker;
import com.sweetbite.management.domain.Oven;
import com.sweetbite.management.domain.BakingSkill;
import com.sweetbite.management.repository.BakerRepository;
import com.sweetbite.management.repository.OvenRepository;
import com.sweetbite.management.repository.BakingSkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BakerService {

    private final BakerRepository repository;
    private final BakingSkillRepository bakingSkillRepository;
    private final OvenRepository ovenRepository;

    public BakerService(BakerRepository repository, BakingSkillRepository bakingSkillRepository, OvenRepository ovenRepository) {
        this.repository = repository;
        this.bakingSkillRepository = bakingSkillRepository;
        this.ovenRepository = ovenRepository;
    }

    // save a baker
    public Baker save(Baker baker) {
        return repository.save(baker);
    }

    // get all bakers
    public List<Baker> findAll() {
        return repository.findAll();
    }

    // get one baker by ID
    public Optional<Baker> findById(UUID id) {
        return repository.findById(id);
    }

    // Update a baker
    public Optional<Baker> update(UUID id, Baker details) {

        Optional<Baker> result = repository.findById(id);

        if (result.isEmpty()) {
            return Optional.empty();
        }

        Baker baker = result.get();

        baker.setFirstName(details.getFirstName());
        baker.setLastName(details.getLastName());
        baker.setPhone(details.getPhone());
        baker.setOven(details.getOven());
        baker.setBakingSkills(details.getBakingSkills());

        return Optional.of(repository.save(baker));
    }

    // deleting  a baker using ID
    public boolean delete(UUID id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }

    // Find bakers by baking skill
    public List<Baker> findByBakingSkill(String name) {
         
        return repository.findByBakingSkill(name);
   }

   public String addBakingSkill(UUID bakerId, UUID bakingSkillId) {

    Optional<Baker> bakerResult = repository.findById(bakerId);

    if (bakerResult.isEmpty()) {
        
        return "Baker not found";
    }

     Optional<BakingSkill> skillResult = bakingSkillRepository.findById(bakingSkillId);

    if (skillResult.isEmpty()) {
        
        return "Baking skill not found";
    }

     Baker baker = bakerResult.get();
     BakingSkill skill = skillResult.get();

    for (BakingSkill existingSkill : baker.getBakingSkills()) {
        
        if (existingSkill.getId().equals(bakingSkillId)) {
            
            return "Baker already has this baking skill";
        }
    }

        baker.getBakingSkills().add(skill);
        repository.save(baker);

        return "Baking skill added to the baker";
    }

    public String changeOven(UUID bakerId, int ovenNumber) {

     Optional<Baker> bakerResult = repository.findById(bakerId);

     if (bakerResult.isEmpty()) {
       
        return "Baker not found";
    }

     Optional<Oven> ovenResult = ovenRepository.findByOvenNumber(ovenNumber);

     if (ovenResult.isEmpty()) {
       
        return "Oven not found";
    }

     Oven oven = ovenResult.get();

     if (repository.existsByOven(oven)) {
        
        return "Oven is already assigned to a baker";
    }

           Baker baker = bakerResult.get();
           baker.setOven(oven);
           repository.save(baker);

         return "Oven changed successfully";

    }

}