package com.sweetbite.management.repository;

import com.sweetbite.management.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}