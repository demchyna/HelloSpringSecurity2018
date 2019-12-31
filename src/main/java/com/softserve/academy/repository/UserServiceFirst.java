package com.softserve.academy.repository;

import com.softserve.academy.model.Role;
import com.softserve.academy.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceFirst implements UserDetailsService {

    private List<User> users = Arrays.asList(
            new User(1, "mike", "$2a$10$6U/5JW/Mj9AB20f8zAm3vOpTxCP/ShrEYYcl7v7OrWA4ZwCLR2H7u", Arrays.asList(new Role("WRITER")))
    );

    public User readById(final int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findAny().orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        return user;
    }
}
