package com.gestorlibreria.core.services;

import com.gestorlibreria.core.dao.IFacturacionProductDao;
import com.gestorlibreria.core.entitys.FacturaProduct;
import com.gestorlibreria.core.entitys.FacturaProductFK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaProductServiceImpl implements IFacturaProductService{

    @Autowired
    private IFacturacionProductDao iFacturacionProductDao;

    @Override
    public List<FacturaProduct> findAll() {
        return this.iFacturacionProductDao.findAll();
    }

    @Override
    public Page<FacturaProduct> findAll(Pageable pageable) {
        return this.iFacturacionProductDao.findAll(pageable);
    }

    @Override
    public FacturaProduct save(FacturaProduct facturaProduct) {
        return this.iFacturacionProductDao.save(facturaProduct);
    }

    @Override
    public FacturaProduct findByRootIdAndProductId(FacturaProductFK facturaProductFK) {
        return this.iFacturacionProductDao.findByRootIdAndProductId(facturaProductFK);
    }

    @Override
    public boolean existsById(FacturaProductFK facturaProductFK) {
        return this.iFacturacionProductDao.existsById(facturaProductFK);
    }

    @Override
    public void delete(FacturaProduct facturaProduct) {
        this.iFacturacionProductDao.delete(facturaProduct);
    }
}
