package com.example.ReactSpringProject.crudBackend.repository;

import com.example.ReactSpringProject.crudBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
