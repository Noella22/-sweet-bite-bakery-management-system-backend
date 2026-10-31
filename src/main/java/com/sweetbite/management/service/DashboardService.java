
package com.sweetbite.management.service;

import com.sweetbite.management.repository.CustomerRepository;
import com.sweetbite.management.repository.BakerRepository;
import com.sweetbite.management.repository.OvenRepository;
import com.sweetbite.management.repository.CakeOrderRepository;
import com.sweetbite.management.repository.PaymentRepository;
import com.sweetbite.management.domain.OrderStatus;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.LinkedHashMap;

@Service
public class DashboardService {

    private final CustomerRepository customerRepository;
    private final BakerRepository bakerRepository;
    private final OvenRepository ovenRepository;
    private final CakeOrderRepository cakeOrderRepository;
    private final PaymentRepository paymentRepository;

    public DashboardService(
            CustomerRepository customerRepository,
            BakerRepository bakerRepository,
            OvenRepository ovenRepository,
            CakeOrderRepository cakeOrderRepository,
            PaymentRepository paymentRepository) {

        this.customerRepository = customerRepository;
        this.bakerRepository = bakerRepository;
        this.ovenRepository = ovenRepository;
        this.cakeOrderRepository = cakeOrderRepository;
        this.paymentRepository = paymentRepository;
    }

    public Map<String, Long> getSummary() {

        Map<String, Long> summary = new LinkedHashMap<>();

        summary.put("customers", customerRepository.count());
        summary.put("bakers", bakerRepository.count());
        summary.put("freeOvens", (long) ovenRepository.findFreeOvens().size());
        summary.put("ordersToday", cakeOrderRepository.countByOrderDate(LocalDate.now()));
        summary.put("placed", cakeOrderRepository.countByStatus(OrderStatus.PLACED));
        summary.put("payments", paymentRepository.count());

        return summary;
    }
}
