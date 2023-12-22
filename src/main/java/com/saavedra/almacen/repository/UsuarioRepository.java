package com.saavedra.almacen.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saavedra.almacen.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	List<Usuario> findByEmailContaining(String email, Pageable page);
	Usuario findByEmail(String email);

}
