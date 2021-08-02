package com.gestorlibreria.core.dao;

import com.gestorlibreria.core.entitys.SchoolStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISchoolStoreDao extends JpaRepository<SchoolStore, String> {
}
