package com.saavedra.almacen.converter;

import org.springframework.stereotype.Component;

import com.saavedra.almacen.dto.UsuarioDTO;
import com.saavedra.almacen.entity.Usuario;



@Component
public class UsuarioConverter extends AbstractConverter<Usuario, UsuarioDTO>{

	@Override
	public UsuarioDTO fromEntity(Usuario entity) {
		if (entity==null)return null;
		return UsuarioDTO.builder()
				.id(entity.getId())
				.email(entity.getEmail())
				.password(entity.getPassword())
				.createdAt(entity.getCreatedAt())
				.updatedAt(entity.getUpdatedAt())
				.activo(entity.getActivo())
				.build();
	}
	
	@Override
	public Usuario fromDTO(UsuarioDTO dto) {
		if (dto==null)return null;
		return Usuario.builder()
				.id(dto.getId())
				.email(dto.getEmail())
				.password(dto.getPassword())
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.activo(dto.getActivo())
				.build();
	}

}
