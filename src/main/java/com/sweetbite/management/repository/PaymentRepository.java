package com.sweetbite.management.repository;

import com.sweetbite.management.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    @Query("SELECT p FROM Payment p WHERE p.cakeOrder.customer.customerCode = :customerCode ORDER BY p.paidDate DESC")
     
    List<Payment> findPaymentsByCustomerCode(@Param("customerCode") String customerCode);
    boolean existsByCakeOrder_Id(UUID cakeOrderId);
}