package com.sweetbite.management.service;

import com.sweetbite.management.domain.Oven;
import com.sweetbite.management.repository.OvenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OvenService {

    private final OvenRepository ovenRepository;

    public OvenService(OvenRepository ovenRepository) {
        this.ovenRepository = ovenRepository;
    }

    // create an oven
    public Oven save(Oven oven) {
        return ovenRepository.save(oven);
    }

    // read all part
    public List<Oven> findAll() {
        return ovenRepository.findAll();
    }

    // read one part
    public Optional<Oven> findById(UUID id) {
        return ovenRepository.findById(id);
    }

    // update part
    public Optional<Oven> update(UUID id, Oven details) {
        return ovenRepository.findById(id).map(oven -> {
            oven.setOvenNumber(details.getOvenNumber());
            oven.setName(details.getName());
            return ovenRepository.save(oven);
        });
    }

    // delete part
    public boolean delete(UUID id) {
        if (!ovenRepository.existsById(id)) {
            return false;
        }

        ovenRepository.deleteById(id);
        return true;
    }
}