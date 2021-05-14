package com.barbosa.unit.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private boolean paid;

    public boolean isPaid() {
        return paid;
    }

    public Order markPaid() {
        paid = true;
        return this;
    }
}
