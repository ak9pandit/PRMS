package com.ashish.PRMS.auth.repository;

import com.ashish.PRMS.auth.entity.User;
import com.ashish.PRMS.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByRole(Role role);
}
