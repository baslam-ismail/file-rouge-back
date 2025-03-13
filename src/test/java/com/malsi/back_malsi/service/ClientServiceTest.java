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
import com.malsi.back_malsi.dto.DemandDto;
import com.malsi.back_malsi.dto.MeetingDto;
import com.malsi.back_malsi.dto.UserDto;
import com.malsi.back_malsi.model.Client;
import com.malsi.back_malsi.model.Demand;
import com.malsi.back_malsi.model.Meeting;
import com.malsi.back_malsi.model.User;
import com.malsi.back_malsi.repository.ClientRepository;
import com.malsi.back_malsi.repository.UserRepository;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void testCreateClient() {
        Client client = new Client();
        User user = new User();
        user.setId(1);
        client.setUser(user);
        ClientDto clientDto = new ClientDto();

        when(userRepository.findById(any(Integer.class))).thenReturn(Optional.of(user));
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(modelMapper.map(any(Client.class), eq(ClientDto.class))).thenReturn(clientDto);

        ClientDto result = clientService.createClient(client);

        assertEquals(clientDto, result);
        verify(clientRepository).save(client);
    }

    @Test
    public void testGetClients() {
        List<Client> clients = Arrays.asList(new Client(), new Client());
        List<ClientDto> clientDtos = Arrays.asList(new ClientDto(), new ClientDto());

        when(clientRepository.findAll()).thenReturn(clients);
        when(modelMapper.map(any(Client.class), eq(ClientDto.class))).thenReturn(clientDtos.get(0), clientDtos.get(1));

        List<ClientDto> result = clientService.getClients();

        assertEquals(clientDtos, result);
    }

    @Test
    public void testGetClientById() {
        Client client = new Client();
        ClientDto clientDto = new ClientDto();

        when(clientRepository.findById(any(Integer.class))).thenReturn(Optional.of(client));
        when(modelMapper.map(any(Client.class), eq(ClientDto.class))).thenReturn(clientDto);

        ClientDto result = clientService.getClientById(1);

        assertEquals(clientDto, result);
    }

    @Test
    public void testGetClientCommercial() {
        Client client = new Client();
        User user = new User();
        client.setUser(user);
        UserDto userDto = new UserDto();

        when(clientRepository.findById(any(Integer.class))).thenReturn(Optional.of(client));
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(userDto);

        UserDto result = clientService.getClientCommercial(1);

        assertEquals(userDto, result);
    }

    @Test
    public void testGetClientByPhone() {
        Client client = new Client();
        ClientDto clientDto = new ClientDto();

        when(clientRepository.findByPhone(any(String.class))).thenReturn(Optional.of(client));
        when(modelMapper.map(any(Client.class), eq(ClientDto.class))).thenReturn(clientDto);

        ClientDto result = clientService.getClientByPhone("1234567890");

        assertEquals(clientDto, result);
    }

    @Test
    public void testGetClientByEmail() {
        Client client = new Client();
        ClientDto clientDto = new ClientDto();

        when(clientRepository.findByEmail(any(String.class))).thenReturn(Optional.of(client));
        when(modelMapper.map(any(Client.class), eq(ClientDto.class))).thenReturn(clientDto);

        ClientDto result = clientService.getClientByEmail("test@example.com");

        assertEquals(clientDto, result);
    }

    @Test
    public void testGetClientMeetings() {
        Client client = new Client();
        List<Meeting> meetings = Arrays.asList(new Meeting(), new Meeting());
        client.setMeetings(meetings);
        List<MeetingDto> meetingDtos = Arrays.asList(new MeetingDto(), new MeetingDto());

        when(clientRepository.findById(any(Integer.class))).thenReturn(Optional.of(client));
        when(modelMapper.map(any(Meeting.class), eq(MeetingDto.class))).thenReturn(meetingDtos.get(0), meetingDtos.get(1));

        List<MeetingDto> result = clientService.getClientMeetings(1);

        assertEquals(meetingDtos, result);
    }

    @Test
    public void testGetClientDemands() {
        Client client = new Client();
        List<Demand> demands = Arrays.asList(new Demand(), new Demand());
        client.setDemands(demands);
        List<DemandDto> demandDtos = Arrays.asList(new DemandDto(), new DemandDto());

        when(clientRepository.findById(any(Integer.class))).thenReturn(Optional.of(client));
        when(modelMapper.map(any(Demand.class), eq(DemandDto.class))).thenReturn(demandDtos.get(0), demandDtos.get(1));

        List<DemandDto> result = clientService.getClientDemands(1);

        assertEquals(demandDtos, result);
    }
}