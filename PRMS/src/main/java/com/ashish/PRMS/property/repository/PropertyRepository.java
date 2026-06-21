package com.ashish.PRMS.property.repository;

import com.ashish.PRMS.auth.entity.User;
import com.ashish.PRMS.property.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByOwner(User owner);
    Optional<Property> findByIdAndOwner(Long id, User owner);
    boolean existsByOwnerAndName(User owner, String name);
}
