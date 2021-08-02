package com.gestorlibreria.core.services;

import com.gestorlibreria.core.entitys.FacturaProduct;
import com.gestorlibreria.core.entitys.FacturaProductFK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFacturaProductService {
    public List<FacturaProduct> findAll();
    public Page<FacturaProduct> findAll(Pageable pageable);
    public FacturaProduct save(FacturaProduct facturaProduct);
    public FacturaProduct findByRootIdAndProductId(FacturaProductFK facturaProductFK);
    public boolean existsById(FacturaProductFK facturaProductFK);
    public void delete(FacturaProduct facturaProduct);
}
