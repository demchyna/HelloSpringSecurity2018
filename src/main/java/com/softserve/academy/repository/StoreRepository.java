package com.softserve.academy.repository;

import com.softserve.academy.model.Store;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StoreRepository {
    private List<Store> stores = Arrays.asList(
            new Store(1, "Mike's Store #1", 1),
            new Store(2, "Mike's Store #2", 1),
            new Store(3, "Nick's Store #1", 2),
            new Store(4, "Nick's Store #2", 2)
    );

    private int counter = 0;

    public void create(Store store) {
        if (store != null)
            store.setId(++counter);
        stores.add(store);
    }

    public Store readById(final long id) {
        return stores.stream()
                .filter(s -> s.getId() == id)
                .findAny().orElse(null);
    }

    public List<Store> readByUserId(final long userId) {
        return stores.stream()
                .filter(s -> s.getUserId() == userId)
                .collect(Collectors.toList());
    }

    public void update(Store store) {
        if (store != null) {
            Store oldStore = stores.stream()
                    .filter(s -> s.getId() == store.getId())
                    .findAny().orElse(null);
            if (oldStore != null) {
                int index = stores.indexOf(oldStore);
                stores.set(index, store);
            }
        }
    }

    public void delete(long id) {
        stores.stream()
                .filter(s -> s.getId() == id).findAny()
                .ifPresent(s -> stores.remove(s));
    }

    public List<Store> readAll() {
        return stores;
    }
}
