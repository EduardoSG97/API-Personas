package com.mx.personas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.personas.dto.Respuesta;
import com.mx.personas.entity.Persona;
import com.mx.personas.service.PersonaService;

@RestController
@RequestMapping("Personas")
public class PersonaController {
	
	@Autowired
	PersonaService service;
	
	@GetMapping
	public ResponseEntity<List<Persona>>listar(){
		return service.getPersonaAll();
	}
	
	@PostMapping
	public Respuesta guardar(@RequestBody Persona persona) {
		return service.guardar(persona);
	}
	
	@PostMapping("editar")
	public Respuesta editar(@RequestBody Persona persona) {
		return service.editar(persona);
	}
	
	@PostMapping("Eliminar")
	public Respuesta eliminar(@RequestBody Persona persona) {
		return service.eliminar(persona);
	}
	
	@GetMapping("buscar/{curp}")
	public ResponseEntity<Persona> buscar(@PathVariable("curp")String curp){
		return service.getPersona(curp);
	}

}
