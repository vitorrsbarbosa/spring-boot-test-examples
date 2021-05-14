package com.barbosa.unit.order;

import com.barbosa.unit.order.Order;
import com.barbosa.unit.order.OrderRepository;
import com.barbosa.unit.order.OrderService;
import com.barbosa.unit.payment.PaymentException;
import com.barbosa.unit.payment.PaymentReposotory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    public static final String CREDIT_CARD_NUMBER = "12341234";
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private PaymentReposotory paymentReposotory;
    @InjectMocks
    private OrderService orderService;

    @Test
    void payOrder() {
        var order = new Order(1L, false);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(paymentReposotory.save(any())).then(returnsFirstArg());

        var payment = orderService.pay(1L, CREDIT_CARD_NUMBER);

        assertThat(payment.getOrder().isPaid()).isTrue();
        assertThat(payment.getCreditCardNumber()).isEqualTo(CREDIT_CARD_NUMBER);
    }

    @Test
    void cannotPayAlreadyPaidOrder() {
        var order = new Order(2L, true);
        when(orderRepository.findById(2L)).thenReturn(Optional.of(order));

        assertThrows(PaymentException.class, () -> orderService.pay(order.getId(), CREDIT_CARD_NUMBER));
    }
}