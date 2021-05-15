package com.barbosa.product.repository;

import com.barbosa.product.entity.Product;

public interface ProductRepository {
    Product findById(Long id);
    void save(Product product);
    void deleteById(Long id);
}
