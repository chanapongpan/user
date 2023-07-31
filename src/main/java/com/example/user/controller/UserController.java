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

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    public ResponseEntity<ApiResp<UserProfile>> getUserById(@PathVariable Long id) {
        var userProfile = userService.getUserById(id).orElseThrow(() -> new UserNotFoundException());
        return new ResponseEntity<>(ApiResp.success(RespMapper.mapUser(userProfile)), HttpStatus.OK);
    }

    @PutMapping
    @ApiResponse(responseCode = "404", content = @Content)
    public void updateUser(@Valid @RequestBody CreateUserReq user) {
        var existingUser = userService.getUserById(user.getId());
        if (!existingUser.isPresent()) {
            throw new UserNotFoundException();
        }

        userService.updateUser(ReqMapper.mapCreateUser(user));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody CreateUserReq user) {
        userService.createUser(ReqMapper.mapCreateUser(user));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", content = @Content)
    public void deleteUserProfile(@PathVariable Long id) {
        var userProfile = userService.getUserById(id).orElseThrow(() -> new UserNotFoundException());
        userService.deleteUser(userProfile);

    }
}
