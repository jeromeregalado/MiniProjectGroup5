package com.example.MiniProjectGroup5.controller;

import com.example.MiniProjectGroup5.enums.CommunityType;
import com.example.MiniProjectGroup5.enums.UserType;
import com.example.MiniProjectGroup5.exception.RecordNotFoundException;
import com.example.MiniProjectGroup5.model.User;
import com.example.MiniProjectGroup5.model.User;
import com.example.MiniProjectGroup5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @Autowired
    private UserService userService;
    @GetMapping("/getAll") //(/users/getAll)
    public ResponseEntity<Page<User>> getUsers(Pageable pageable) {
        Page<User> user = userService.findAllUsers(pageable);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Getting user by id
    @GetMapping("/getUser/{userId}")
    public User getUserById(@PathVariable Long userId) throws RecordNotFoundException {
        return userService.findUserById(userId);
    }

    // Getting user by TYPE
    @GetMapping("/types/{userType}")
    public Page<User> getUserByType(@PathVariable UserType userType, Pageable pageable) throws RecordNotFoundException {
        return userService.findByRole(userType, pageable);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) throws RecordNotFoundException {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User Delete Complete",HttpStatus.OK);
    }
}
