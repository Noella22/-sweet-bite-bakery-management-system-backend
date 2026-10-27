package com.sweetbite.management.service;

import com.sweetbite.management.domain.BakingSkill;
import com.sweetbite.management.repository.BakingSkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BakingSkillService {

    private final BakingSkillRepository repository;

    public BakingSkillService(BakingSkillRepository repository) {
        this.repository = repository;
    }

    
    public BakingSkill save(BakingSkill skill) {
        return repository.save(skill);
    }


    public List<BakingSkill> findAll() {
        return repository.findAll();
    }

    
    public Optional<BakingSkill> findById(UUID id) {
        return repository.findById(id);
    }

    
    public Optional<BakingSkill> update(UUID id, BakingSkill details) {

        Optional<BakingSkill> result = repository.findById(id);

        if (result.isEmpty()) {
            return Optional.empty();
        }

        BakingSkill skill = result.get();
        skill.setName(details.getName());

        return Optional.of(repository.save(skill));
    }

    
    public boolean delete(UUID id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }
}