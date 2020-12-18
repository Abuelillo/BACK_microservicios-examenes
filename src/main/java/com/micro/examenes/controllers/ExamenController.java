package com.micro.examenes.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.validation.Valid;

import com.commons.examenes.models.entity.Examen;
import com.micro.commons.controllers.CommonController;
import com.micro.examenes.services.ExamenService;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService>{

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id){
		
		if (result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Examen> o = service.findById(id);
		
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Examen examenDb = o.get();
		examenDb.setNombre(examen.getNombre());
		
		//forma reducida
		examenDb.getPreguntas()
			.stream()
			.filter(pdb -> !examen.getPreguntas().contains(pdb))
			.forEach(examenDb::removePregunta);//pasa por parametro el dato a al metodo removePregunta
		
		/* otra forma de hacerlo
			List<Pregunta> eliminadas = new ArrayList<Pregunta>();
			examenDb.getPreguntas().forEach(pdb -> {
					if (!examen.getPreguntas().contains(pdb)) {
						eliminadas.add(pdb);
					}
			});
			
			eliminadas.forEach(p -> examenDb.removePregunta(p));
		*/
		
		examenDb.setPreguntas(examen.getPreguntas());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombreExamen(term));
	}
	
	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas(){
		return ResponseEntity.ok(service.findAllAsignaturas());
	}
}
