package com.saavedra.almacen.validator;

import com.saavedra.almacen.entity.Usuario;
import com.saavedra.almacen.exceptions.ValidateServiceException;

public class UsuarioValidator {
	
	public static void save(Usuario usuario){
		if (usuario.getEmail()==null || usuario.getEmail().isEmpty()) {
			throw new ValidateServiceException("El e-mail es requerido ");
		}
		
		if (usuario.getPassword().length()<=8) {
			throw new ValidateServiceException("La constraseña debe contener mas de 8 caracteres");
		}
	}

}

