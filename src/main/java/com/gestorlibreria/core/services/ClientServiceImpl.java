package com.gestorlibreria.core.services;

import com.gestorlibreria.core.dao.IClientDao;
import com.gestorlibreria.core.entitys.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService{

    @Autowired
    private IClientDao iClientDao;

    @Override
    public List<Client> findAll() {
        return this.iClientDao.findAll();
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        return this.iClientDao.findAll(pageable);
    }

    @Override
    public Client save(Client client) {
        return this.iClientDao.save(client);
    }

    @Override
    public Client findById(String id) {
        return this.iClientDao.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(String id) {
        return this.iClientDao.existsById(id);
    }

    @Override
    public void delete(Client client) {
        this.iClientDao.delete(client);
    }

    @Override
    public void delete(String id) {
        this.iClientDao.deleteById(id);
    }
}
