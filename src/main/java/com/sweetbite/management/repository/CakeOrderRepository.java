package com.sweetbite.management.repository;

import com.sweetbite.management.domain.CakeOrder;
import com.sweetbite.management.domain.OrderStatus;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.List;
import java.time.LocalDate;

public interface CakeOrderRepository extends JpaRepository<CakeOrder, UUID> {

    List<CakeOrder> findByStatusOrderByOrderDateAsc(OrderStatus status);
    List<CakeOrder> findByOrderDateBetweenOrderByOrderDateAsc(LocalDate start, LocalDate end);

    boolean existsByBakerIdAndOrderDateAndStatusNot( UUID bakerId, LocalDate orderDate, OrderStatus status);
    
    // Counting cake orders by status
    @Query("SELECT o.status, COUNT(o) FROM CakeOrder o GROUP BY o.status")
     
    List<Object[]> countOrdersByStatus();

    @Modifying
    @Transactional
    @Query("UPDATE CakeOrder o SET o.status = :cancelled " +
       "WHERE o.baker.id = :bakerId " +
       "AND o.orderDate = :date " +
       "AND o.status <> :ready " +
       "AND o.status <> :cancelled")
   int cancelOrdersForDay(
        @Param("bakerId") UUID bakerId,
        @Param("date") LocalDate date,
        @Param("ready") OrderStatus ready,
        @Param("cancelled") OrderStatus cancelled);

}