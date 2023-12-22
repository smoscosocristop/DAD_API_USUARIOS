package com.saavedra.almacen.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.saavedra.almacen.entity.Rol;

public interface RolService {
	public List<Rol> findAll(Pageable page);

	public List<Rol> findByNombre(String nombre, Pageable page);

	public Rol findById(int id);

	public Rol save(Rol rol);

	public Rol update(Rol rol);

	public void delete(int id);

//    public int deactivate(int id);
//    public int activate(int id);
//	public List<Empleado> selectEmpleados();	

}
