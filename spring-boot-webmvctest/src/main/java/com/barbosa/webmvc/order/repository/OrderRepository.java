package com.barbosa.webmvc.order.repository;

import com.barbosa.webmvc.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
