package com.sweetbite.management.repository;

import com.sweetbite.management.domain.Baker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BakerRepository extends JpaRepository<Baker, UUID> {
       @Query("SELECT DISTINCT b FROM Baker b JOIN b.bakingSkills s " + "WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
         List<Baker> findByBakingSkill(@Param("name") String name);

         
}