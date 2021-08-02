package com.gestorlibreria.core.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SchoolStore {
    private String _id;
    private String nameSchoolStore;
    private String directionSchoolStore;
    private int NIT;
}
