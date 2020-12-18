package com.micro.examenes.services;

import java.util.List;

import com.commons.examenes.models.entity.Asignatura;
import com.commons.examenes.models.entity.Examen;
import com.micro.commons.services.CommonService;

public interface ExamenService extends CommonService<Examen>{
	
	public List<Examen> findByNombreExamen(String term);
	
	public Iterable<Asignatura> findAllAsignaturas();

}
