package com.saavedra.almacen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saavedra.almacen.entity.Rol;
import com.saavedra.almacen.repository.RolRepository;
import com.saavedra.almacen.service.RolService;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Rol> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();

		} catch (Exception e) {
			throw null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Rol> findByNombre(String nombre, Pageable page) {
		try {
			return repository.findByNombreContaining(nombre, page);

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Rol findById(int id) {
		try {
			Rol registro = repository.findById(id).orElseThrow();
			return registro;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional
	public Rol save(Rol rol) {
		try {
			Rol registro = repository.save(rol);
			return registro;

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional
	public Rol update(Rol rol) {
		try {

			Rol registro = repository.findById(rol.getId()).orElseThrow();
			registro.setNombre(rol.getNombre());
			repository.save(registro);
			return registro;

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void delete(int id) {
		try {

			Rol registro = repository.findById(id).orElseThrow();
			repository.delete(registro);

		} catch (Exception e) {
			throw e;
		}
	}

}
