package com.gestorlibreria.core.services;

import com.gestorlibreria.core.dao.ISchoolStoreDao;
import com.gestorlibreria.core.entitys.SchoolStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolStoreServiceImpl implements ISchoolStoreService{

    @Autowired
    private ISchoolStoreDao schoolStoreDao;

    @Override
    public List<SchoolStore> findAll() {
        return this.schoolStoreDao.findAll();
    }

    @Override
    public Page<SchoolStore> findAll(Pageable pageable) {
        return this.schoolStoreDao.findAll(pageable);
    }

    @Override
    public SchoolStore save(SchoolStore schoolStore) {
        return this.schoolStoreDao.save(schoolStore);
    }

    @Override
    public SchoolStore findById(String id) {
        return this.schoolStoreDao.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(String id) {
        return this.schoolStoreDao.existsById(id);
    }

    @Override
    public void delete(SchoolStore schoolStore) {
        this.schoolStoreDao.delete(schoolStore);
    }

    @Override
    public void delete(String id) {
        this.schoolStoreDao.deleteById(id);
    }
}
