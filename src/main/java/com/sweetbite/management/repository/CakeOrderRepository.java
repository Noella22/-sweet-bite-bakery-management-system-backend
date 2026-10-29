package com.sweetbite.management.repository;

import com.sweetbite.management.domain.CakeOrder;
import com.sweetbite.management.domain.OrderStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;
import java.time.LocalDate;

public interface CakeOrderRepository extends JpaRepository<CakeOrder, UUID> {

    List<CakeOrder> findByStatusOrderByOrderDateAsc(OrderStatus status);
    List<CakeOrder> findByOrderDateBetweenOrderByOrderDateAsc(LocalDate start, LocalDate end);

}