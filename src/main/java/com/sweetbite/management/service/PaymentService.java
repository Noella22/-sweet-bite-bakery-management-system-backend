package com.sweetbite.management.service;

import com.sweetbite.management.domain.Payment;
import com.sweetbite.management.domain.CakeOrder;
import com.sweetbite.management.domain.OrderStatus;
import com.sweetbite.management.repository.PaymentRepository;
import com.sweetbite.management.repository.CakeOrderRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository repository;
    private final CakeOrderRepository cakeOrderRepository;

    public PaymentService(PaymentRepository repository, CakeOrderRepository cakeOrderRepository) {
        this.repository = repository;
        this.cakeOrderRepository = cakeOrderRepository;
    }

    public String savePayment(Payment payment, UUID cakeOrderId) {

        Optional<CakeOrder> result = cakeOrderRepository.findById(cakeOrderId);

        if (result.isEmpty()) {
            
            return "Cake order not found";
        }

        CakeOrder order = result.get();

        if (order.getStatus() != OrderStatus.READY) {
            
            return "Payments are only for READY cake orders";
        }

        payment.setCakeOrder(order);
        payment.setPaidDate(LocalDate.now());

        repository.save(payment);

        return "Payment saved successfully";
    }

    public List<Payment> findByCustomerCode(String customerCode) {
           
        return repository.findPaymentsByCustomerCode(customerCode);
    }

    public boolean delete(UUID id) {

    if (repository.existsById(id)) {
       
        repository.deleteById(id);
       
           return true;
    }

        return false;
   }
}