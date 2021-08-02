package com.gestorlibreria.core.services;

import com.gestorlibreria.core.entitys.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();
    public Product save(Product product);
    public Page<Product> findAll(Pageable pageable);
    public Product findById(String id);
    public boolean existsById(String id);
    public void delete(Product product);
    public void delete(String id);
}
