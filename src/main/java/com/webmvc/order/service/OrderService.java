package com.webmvc.order.service;

import com.webmvc.order.entity.Order;
import com.webmvc.order.exception.OrderAlreadyPaid;
import com.webmvc.order.repository.OrderRepository;
import com.webmvc.payment.entity.Payment;
import com.webmvc.payment.repository.PaymentRepository;
import com.webmvc.receipt.entity.Receipt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public Payment pay(Long orderId, String creditCardNumber) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);

        if (order.isPaid()) {
            throw new OrderAlreadyPaid();
        }

        orderRepository.save(order.markPaid());
        return paymentRepository.save(new Payment(order, creditCardNumber));
    }

    public Receipt getReceipt(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId).orElseThrow(EntityNotFoundException::new);
        return new Receipt(payment.getOrder().getDate(), payment.getCreditCardNumber(), payment.getOrder().getAmount());
    }
}
