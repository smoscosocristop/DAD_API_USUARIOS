package com.saavedra.almacen.converter;

import org.springframework.stereotype.Component;

import com.saavedra.almacen.dto.RolDTO;
import com.saavedra.almacen.entity.Rol;



@Component
public class RolConverter extends AbstractConverter<Rol, RolDTO>{

	@Override
	public RolDTO fromEntity(Rol entity) {
		if (entity==null)return null;
		return RolDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.build();
	}
	
	@Override
	public Rol fromDTO(RolDTO dto) {
		if (dto==null)return null;
		return Rol.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.build();
	}
}
