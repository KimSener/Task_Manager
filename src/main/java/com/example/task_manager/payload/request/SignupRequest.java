package com.example.task_manager.payload.request;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
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

    private Set<String> role;
}
