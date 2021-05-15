package com.barbosa.datajpa.payment;

import com.barbosa.datajpa.order.entity.Order;
import com.barbosa.datajpa.payment.entity.Payment;
import com.barbosa.datajpa.payment.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.PersistenceException;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:13.2-alpine:///payment"
})
public class PaymentRepositoryTest {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void existingPaymentCanBeFound() {
//        Given
        var order = new Order(LocalDateTime.now(), BigDecimal.valueOf(100.0), true);
        var payment = new Payment(order, "4532756279624064");

//        When
        var orderId = testEntityManager.persist(order).getId();
        // Flush to synchronize persistence context changes to database
        testEntityManager.persistAndFlush(payment);
        // Clear the context so that entities are not fetched from the first-level cache
        testEntityManager.clear();

        var savedPayment = paymentRepository.findByOrderId(orderId);

//        Then
        assertThat(savedPayment).isPresent();
        assertThat(savedPayment.get().getOrder().getPaid()).isTrue();
    }

    @Test
    void paymentsAreUniquePerOrder() {
        var order = new Order(LocalDateTime.now(), BigDecimal.valueOf(100.0), true);
        var firstPayment = new Payment(order, "4532756279624064");
        var secondPayment = new Payment(order, "4716327217780406");

        testEntityManager.persist(order);
        testEntityManager.persist(firstPayment);

        assertThrows(PersistenceException.class, () -> testEntityManager.persistAndFlush(secondPayment));
    }

    @Test
    void findPaymentsAfterDate() {
        var order = new Order(LocalDateTime.now(), BigDecimal.valueOf(1000), true);
        var payment = new Payment(order, "123434123");

        var orderId = testEntityManager.persist(order).getId();
        testEntityManager.persistAndFlush(payment);
        testEntityManager.clear();

        var payments = paymentRepository.findAllAfter(LocalDateTime.now().minusSeconds(1));

        assertThat(payments).extracting("order.id").contains(orderId);
    }

    @Test
    @Sql("/multiple-payments.sql")
    void findPaymentByCreditCard() {
        var payments = paymentRepository.findByCreditCardNumber("4532756279624064");

        assertThat(payments).extracting("order.id").containsExactly(1L);
        assertThat(payments).extracting("order.id").containsOnly(1L);
    }
}
