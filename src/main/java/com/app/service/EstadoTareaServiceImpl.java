package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.IEstadoTareaDao;
import com.app.entity.EstadoTarea;

@Service
public class EstadoTareaServiceImpl implements IEstadoTareaService {
	
	@Autowired
	private IEstadoTareaDao estadoTareaDao;

	@Override
	public Optional<EstadoTarea> findById(Long id) {
		return estadoTareaDao.findById(id);
	}

}
