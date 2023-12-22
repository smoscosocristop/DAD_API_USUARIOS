package com.saavedra.almacen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.saavedra.almacen.converter.UsuarioConverter;
import com.saavedra.almacen.dto.UsuarioDTO;
import com.saavedra.almacen.entity.Usuario;
import com.saavedra.almacen.service.UsuarioService;
import com.saavedra.almacen.utils.WrapperResponse;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UsuarioConverter converter;

	@GetMapping()
	public ResponseEntity<List<UsuarioDTO>> findAll(
			@RequestParam(value = "email", required = false, defaultValue = "") String email,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
			) {
		
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Usuario> usuarios;

		if (email == null) {
			usuarios = service.findAll(page);

		} else {
			usuarios = service.findByEmail(email, page);

		}

		if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		List<UsuarioDTO> usuariosDTO = converter.fromEntity(usuarios);
		return new WrapperResponse(true,"Succes",usuariosDTO).createResponse(HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<WrapperResponse<UsuarioDTO>> findById(@PathVariable("id") int id) {
		Usuario usuario = service.findById(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();

		}
		UsuarioDTO usuariosDTO = converter.fromEntity(usuario);
		return new WrapperResponse<UsuarioDTO>(true,"Succes",usuariosDTO).createResponse(HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<UsuarioDTO> save(@RequestBody UsuarioDTO usuarioDTO) {

		Usuario registro = service.save(converter.fromDTO(usuarioDTO));
		UsuarioDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"Succes",registroDTO).createResponse(HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable("id") int id, 
											@RequestBody UsuarioDTO usuarioDTO) {

		Usuario registro = service.update(converter.fromDTO(usuarioDTO));
		if (registro == null) {
			return ResponseEntity.notFound().build();
		}
		UsuarioDTO registroDTO = converter.fromEntity(registro);
		return new WrapperResponse(true,"Succes",registroDTO).createResponse(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> delete(@PathVariable("id") int id) {
		service.delete(id);
		return new WrapperResponse(true,"Succes",null).createResponse(HttpStatus.OK);
	}

}
