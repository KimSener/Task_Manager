package com.example.task_manager.repository;

import com.example.task_manager.dto.JoinTableUserAndTaskDto;
import com.example.task_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT new com.example.task_manager.dto.JoinTableUserAndTaskDto" +
            "(u.firstName,u.lastName,t.nameTask,t.description) FROM User u JOIN u.task t")
     List<JoinTableUserAndTaskDto> getJoinInnerTable();


    @Query("SELECT new com.example.task_manager.dto.JoinTableUserAndTaskDto" +
            "(u.firstName,u.lastName,t.nameTask,t.description) FROM User u left JOIN u.task t")
    List<JoinTableUserAndTaskDto> getJoinLeftTable();


}
