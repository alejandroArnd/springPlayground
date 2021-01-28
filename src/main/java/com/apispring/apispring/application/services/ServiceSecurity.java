package com.apispring.apispring.application.services;

import com.apispring.apispring.application.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ServiceSecurity implements UserDetailsService {

    private final UserRepository userRepository;

    public ServiceSecurity(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsernameForSecurity(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
}
