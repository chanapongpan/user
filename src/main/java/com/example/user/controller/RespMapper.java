package com.example.user.controller;

import org.modelmapper.ModelMapper;

import com.example.user.model.User;
import com.example.user.model.UserProfile;
import com.example.user.model.UserStatus;

public class RespMapper {

    static public UserProfile mapUser(User user) {
        var modelMapper = new ModelMapper();
        var userProfile = modelMapper.map(user, UserProfile.class);
        userProfile.setCreatedDate(user.getCreatedDate().getTime());
        userProfile.setUpdatedDate(user.getUpdatedDate().getTime());

        if ("A".equals(user.getStatus())) {
            userProfile.setStatus(UserStatus.ACTIVE);
        } else if ("I".equals(user.getStatus())) {
            userProfile.setStatus(UserStatus.INACTIVE);
        } else {
            userProfile.setStatus(UserStatus.INACTIVE);
        }

        return userProfile;
    }

}
