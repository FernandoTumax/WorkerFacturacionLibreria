package com.gestorlibreria.core.dao;

import com.gestorlibreria.core.entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductDao extends JpaRepository<Product, String> {
}
