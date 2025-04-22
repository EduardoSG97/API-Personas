package com.mx.personas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mx.personas.dao.IPersonaDao;
import com.mx.personas.dto.Respuesta;
import com.mx.personas.entity.Persona;

@Service
public class PersonaService {
	@Autowired
	IPersonaDao dao;
	
	public ResponseEntity<List<Persona>> getPersonaAll(){
		if(dao.findAll().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(dao.findAll());	
	}
	
	public Respuesta guardar(Persona persona) {
		Respuesta rs = new Respuesta();
		try {
			if(dao.existsById(persona.getCurp())) {
				rs.setMensaje("La Persona no se agrego a la base de datos por que su curp ya existe");
				rs.setSucces(false);
				rs.setObj(persona.getCurp());
				return rs;
			}
			dao.save(persona);
			rs.setMensaje("La Persona ha sido agregada a la base de datos");
			rs.setSucces(true);
			rs.setObj(persona);
			return rs;
		} catch (Exception e) {
			rs.setMensaje("Error intenta mas tarde");
			rs.setSucces(false);
			rs.setObj(persona);
			return rs;
			
		}
	}
	
	public Respuesta editar(Persona persona) {
		Respuesta rs = new Respuesta();
		try {
			if(dao.existsById(persona.getCurp())) {
				dao.save(persona);
				rs.setMensaje("La Persona ha sido Editada");
				rs.setSucces(true);
				rs.setObj(persona);
				return rs;
			}
			rs.setMensaje("La Persona que trata de editar no existe");
			rs.setSucces(false);
			rs.setObj(persona.getCurp());
			return rs;
		}catch(Exception e) {
			rs.setMensaje("Error al Editar Intenta Mas Tarde");
			rs.setSucces(false);
			rs.setObj(persona);
			return rs;		
		}
	}
	
	public Respuesta eliminar(Persona persona) {
		Respuesta rs =  new Respuesta();
		try {
			if(dao.existsById(persona.getCurp())) {
				rs.setObj(dao.findById(persona.getCurp()));
				dao.delete(persona);
				rs.setMensaje("La Persona ha sido Eliminada");
				rs.setSucces(true);
				return rs;
			}
			rs.setMensaje("La Persona que se intenta eliminar no existe");
			rs.setSucces(false);
			rs.setObj(persona.getCurp());
			return rs;
		} catch (Exception e ) {
			rs.setMensaje("Errir al eliminar Intenta de nuevo mas tarde");
			rs.setSucces(false);
			rs.setObj(persona.getCurp());
			return rs;
		}
	}
	
	public ResponseEntity<Persona> getPersona(String curp){
		Persona persona = dao.findById(curp).orElse(null);
		if(persona == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(persona);
	}
}
