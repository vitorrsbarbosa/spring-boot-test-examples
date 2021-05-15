package com.barbosa.datajpa.order.repository;

import com.barbosa.datajpa.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
