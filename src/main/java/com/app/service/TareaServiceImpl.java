package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ITareaDao;
import com.app.entity.Tarea;

@Service
public class TareaServiceImpl implements ITareaService {
	
	@Autowired
	ITareaDao tareaDao;

	@Override
	public List<Tarea> findAll() {
		return (List<Tarea>) tareaDao.findAll();
	}

	@Override
	public Tarea save(Tarea tareaReq) {
		return tareaDao.save(tareaReq);
	}

	@Override
	public Optional<Tarea> findById(Long id) {
		return tareaDao.findById(id);
	}

	@Override
	public void delete(Long id) {
		tareaDao.deleteById(id);
	}

	@Override
	public Optional<Tarea> findByTarea(String nombre) {
		return tareaDao.findByTarea(nombre);
	}

}
