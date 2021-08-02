package com.gestorlibreria.core.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "facturacion")
public class Root implements Serializable {
    @Id
    @Column(name = "id")
    private String _id;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "total_pagar")
    private Float totalAPagar;
    @OneToMany(mappedBy = "root")
    private List<FacturaProduct> products = new ArrayList<FacturaProduct>();
    @OneToOne
    private Client client;
    @OneToOne
    private SchoolStore schoolStore;

    @Override
    public String toString() {
        return "Instancia";
    }
}
