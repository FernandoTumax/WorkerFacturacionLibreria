package com.gestorlibreria.core.services;

import com.gestorlibreria.core.entitys.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRootService {
    public List<Root> findAll();
    public Page<Root> findAll(Pageable pageable);
    public Root save(Root root);
    public Root findById(String id);
    public boolean existsById(String id);
    public void delete(Root root);
    public void delete(String id);
}
