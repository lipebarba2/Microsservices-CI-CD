package com.appsdeveloperblog.app.ws.impl;

import com.appsdeveloperblog.app.ws.model.request.UserDetailRequestModel;
import com.appsdeveloperblog.app.ws.userService.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    UserDetailRequestModel userDetailRequestModel;

    Map<String, UserDetailRequestModel> users = new HashMap<>();
    private UserDetailRequestModel userDetails;
    public UserServiceImpl(){
        userDetailRequestModel = new UserDetailRequestModel();

    }

@Override
public UserDetailRequestModel createUser(UserDetailRequestModel userDetails) {

        UserDetailRequestModel returnValue = new UserDetailRequestModel();
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setPassword(userDetails.getPassword());

        String userId = UUID.randomUUID().toString();
        returnValue.setUserId(userId);

        if (users.containsKey(userId)) users = new HashMap<>();
        users.put(userId, returnValue);

        return returnValue;
    }
}
