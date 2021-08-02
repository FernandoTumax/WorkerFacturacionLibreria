package com.gestorlibreria.core.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @Column(name = "id")
    private String _id;
    @Column(name = "name_product")
    private String name;
    @Column(name = "description_product")
    private String description;
    @Column(name = "price")
    private Float price;
    @Column(name = "stock")
    private int stock;
    @Column(name = "img")
    private String img;
    @Column(name = "cantidad")
    private int cantidad;
}
