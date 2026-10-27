package com.sweetbite.management.repository;

import com.sweetbite.management.domain.BakingSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BakingSkillRepository extends JpaRepository<BakingSkill, UUID> {

}