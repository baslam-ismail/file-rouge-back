package com.malsi.back_malsi.controller;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.malsi.back_malsi.dto.UserDto;
import com.malsi.back_malsi.dto.ClientDto;
import com.malsi.back_malsi.model.User;
import com.malsi.back_malsi.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserController userController;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("Test User");
        user.setPassword("password123");

        UserDto userDto = new UserDto();
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");
        when(userService.createUser(any(User.class))).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    public void testGetUsers() {
        List<UserDto> userList = Arrays.asList(new UserDto(), new UserDto());
        when(userService.getUsers()).thenReturn(userList);

        ResponseEntity<List<UserDto>> response = userController.getUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    public void testGetUserById() {
        UserDto userDto = new UserDto();
        when(userService.getUserById(eq(1))).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getUserById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    public void testGetUserByPhone() {
        UserDto userDto = new UserDto();
        when(userService.getUserByPhone(eq("1234567890"))).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getUserByPhone("1234567890");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    public void testGetUserByEmail() {
        UserDto userDto = new UserDto();
        when(userService.getUserByEmail(eq("test@example.com"))).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getUserByEmail("test@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    public void testGetClientsUser() {
        List<ClientDto> clientList = Arrays.asList(new ClientDto(), new ClientDto());
        when(userService.getClientsUser(eq(1))).thenReturn(clientList);

        ResponseEntity<List<ClientDto>> response = userController.getClientsUser(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientList, response.getBody());
    }
}