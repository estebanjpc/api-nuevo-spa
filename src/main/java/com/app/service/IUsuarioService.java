package com.app.service;

import java.util.Optional;

import com.app.entity.Usuario;

public interface IUsuarioService {

	public Optional<Usuario> findById(Long id);

}
