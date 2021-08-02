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
@Table(name = "school_store")
public class SchoolStore implements Serializable {
    @Id
    @Column(name = "id")
    private String _id;
    @Column(name = "name")
    private String nameSchoolStore;
    @Column(name = "direction")
    private String directionSchoolStore;
    @Column(name = "nit")
    private int NIT;
}
