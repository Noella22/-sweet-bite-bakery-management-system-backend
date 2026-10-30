package com.sweetbite.management.service;

import com.sweetbite.management.domain.Oven;
import com.sweetbite.management.repository.OvenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OvenService {

    private final OvenRepository repository;

    public OvenService(OvenRepository repository) {
        this.repository = repository;
    }

    // save an oven
    public Oven save(Oven oven) {
        return repository.save(oven);
    }

    // read all
    public List<Oven> findAll() {
        return repository.findAll();
    }

    // read one
    public Optional<Oven> findById(UUID id) {
        return repository.findById(id);
    }

    // update
    public Optional<Oven> update(UUID id, Oven details) {

        Optional<Oven> result = repository.findById(id);

        if (result.isEmpty()) {
            return Optional.empty();
        }

        Oven oven = result.get();
        oven.setOvenNumber(details.getOvenNumber());
        oven.setName(details.getName());

        return Optional.of(repository.save(oven));
    }

    // delete
    public boolean delete(UUID id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }

    // Finding ovens that are not assigned to any baker
    public List<Oven> findFreeOvens() {
    
        return repository.findFreeOvens();
    }
}