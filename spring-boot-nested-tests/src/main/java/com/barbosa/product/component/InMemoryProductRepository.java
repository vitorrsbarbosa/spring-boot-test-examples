package com.barbosa.product.component;

import com.barbosa.product.entity.Product;
import com.barbosa.product.repository.ProductRepository;
import com.barbosa.product.exception.ProductNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryProductRepository implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    @Override
    public Product findById(Long id) {
        return Optional.ofNullable(products.get(id)).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void deleteById(Long id) {
        Optional.ofNullable(products.remove(id)).orElseThrow(ProductNotFoundException::new);
    }
}
