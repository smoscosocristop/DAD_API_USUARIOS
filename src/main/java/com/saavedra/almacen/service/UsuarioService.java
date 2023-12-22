package com.saavedra.almacen.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.saavedra.almacen.entity.Usuario;

public interface UsuarioService {
	public List<Usuario> findAll(Pageable page);

	public List<Usuario> findByEmail(String email, Pageable page);

	public Usuario findById(int id);

	public Usuario save(Usuario usuario);

	public Usuario update(Usuario usuario);

	public void delete(int id);

//    public int deactivate(int id);
//    public int activate(int id);
//	public List<Empleado> selectEmpleados();	

}
