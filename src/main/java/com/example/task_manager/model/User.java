package com.example.task_manager.model;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Size(max = 25)
    @Column(name= "username")
    private String username;
    @NotBlank
    @Size(max=50)
    @Email
    private String email;
    @NotBlank
    @Size(max=120)
    private String password;
    @NotBlank
    @Size(max=50)
    @Column(name = "first_name")
    private String firstName;
    @NotBlank
    @Size(max=50)
    @Column(name = "last_Name")
    private String lastName;
    @NotBlank
    @Size(max=25)
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(targetEntity = Task.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "id")
    private List<Task> task;

    public User(String username, String email, String password,
                String firstName, String lastName, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;

    }
}
