package com.gestorlibreria.core.services;

import com.gestorlibreria.core.entitys.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClientService {
    public List<Client> findAll();
    public Page<Client> findAll(Pageable pageable);
    public Client save(Client client);
    public Client findById(String id);
    public boolean existsById(String id);
    public void delete(Client client);
    public void delete(String id);
}
