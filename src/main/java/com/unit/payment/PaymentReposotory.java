package com.unit.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentReposotory extends JpaRepository<Payment, Long> {
}
