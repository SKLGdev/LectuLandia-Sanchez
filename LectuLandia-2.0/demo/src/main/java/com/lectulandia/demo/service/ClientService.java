package com.lectulandia.demo.service;

import com.lectulandia.demo.entity.ClientData;
import com.lectulandia.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientData getClientCreated(ClientData client) {
        return clientRepository.save(client);
    }

    public Optional<ClientData> getClientDataById(Long idClient) {
        return clientRepository.findById(idClient);
    }

    public List<ClientData> getAllClientsData() {
        return clientRepository.findAll();
    }

    public ClientData getClientUpdated(Long id, ClientData client) {
        ClientData updateClient = clientRepository.findById(id).get();

        updateClient.setName(client.getName());
        updateClient.setSurname(client.getSurname());
        updateClient.setEmail(client.getEmail());
        updateClient.setPhone(client.getPhone());
        updateClient.setAddress(client.getAddress());
        updateClient.setCity(client.getCity());

        return clientRepository.save(updateClient);
    }

    public String getClientDelete(Long id) {
        ClientData deleteClient = clientRepository.findById(id).get();
        clientRepository.delete(deleteClient);

        return "DATA DELETED";
    }

}
