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
public class UserServiceSecond implements UserDetailsService {

    private List<User> users = Arrays.asList(
            new User(2, "nick", "$2a$10$vqkG1Lonp75WyLwQi3RAA.MJ8ElQXEHCFa5FmPUPwnXrc1JOEJB7m", Arrays.asList(new Role("READER")))
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
