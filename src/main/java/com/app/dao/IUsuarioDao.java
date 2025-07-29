package com.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	@Query("select u from Usuario u where username = ?1")
	public Usuario findByUsername(String username);
	
}
