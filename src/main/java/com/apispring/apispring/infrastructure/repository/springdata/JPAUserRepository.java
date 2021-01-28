package com.apispring.apispring.infrastructure.repository.springdata;

import com.apispring.apispring.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPAUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
