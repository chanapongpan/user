package com.example.user.controller;

import org.modelmapper.ModelMapper;

import com.example.user.model.CreateUserReq;
import com.example.user.model.User;
import com.example.user.utils.DateUtil;

public class ReqMapper {
    static public User mapCreateUser(CreateUserReq userReq) {
        var modelMapper = new ModelMapper();
        var user = modelMapper.map(userReq, User.class);

        var currentDate = DateUtil.getCurrentDate();
        user.setCreatedDate(new java.sql.Date(currentDate));
        user.setUpdatedDate(new java.sql.Date(currentDate));
        return user;
    }
}
