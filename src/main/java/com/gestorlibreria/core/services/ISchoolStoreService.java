package com.gestorlibreria.core.services;

import com.gestorlibreria.core.entitys.SchoolStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISchoolStoreService {
    public List<SchoolStore> findAll();
    public Page<SchoolStore> findAll(Pageable pageable);
    public SchoolStore save(SchoolStore schoolStore);
    public SchoolStore findById(String id);
    public boolean existsById(String id);
    public void delete(SchoolStore schoolStore);
    public void delete(String id);
}
