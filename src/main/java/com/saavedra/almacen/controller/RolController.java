package com.saavedra.almacen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.saavedra.almacen.converter.RolConverter;
import com.saavedra.almacen.dto.RolDTO;
import com.saavedra.almacen.entity.Rol;
import com.saavedra.almacen.service.RolService;

@RestController
@RequestMapping("/roles")
public class RolController {

	@Autowired
	private RolService service;
	
	@Autowired
	private RolConverter converter;

	@GetMapping()
	public ResponseEntity<List<RolDTO>> findAll(
			@RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
			) {
		
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Rol> roles;

		if (nombre == null) {
			roles = service.findAll(page);

		} else {
			roles = service.findByNombre(nombre, page);

		}
		if (roles.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		List<RolDTO> rolesDTO = converter.fromEntity(roles);
        return ResponseEntity.ok(rolesDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<RolDTO> findById(@PathVariable("id") int id) {
		Rol rol = service.findById(id);
		if (rol == null) {
			return ResponseEntity.notFound().build();

		}
		RolDTO rolesDTO = converter.fromEntity(rol);
        return ResponseEntity.ok(rolesDTO);
	}

	@PostMapping()
	public ResponseEntity<RolDTO> save(@RequestBody RolDTO rolDTO) {

		Rol registro = service.save(converter.fromDTO(rolDTO));
		RolDTO registroDTO=converter.fromEntity(registro);
		return ResponseEntity.status(HttpStatus.CREATED).body(registroDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<RolDTO> update(@PathVariable("id") int id, 
											@RequestBody RolDTO rolDTO) {

		Rol registro = service.update(converter.fromDTO(rolDTO));
		if (registro == null) {
			return ResponseEntity.notFound().build();
		}
		RolDTO registroDTO = converter.fromEntity(registro);
		return ResponseEntity.ok(registroDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<RolDTO> delete(@PathVariable("id") int id) {
		service.delete(id);
		return ResponseEntity.ok(null);
	}

}
