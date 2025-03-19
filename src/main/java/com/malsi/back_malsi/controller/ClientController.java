package com.malsi.back_malsi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malsi.back_malsi.dto.ClientDto;
import com.malsi.back_malsi.dto.DemandDto;
import com.malsi.back_malsi.dto.MeetingDto;
import com.malsi.back_malsi.dto.UserDto;
import com.malsi.back_malsi.model.Client;
import com.malsi.back_malsi.model.User;
import com.malsi.back_malsi.service.ClientService;
import com.malsi.back_malsi.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public ResponseEntity<ClientDto> createClient(@RequestBody Client client) {

        if (client.getName() == null || client.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        User user = new User();
        user.setName(client.getName());
        user.setEmail(client.getEmail());
        user.setAddress(client.getAddress());
        user.setRole("client");
        user.setPhone(client.getPhone());
        user.setPassword("Passer123");

        UserDto userInBase = userService.getUserByEmail(user.getEmail());
        if (userInBase != null) {
            return new ResponseEntity<ClientDto>(HttpStatus.CONFLICT);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.createUser(user);
        ClientDto createdClient = clientService.createClient(client);

        return new ResponseEntity<ClientDto>(createdClient, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ClientDto>> getClients() {
        List<ClientDto> clients = clientService.getClients();

        return new ResponseEntity<List<ClientDto>>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Integer id) {
        ClientDto client = clientService.getClientById(id);
        if (client == null) {
            return new ResponseEntity<ClientDto>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ClientDto>(client, HttpStatus.OK);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<ClientDto> getClientByPhone(@PathVariable String phone) {
        ClientDto client = clientService.getClientByPhone(phone);
        if (client == null) {
            return new ResponseEntity<ClientDto>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ClientDto>(client, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClientDto> getClientByEmail(@PathVariable String email) {
        ClientDto client = clientService.getClientByEmail(email);
        if (client == null) {
            return new ResponseEntity<ClientDto>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<ClientDto>(client, HttpStatus.OK);
    }
    
    @GetMapping("/commercial/{client_id}")
    public ResponseEntity<UserDto> getClientCommercial(@PathVariable Integer client_id) {
        UserDto commercial = clientService.getClientCommercial(client_id);

        return new ResponseEntity<UserDto>(commercial, HttpStatus.OK);
    }

    @GetMapping("/meetings/{client_id}")
    public ResponseEntity<List<MeetingDto>> getClientMeetings(@PathVariable Integer client_id) {
        List<MeetingDto> meetings = clientService.getClientMeetings(client_id);

        return new ResponseEntity<List<MeetingDto>>(meetings, HttpStatus.OK);
    }
    
    @GetMapping("/demands/{client_id}")
    public ResponseEntity<List<DemandDto>> getClientDemands(@PathVariable Integer client_id) {
        List<DemandDto> demands = clientService.getClientDemands(client_id);

        return new ResponseEntity<List<DemandDto>>(demands, HttpStatus.OK);
    }
    
    
    
}
