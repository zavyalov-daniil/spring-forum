package com.zavyalov.daniil.springforum.repository;

import com.zavyalov.daniil.springforum.entity.UserTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPostgresRepository extends JpaRepository<UserTableEntity, Integer> {

    @Query("SELECT u FROM UserTableEntity u WHERE u.username = ?1")
    Optional<UserTableEntity> findByUsername(String username);
}
