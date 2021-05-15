package com.barbosa.datajpa.payment.repository;

import com.barbosa.datajpa.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    String FIND_ALL_PAYMENTS_AFTER_THIS_DATE = "SELECT p FROM Payment p JOIN p.order o ON o.date > :after";
    String FIND_BY_CREDIT_CARD_NUMBER = "SELECT * FROM payment WHERE credit_card_number = :ccn";
//    Infered query
    Optional<Payment> findByOrderId(Long orderId);
//    JPQL query
    @Query(FIND_ALL_PAYMENTS_AFTER_THIS_DATE)
    List<Payment> findAllAfter(@Param("after") LocalDateTime afterDate);
//    Native query
    @Query(value = FIND_BY_CREDIT_CARD_NUMBER, nativeQuery = true)
    List<Payment> findByCreditCardNumber(@Param("ccn") String creditCardNumber);
}
