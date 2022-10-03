package com.exam.portal.repositories;

import com.exam.portal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsernameOrEmail(String user, String password);

    User findUserByUsername(String username);

    Optional<User> findByUsername(String username);

    User findByEmail(String email);

}
