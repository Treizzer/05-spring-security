package com.treizer.spring_security_app.persistence.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.treizer.spring_security_app.persistence.entities.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

}
