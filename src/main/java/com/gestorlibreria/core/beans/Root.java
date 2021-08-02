package com.gestorlibreria.core.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Root {
    private String _id;
    private String fecha;
    private float totalAPagar;
    private List<Product> products = new ArrayList<Product>();
    private Client client;
    private SchoolStore schoolStore;
}
