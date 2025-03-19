package com.malsi.back_malsi.service;

import com.malsi.back_malsi.dto.ClientDto;
import com.malsi.back_malsi.dto.UserDto;
import com.malsi.back_malsi.model.Client;
import com.malsi.back_malsi.model.User;
import com.malsi.back_malsi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UserDto createUser(User user) {
        return this.modelMapper.map(this.userRepository.save(user), UserDto.class);
    }

    public List<UserDto> getUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> usersDto = new ArrayList<>();

        for (User user : users) {
            usersDto.add(this.modelMapper.map(user, UserDto.class));
        }

        return usersDto;
    }

    public UserDto getUserById(Integer id) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        return this.modelMapper.map(user, UserDto.class);
    }

    public UserDto getUserByPhone (String phone) {
        User user = this.userRepository.findByPhone(phone).orElse(null);
        if (user == null) {
            return null;
        }

        return this.modelMapper.map(user, UserDto.class);
    }

    public UserDto getUserByEmail (String email) {
        User user = this.userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return null;
        }

        return this.modelMapper.map(user, UserDto.class);
    }

    public List<ClientDto> getClientsUser(int userId) {
        User user = this.userRepository.findById(userId).orElse(null);
        List<ClientDto> clients = new ArrayList<>();
        if (user == null) {
            return null;
        }

        for (Client client : user.getClients()) {
            clients.add(this.modelMapper.map(client, ClientDto.class));
        }

        return clients;
    }
}
