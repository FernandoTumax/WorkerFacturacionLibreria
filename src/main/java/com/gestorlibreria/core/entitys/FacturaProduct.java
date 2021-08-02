package com.gestorlibreria.core.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "facturacion_product")
public class FacturaProduct implements Serializable {
    @EmbeddedId
    private FacturaProductFK facturaProductFK;
    @ManyToOne
    @JoinColumn(name = "root_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Root root;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    @Override
    public String toString() {
        return "instancia";
    }
}
