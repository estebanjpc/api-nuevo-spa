package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entity.Tarea;

public interface ITareaService {

	public List<Tarea> findAll();

	public Tarea save(Tarea tareaReq);

	public Optional<Tarea> findById(Long id);

	public void delete(Long id);

	public Optional<Tarea> findByTarea(String nombre);

}
