package com.sweetbite.management.repository;

import com.sweetbite.management.domain.CakeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CakeOrderRepository extends JpaRepository<CakeOrder, UUID> {

}