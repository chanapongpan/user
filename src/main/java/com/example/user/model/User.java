package com.example.user.model;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_profile")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String email;

    @Column
    private Integer age;

    @Column
    private String address;

    @Column
    private String phone;

    @Column(insertable = false)
    private String status;

    @Column(unique = true)
    private String username;

    @Column(name = "created_date", insertable = false, updatable = false)
    @Schema(hidden = true)
    private Date createdDate;

    @Column(name = "updated_date", insertable = false)
    @Schema(hidden = true)
    private Date updatedDate;

}
