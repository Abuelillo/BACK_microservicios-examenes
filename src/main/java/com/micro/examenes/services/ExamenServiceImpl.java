package com.micro.examenes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commons.examenes.models.entity.Asignatura;
import com.commons.examenes.models.entity.Examen;
import com.micro.commons.services.CommonServiceImp;
import com.micro.examenes.models.repository.AsignaturaRepository;
import com.micro.examenes.models.repository.ExamenRepository;

@Service
public class ExamenServiceImpl extends CommonServiceImp<Examen, ExamenRepository> implements ExamenService {

	@Autowired
	private AsignaturaRepository asignaturarepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombreExamen(String term) {		
		return repo.findByNombreExamen(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Asignatura> findAllAsignaturas() {	
		return asignaturarepository.findAll();
	}


}
