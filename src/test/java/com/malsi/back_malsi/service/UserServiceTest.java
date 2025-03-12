package com.malsi.back_malsi.service;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.malsi.back_malsi.dto.ClientDto;
import com.malsi.back_malsi.dto.UserDto;
import com.malsi.back_malsi.model.Client;
import com.malsi.back_malsi.model.User;
import com.malsi.back_malsi.repository.UserRepository;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        User user = new User();
        UserDto userDto = new UserDto();

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(userDto);

        UserDto result = userService.createUser(user);

        assertEquals(userDto, result);
        verify(userRepository).save(user);
    }

    @Test
    public void testGetUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        List<UserDto> userDtos = Arrays.asList(new UserDto(), new UserDto());

        when(userRepository.findAll()).thenReturn(users);
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(userDtos.get(0), userDtos.get(1));

        List<UserDto> result = userService.getUsers();

        assertEquals(userDtos, result);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        UserDto userDto = new UserDto();

        when(userRepository.findById(any(Integer.class))).thenReturn(Optional.of(user));
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(userDto);

        UserDto result = userService.getUserById(1);

        assertEquals(userDto, result);
    }

    @Test
    public void testGetUserByPhone() {
        User user = new User();
        UserDto userDto = new UserDto();

        when(userRepository.findByPhone(any(String.class))).thenReturn(Optional.of(user));
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(userDto);

        UserDto result = userService.getUserByPhone("1234567890");

        assertEquals(userDto, result);
    }

    @Test
    public void testGetUserByEmail() {
        User user = new User();
        UserDto userDto = new UserDto();

        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(user));
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(userDto);

        UserDto result = userService.getUserByEmail("test@example.com");

        assertEquals(userDto, result);
    }

    @Test
    public void testGetClientsUser() {
        User user = new User();
        user.setId(1);
        List<Client> clients = Arrays.asList(new Client(), new Client());
        user.setClients(clients);
        List<ClientDto> clientDtos = Arrays.asList(new ClientDto(), new ClientDto());

        when(userRepository.findById(eq(1))).thenReturn(Optional.of(user));
        when(modelMapper.map(any(Client.class), eq(ClientDto.class))).thenReturn(clientDtos.get(0), clientDtos.get(1));

        List<ClientDto> result = userService.getClientsUser(1);

        assertEquals(clientDtos, result);
    }
}