package com.gestorlibreria.core.dao;

import com.gestorlibreria.core.entitys.FacturaProduct;
import com.gestorlibreria.core.entitys.FacturaProductFK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IFacturacionProductDao extends JpaRepository<FacturaProduct, FacturaProductFK> {
    @Query("from FacturaProduct fp where fp.facturaProductFK.productId = ?1 and fp.facturaProductFK.rootId = ?2")
    public FacturaProduct findByRootIdAndProductId(FacturaProductFK facturaProductFK);
}
