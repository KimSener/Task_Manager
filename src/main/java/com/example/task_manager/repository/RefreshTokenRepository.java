package com.example.task_manager.repository;

import com.example.task_manager.model.RefreshToken;
import com.example.task_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String Token);

    @Modifying
    int deleteByUser(User user);
}