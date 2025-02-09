package com.malsi.back_malsi.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malsi.back_malsi.dto.ClientDto;
import com.malsi.back_malsi.model.Client;
import com.malsi.back_malsi.repository.ClientRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ClientDto createClient(Client client) {
        return this.modelMapper.map(this.clientRepository.save(client), ClientDto.class);
    }
}
