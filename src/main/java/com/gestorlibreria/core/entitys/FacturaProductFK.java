package com.gestorlibreria.core.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class FacturaProductFK implements Serializable {
    @Column(name = "root_id")
    private String rootId;
    @Column(name = "product_id")
    private String productId;
}
