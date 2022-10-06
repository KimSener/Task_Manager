package com.example.task_manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameTask;
    private String description;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="fk_user_id",referencedColumnName = "id")
//    private User user;
}
