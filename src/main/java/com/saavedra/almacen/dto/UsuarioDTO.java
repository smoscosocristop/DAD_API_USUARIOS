package com.saavedra.almacen.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

	private int id;
	private String email;
	private String password;
	private Date createdAt;
	private Date updatedAt;
	private Boolean activo;
	 
}
