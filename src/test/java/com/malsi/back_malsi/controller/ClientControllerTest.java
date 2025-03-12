package com.malsi.back_malsi.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
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

import com.malsi.back_malsi.dto.ClientDto;
import com.malsi.back_malsi.dto.DemandDto;
import com.malsi.back_malsi.dto.MeetingDto;
import com.malsi.back_malsi.dto.UserDto;
import com.malsi.back_malsi.model.Client;
import com.malsi.back_malsi.model.User;
import com.malsi.back_malsi.service.ClientService;
import com.malsi.back_malsi.service.UserService;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ClientController clientController;

    @Test
    public void testCreateClient() {
        Client client = new Client();
        client.setName("Test Client");
        client.setEmail("test@example.com");
        client.setAddress("123 Test St");
        client.setPhone("0612345678");

        ClientDto clientDto = new ClientDto();
        when(clientService.createClient(any(Client.class))).thenReturn(clientDto);

        ResponseEntity<ClientDto> response = clientController.createClient(client);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clientDto, response.getBody());
        verify(userService).createUser(any(User.class));
    }

    @Test
    public void testCreateClientWithInvalidData() {
        Client client = new Client();
        client.setName(null); // Invalid data

        ResponseEntity<ClientDto> response = clientController.createClient(client);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetClients() {
        List<ClientDto> clientList = Arrays.asList(new ClientDto(), new ClientDto());
        when(clientService.getClients()).thenReturn(clientList);

        ResponseEntity<List<ClientDto>> response = clientController.getClients();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientList, response.getBody());
    }

    @Test
    public void testGetClientById() {
        ClientDto clientDto = new ClientDto();
        when(clientService.getClientById(eq(1))).thenReturn(clientDto);

        ResponseEntity<ClientDto> response = clientController.getClientById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientDto, response.getBody());
    }

    @Test
    public void testGetClientByPhone() {
        ClientDto clientDto = new ClientDto();
        when(clientService.getClientByPhone(eq("0612345678"))).thenReturn(clientDto);

        ResponseEntity<ClientDto> response = clientController.getClientByPhone("0612345678");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientDto, response.getBody());
    }

    @Test
    public void testGetClientByEmail() {
        ClientDto clientDto = new ClientDto();
        when(clientService.getClientByEmail(eq("test@example.com"))).thenReturn(clientDto);

        ResponseEntity<ClientDto> response = clientController.getClientByEmail("test@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientDto, response.getBody());
    }

    @Test
    public void testGetClientCommercial() {
        UserDto userDto = new UserDto();
        when(clientService.getClientCommercial(eq(1))).thenReturn(userDto);

        ResponseEntity<UserDto> response = clientController.getClientCommercial(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    public void testGetClientMeetings() {
        List<MeetingDto> meetingList = Arrays.asList(new MeetingDto(), new MeetingDto());
        when(clientService.getClientMeetings(eq(1))).thenReturn(meetingList);

        ResponseEntity<List<MeetingDto>> response = clientController.getClientMeetings(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(meetingList, response.getBody());
    }

    @Test
    public void testGetClientDemands() {
        List<DemandDto> demandList = Arrays.asList(new DemandDto(), new DemandDto());
        when(clientService.getClientDemands(eq(1))).thenReturn(demandList);

        ResponseEntity<List<DemandDto>> response = clientController.getClientDemands(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(demandList, response.getBody());
    }

}