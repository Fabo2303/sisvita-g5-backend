package com.grupo5.sisvita.api.controllers;

import com.grupo5.sisvita.api.dto.UserDTO;
import com.grupo5.sisvita.api.entities.User;
import com.grupo5.sisvita.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping()
    public ResponseEntity<Iterable<User>> getAllUsers(){
        Iterable<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/dto")
    public ResponseEntity<Iterable<UserDTO>> getAllUsersDTO(){
        Iterable<UserDTO> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

}
