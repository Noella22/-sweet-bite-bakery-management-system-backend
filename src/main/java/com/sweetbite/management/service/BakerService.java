package com.sweetbite.management.service;

import com.sweetbite.management.domain.Baker;
import com.sweetbite.management.repository.BakerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BakerService {

    private final BakerRepository repository;

    public BakerService(BakerRepository repository) {
        this.repository = repository;
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

}