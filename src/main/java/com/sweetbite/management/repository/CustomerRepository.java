package com.sweetbite.management.repository;

import com.sweetbite.management.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
   
      List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT c FROM Customer c JOIN CakeOrder o ON o.customer = c " + "WHERE o.baker.id = :bakerId")

       List<Customer> findCustomersOfBaker(@Param("bakerId") UUID bakerId);
}