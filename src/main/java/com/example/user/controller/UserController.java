package com.example.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.user.exception.UserNotFoundException;
import com.example.user.model.ApiResp;
import com.example.user.model.CreateUserReq;
import com.example.user.model.UserProfile;
import com.example.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResp<List<UserProfile>>> getAllUser() {
        var users = userService.getAllUsers().stream().map(RespMapper::mapUser).collect(Collectors.toList());
        return new ResponseEntity<>(ApiResp.success(users), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResp<UserProfile>> getUserById(@PathVariable Long id) {
        var user = userService.getUserById(id).orElseThrow(() -> new UserNotFoundException());
        return new ResponseEntity<>(ApiResp.success(RespMapper.mapUser(user)), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody CreateUserReq user) {
        userService.createUser(ReqMapper.mapCreateUser(user));
    }
}
