package com.softserve.academy.model;

import com.softserve.academy.repository.StoreRepository;

import java.io.Serializable;

public class Product implements Serializable {
    private long id;
    private String name;
    private double price;
    private int quantity;
    private Store store;

    public Product() {
    }

    public Product(String name, double price, int quantity, Store store) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.store = store;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(long id) {
        StoreRepository storeRepository = new StoreRepository();
        this.store = storeRepository.readById(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", store=" + store +
                '}';
    }
}
