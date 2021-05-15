package com.barbosa.datajpa.order.service;

import com.barbosa.datajpa.order.entity.Order;
import com.barbosa.datajpa.order.repository.OrderRepository;
import com.barbosa.datajpa.payment.entity.Payment;
import com.barbosa.datajpa.payment.repository.PaymentRepository;
import com.barbosa.datajpa.order.exception.OrderAlreadyPaid;
import com.barbosa.datajpa.receipt.entity.Receipt;
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
