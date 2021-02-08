package com.apispring.apispring.application.services;

import com.apispring.apispring.application.repository.UserRepository;
import com.apispring.apispring.domain.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsernameForSecurity(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    public UserModel loadUserById(int id){
        return this.userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User Not found"));
    }
}
