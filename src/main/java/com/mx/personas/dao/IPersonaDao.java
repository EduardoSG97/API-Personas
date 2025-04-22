package com.mx.personas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.personas.entity.Persona;

public interface IPersonaDao extends JpaRepository<Persona, String> {

}
