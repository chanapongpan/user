package com.example.user.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserReq {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String lastName;

    @NotEmpty
    private String email;

    private Integer age;

    private String address;

    private String phone;

}
