package com.sweetbite.management.repository;

import com.sweetbite.management.domain.Oven;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface OvenRepository extends JpaRepository<Oven, UUID> {

}