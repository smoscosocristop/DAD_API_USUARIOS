package com.saavedra.almacen.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saavedra.almacen.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
	List<Rol> findByNombreContaining(String nombre, Pageable page);
	Rol findByNombre(String nombre);

}
