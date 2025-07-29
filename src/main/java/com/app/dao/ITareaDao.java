package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.entity.Tarea;

public interface ITareaDao extends CrudRepository<Tarea, Long>{

	@Query("select t from Tarea t where nombre = ?1")
	public Optional<Tarea> findByTarea(String nombre);

}
