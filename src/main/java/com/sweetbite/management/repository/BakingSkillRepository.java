package com.sweetbite.management.repository;

import com.sweetbite.management.domain.BakingSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface BakingSkillRepository extends JpaRepository<BakingSkill, UUID> {
          @Query("SELECT s FROM BakingSkill s WHERE s.bakers IS EMPTY ORDER BY s.name ASC")
           List<BakingSkill> findUnusedSkills();
}