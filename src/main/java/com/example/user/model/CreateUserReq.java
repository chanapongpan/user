package com.example.user.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateUserReq {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String email;

    private Integer age;

    private String address;

    private String phone;

}
