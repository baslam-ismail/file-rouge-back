package com.malsi.back_malsi.controller;

import com.malsi.back_malsi.dto.ClientDto;
import com.malsi.back_malsi.dto.UserDto;
import com.malsi.back_malsi.model.User;
import com.malsi.back_malsi.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        UserDto createdUser = this.userService.createUser(user);
        return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = this.userService.getUsers();

        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto user = this.userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<UserDto> getUserByPhone(@PathVariable String phone) {
        UserDto user = this.userService.getUserByPhone(phone);

        if (user == null) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        UserDto user = this.userService.getUserByEmail(email);

        if (user == null) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @GetMapping("/clients/{userId}")
    public ResponseEntity<List<ClientDto>> getClientsUser(@PathVariable int userId) {
        List<ClientDto> clients = this.userService.getClientsUser(userId);

        return new ResponseEntity<List<ClientDto>>(clients, HttpStatus.OK);
    }

}
