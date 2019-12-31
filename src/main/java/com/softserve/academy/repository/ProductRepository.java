package com.softserve.academy.repository;

import com.softserve.academy.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> products = new ArrayList<>();
    private int counter = 0;

    public void create(Product product) {
        if (product != null)
            product.setId(++counter);
            products.add(product);
    }

    public Product readById(final int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findAny().orElse(null);
    }

    public void update(Product product) {
        if (product != null) {
            Product oldProduct = products.stream()
                    .filter(p -> p.getId() == product.getId())
                    .findAny().orElse(null);
            if (oldProduct != null) {
                int index = products.indexOf(oldProduct);
                products.set(index, product);
            }
        }
    }

    public void delete(int id) {
        products.stream()
                .filter(p -> p.getId() == id).findAny()
                .ifPresent(p -> products.remove(p));
    }

    public List<Product> readAll() {
        return products;
    }
}
