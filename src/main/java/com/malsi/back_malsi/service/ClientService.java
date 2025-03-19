package com.malsi.back_malsi.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malsi.back_malsi.dto.ClientDto;
import com.malsi.back_malsi.dto.DemandDto;
import com.malsi.back_malsi.dto.MeetingDto;
import com.malsi.back_malsi.dto.UserDto;
import com.malsi.back_malsi.model.Client;
import com.malsi.back_malsi.model.Demand;
import com.malsi.back_malsi.model.Meeting;
import com.malsi.back_malsi.model.User;
import com.malsi.back_malsi.repository.ClientRepository;
import com.malsi.back_malsi.repository.UserRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    public ClientDto createClient(Client client) {
        User user = userRepository.findById(client.getUser().getId()).orElse(null);
        client.setUser(user);
        return this.modelMapper.map(this.clientRepository.save(client), ClientDto.class);
    }

    public List<ClientDto> getClients() {
        List<Client> clients = this.clientRepository.findAll();
        
        List<ClientDto> clientsDto = new ArrayList<>();

        for (Client client : clients) {
            clientsDto.add(this.modelMapper.map(client, ClientDto.class));
        }

        return clientsDto;
    }

    public ClientDto getClientById(Integer id) {
        Client client = this.clientRepository.findById(id).orElse(null);
        if (client == null) {
            return null;
        }

        return this.modelMapper.map(client, ClientDto.class);
    }

    public UserDto getClientCommercial(int clientId) {
        Client client = this.clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            return null;
        }

        return this.modelMapper.map(client.getUser(), UserDto.class);
    }

    public ClientDto getClientByPhone(String phone) {
        Client client = this.clientRepository.findByPhone(phone).orElse(null);
        if (client == null) {
            return null;
        }

        return this.modelMapper.map(client, ClientDto.class);
    }

    public ClientDto getClientByEmail(String email) {
        Client client = this.clientRepository.findByEmail(email).orElse(null);
        if (client == null) {
            return null;
        }

        return this.modelMapper.map(client, ClientDto.class);
    }

    public List<MeetingDto> getClientMeetings(int clientId) {
        Client client = this.clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            return null;
        }
        List<MeetingDto> meetings = new ArrayList<>();

        for (Meeting meeting : client.getMeetings()) {
            meetings.add(this.modelMapper.map(meeting, MeetingDto.class));
        }        
        return meetings;
    }

    public List<DemandDto> getClientDemands(int clientId) {
        Client client = this.clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            return null;
        }
        List<DemandDto> demands = new ArrayList<>();

        for (Demand demand : client.getDemands()) {
            demands.add(this.modelMapper.map(demand, DemandDto.class));
        }
        return demands;
    }
}
