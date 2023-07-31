package com.example.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserProfile {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String address;
    private String phone;
    @Schema(enumAsRef = true)
    private UserStatus status;
    private String username;
    private long updatedDate;
    private long createdDate;

}
