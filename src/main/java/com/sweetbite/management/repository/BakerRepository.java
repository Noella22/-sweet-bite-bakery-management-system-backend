package com.sweetbite.management.repository;

import com.sweetbite.management.domain.Baker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BakerRepository extends JpaRepository<Baker, UUID> {

}