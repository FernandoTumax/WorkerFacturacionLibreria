package com.gestorlibreria.core.services;

import com.gestorlibreria.core.dao.IRootDao;
import com.gestorlibreria.core.entitys.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RootServiceImpl implements IRootService{

    @Autowired
    private IRootDao iRootDao;

    @Override
    public List<Root> findAll() {
        return this.iRootDao.findAll();
    }

    @Override
    public Page<Root> findAll(Pageable pageable) {
        return this.iRootDao.findAll(pageable);
    }

    @Override
    public Root save(Root root) {
        return this.iRootDao.save(root);
    }

    @Override
    public Root findById(String id) {
        return this.iRootDao.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(String id) {
        return this.iRootDao.existsById(id);
    }

    @Override
    public void delete(Root root) {
        this.iRootDao.delete(root);
    }

    @Override
    public void delete(String id) {
        this.iRootDao.deleteById(id);
    }
}
