package com.sweetbite.management.service;

import com.sweetbite.management.domain.CakeOrder;
import com.sweetbite.management.repository.CakeOrderRepository;
import org.springframework.stereotype.Service;
import com.sweetbite.management.domain.OrderStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;

@Service
public class CakeOrderService {
      private final CakeOrderRepository repository;

    public CakeOrderService(CakeOrderRepository repository) {
        this.repository = repository;
}

     // Save a cake order
    public CakeOrder save(CakeOrder order) {
        return repository.save(order);
    }

    // Get all cake orders
    public List<CakeOrder> findAll() {
        return repository.findAll();
    }

    // Find a cake order by id
    public Optional<CakeOrder> findById(UUID id) {
        return repository.findById(id);
    }
// Update a CakeOrder by id
public Optional<CakeOrder> update(UUID id, CakeOrder details) {

    Optional<CakeOrder> result = repository.findById(id);

    if (result.isEmpty()) {
        return Optional.empty();
    }

    CakeOrder order = result.get();

    order.setOrderDate(details.getOrderDate());
    order.setDescription(details.getDescription());
    order.setQuantity(details.getQuantity());
    order.setCustomer(details.getCustomer());
    order.setBaker(details.getBaker());

    return Optional.of(repository.save(order));
}

    // deleting  a CakeOrder using the id
    public boolean delete(UUID id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }

    // Find cake orders by status
      public List<CakeOrder> findByStatus(OrderStatus status) {
         return repository.findByStatusOrderByOrderDateAsc(status);
}

    // Find cake orders between two dates
    public List<CakeOrder> findBetweenDates(LocalDate start, LocalDate end) {
    return repository.findByOrderDateBetweenOrderByOrderDateAsc(start, end);
}

}
