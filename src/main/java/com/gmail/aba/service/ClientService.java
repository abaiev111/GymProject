package com.gmail.aba.service;

import com.gmail.aba.model.Client;
import com.gmail.aba.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List <Client> getAllClients() {
        return clientRepository.findAll();
    }


    public void saveClient(Client client) {
        this.clientRepository.save(client);
    }


    public Client getClientById(long id) {
        Optional<Client> optional = clientRepository.findById(id);
        Client client = null;
        if (optional.isPresent()) {
            client = optional.get();
        } else {
            throw new RuntimeException("Client not found for id :: " + id);
        }
        return client;
    }


    public void deleteClientById(long id) {
        this.clientRepository.deleteById(id);
    }


    public Page<Client> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.clientRepository.findAll(pageable);
    }
}
