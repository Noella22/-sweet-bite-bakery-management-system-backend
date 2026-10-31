package com.sweetbite.management.repository;

import com.sweetbite.management.domain.Oven;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface OvenRepository extends JpaRepository<Oven, UUID> {
     @Query("SELECT o FROM Oven o WHERE NOT EXISTS " + "(SELECT b FROM Baker b WHERE b.oven = o) " + "ORDER BY o.ovenNumber ASC")
          List<Oven> findFreeOvens();

          Optional<Oven> findByOvenNumber(int ovenNumber);
}