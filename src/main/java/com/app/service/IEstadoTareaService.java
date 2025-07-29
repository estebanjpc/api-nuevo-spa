package com.app.service;

import java.util.Optional;

import com.app.entity.EstadoTarea;

public interface IEstadoTareaService {

	public Optional<EstadoTarea> findById(Long id);

}
