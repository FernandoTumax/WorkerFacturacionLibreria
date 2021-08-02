package com.gestorlibreria.core.services;

import com.gestorlibreria.core.dao.IProductDao;
import com.gestorlibreria.core.entitys.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductDao iProductDao;

    @Override
    public List<Product> findAll() {
        return this.iProductDao.findAll();
    }

    @Override
    public Product save(Product product) {
        return this.iProductDao.save(product);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return this.iProductDao.findAll(pageable);
    }

    @Override
    public Product findById(String id) {
        return this.iProductDao.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(String id) {
        return this.iProductDao.existsById(id);
    }

    @Override
    public void delete(Product product) {
        this.iProductDao.delete(product);
    }

    @Override
    public void delete(String id) {
        this.iProductDao.deleteById(id);
    }
}
