package com.appsdeveloperblog.app.ws.ui.controller;

import com.appsdeveloperblog.app.ws.exceptions.UserServiceExceptions;
import com.appsdeveloperblog.app.ws.impl.UserServiceImpl;
import com.appsdeveloperblog.app.ws.model.request.UpdateUserRequestDetailModel;
import com.appsdeveloperblog.app.ws.model.request.UserDetailRequestModel;
import com.appsdeveloperblog.app.ws.userService.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType; // Import MediaType if you want to specify content type explicitly
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID; // Import UUID for generating unique IDs

@RestController
@RequestMapping("/users") // Base path for all endpoints in this controller
public class UserController {


    Map<String, UserDetailRequestModel> users = new HashMap<>();
    private UserDetailRequestModel userDetails;


    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }





    @GetMapping
    public String getUsers(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "50") int limit) {

         return "get users was called with page = " + page + " and limit = " + limit;
    }


    @GetMapping(path = "/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }) // Specify response formats
    public ResponseEntity<UserDetailRequestModel> getUser(@PathVariable String id) { // Changed type to String


        throw new UserServiceExceptions("A user service exception is thrown YES");

        // Retrieve the user from the in-memory map using the String ID
    }


    @PostMapping("/create")  // Specify response formats
    public ResponseEntity<UserDetailRequestModel> createUser(@org.jetbrains.annotations.NotNull @Valid @RequestBody UserDetailRequestModel userDetails) {

        UserDetailRequestModel returnValue =this.userService.createUser(userDetails);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }


    @PutMapping("/update/{id}")
    public UserDetailRequestModel updateUser(
            @PathVariable String id, // Changed type to String
            @Valid @RequestBody UpdateUserRequestDetailModel userDetails) {


        UserDetailRequestModel storeUserDetails = users.get(id);

        storeUserDetails.setFirstName(userDetails.getFirstName());
        storeUserDetails.setLastName(userDetails.getLastName());

        users.put(id, storeUserDetails);

        return storeUserDetails;

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) { // Changed type to String


        UserDetailRequestModel removedUser = users.remove(id);


        if (removedUser != null) {
            // If user was found and removed, return 204 No Content
            return ResponseEntity.noContent().build();
        } else {
            // If user was not found (nothing was removed), return 404 Not Found.
            return ResponseEntity.notFound().build();
        }

    }
}