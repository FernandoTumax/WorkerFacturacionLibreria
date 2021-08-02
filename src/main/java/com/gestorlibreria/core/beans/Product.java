package com.gestorlibreria.core.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private String _id;
    private String name;
    private String description;
    private Float price;
    private int stock;
    private String img;
    private int cantidad;
    private int __v;
}
