package com.unit.order;

import com.unit.payment.Payment;
import com.unit.payment.PaymentException;
import com.unit.payment.PaymentReposotory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentReposotory paymentReposotory;

    public Payment pay(Long orderId, String creditCardNumber) {
        var order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        if (order.isPaid()) {
            throw new PaymentException();
        }

        orderRepository.save(order.markPaid());
        return paymentReposotory.save(new Payment(order, creditCardNumber));
    }
}
