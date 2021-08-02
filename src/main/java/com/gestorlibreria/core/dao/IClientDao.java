package com.gestorlibreria.core.dao;


import com.gestorlibreria.core.entitys.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientDao extends JpaRepository<Client, String> {
}
