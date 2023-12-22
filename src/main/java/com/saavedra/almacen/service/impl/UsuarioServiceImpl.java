package com.saavedra.almacen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saavedra.almacen.entity.Usuario;
import com.saavedra.almacen.repository.UsuarioRepository;
import com.saavedra.almacen.service.UsuarioService;
import com.saavedra.almacen.validator.UsuarioValidator;
import com.saavedra.almacen.exceptions.ValidateServiceException;
import com.saavedra.almacen.exceptions.GeneralServiceException;
import com.saavedra.almacen.exceptions.NoDataFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
//	private UsuarioRepository empleadoRepository;

//	@Autowired
//	private Encoder encoder;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch (NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findByEmail(String email, Pageable page) {
		try {
			return repository.findByEmailContaining(email,page);
		}  catch (ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(int id) {
		try {
			Usuario registro = repository.findById(id).orElseThrow(()-> new NoDataFoundException("No existe un registro con ese ID"));
			return registro;
		} catch (ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		try {
			UsuarioValidator.save(usuario);
			if (repository.findByEmail(usuario.getEmail())!=null) {
				throw new ValidateServiceException("Ya existe un registro con los mismos datos: " +usuario.getEmail());
			}
			usuario.setActivo(true);
			Usuario registro=repository.save(usuario);
			return registro;
		} catch (ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Usuario update(Usuario usuario) {
		try {
			UsuarioValidator.save(usuario);
			Usuario registro = repository.findById(usuario.getId()).orElseThrow(()-> new NoDataFoundException("No existe un registro con ese ID"));
			Usuario registroD = repository.findByEmail(usuario.getEmail());
			if (registroD!=null&& registroD.getId()!= usuario.getId()) {
				throw new ValidateServiceException("Ya existe un registro con los mismos datos" +usuario.getEmail());
			}
			registro.setEmail(usuario.getEmail());
			registro.setPassword(usuario.getPassword());
			repository.save(registro);
			return registro;
		} catch (ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	public void delete(int id) {
		try {
			Usuario registro = repository.findById(id).orElseThrow(()-> new NoDataFoundException("No existe un registro con ese ID"));
			repository.delete(registro);
		} catch (ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
		
	}

}
