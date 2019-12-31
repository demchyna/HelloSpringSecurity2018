package com.softserve.academy.conficuration;

import com.softserve.academy.BadCredentialsException;
import com.softserve.academy.repository.UserServiceFirst;
import com.softserve.academy.repository.UserServiceSecond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class WebAuthenticationSecondProvider implements AuthenticationProvider {
    private UserServiceSecond userDetailsService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public WebAuthenticationSecondProvider(UserServiceSecond userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("WebAuthenticationSecondProvider => authenticate");

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = null;

        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        } catch (AuthenticationException authenticationException) {
            System.out.println(authenticationException.getMessage());
        }

        if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    userDetails.getAuthorities());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
