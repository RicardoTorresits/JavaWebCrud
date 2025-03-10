/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.intefaces.IRepository;
import com.mycompany.models.Persona;
import com.mycompany.util.Result;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ricardotorres
 */
public class PersonaService <I> {
    
    private IRepository<Persona,I> PersonaRepository;

    public PersonaService() {
    }
    

    public PersonaService(IRepository<Persona, I> PersonaRepository) {
        this.PersonaRepository = PersonaRepository;
    }
    
    
    
    public Result<Persona> createPersona(Persona persona) {
        try {
            Result<Persona> personaValidada = this.validarObjeto(persona);
            if(personaValidada.isFailure()){
                return personaValidada;
            }
            this.PersonaRepository.save(persona);
            return Result.success(persona, "Persona creada con exito");
        } catch (SQLException ex) {
            Logger.getLogger(PersonaService.class.getName()).log(Level.SEVERE, null, ex);
            return Result.failure("Error creando la persona:" + ex.getMessage());
        }
    }
    
    public Result<Persona> getPersona(I id){
        try {
            var persona = this.PersonaRepository.getById(id);
            if(persona == null){
                return Result.failure("Person no existente");
            }
            return Result.success(persona, "esta es la persona");
        } catch (SQLException ex) {
            Logger.getLogger(PersonaService.class.getName()).log(Level.SEVERE, null, ex);
            return Result.failure("Error creando la persona:" + ex.getMessage());
        }
    }
    
    public Result<List<Persona>> getPersonas(){
        try {
            List<Persona> personnas=this.PersonaRepository.getAll();
            if(personnas.isEmpty()){
                return Result.failure("no hay datos de personas");
            }
            return Result.success(personnas, "Estas son las personas");
        } catch (SQLException ex) {
            Logger.getLogger(PersonaService.class.getName()).log(Level.SEVERE, null, ex);
            return Result.failure("Error creando la persona:" + ex.getMessage());
        }
    }
    
    public Result<Persona> updatePersona(Persona persona){
        try {
            if(persona == null){
                return Result.failure("Person no existente");
            }
            Result<Persona> personaValidada = this.validarObjeto(persona);
            if(personaValidada.isFailure()){
                return personaValidada;
            }
            this.PersonaRepository.update(persona);
            return Result.success(persona, "Persona actualizda con exito");
        } catch (SQLException ex) {
            Logger.getLogger(PersonaService.class.getName()).log(Level.SEVERE, null, ex);
            return Result.failure("Error creando la persona:" + ex.getMessage());
        }
    }
    
    public Result<Void> deletePersona(I id){
        try {
            if(id !=  null) {
                this.PersonaRepository.delete(id);
                return Result.success(null, "Persona Eliminada con exito");
            }
            return Result.failure("id invalido");
        } catch (SQLException ex) {
            Logger.getLogger(PersonaService.class.getName()).log(Level.SEVERE, null, ex);
            return Result.failure("Error creando la persona:" + ex.getMessage());
        }
    }

    public void setPersonaRepository(IRepository<Persona, I> PersonaRepository) {
        this.PersonaRepository = PersonaRepository;
    }
    
    
    private Result<Persona> validarObjeto (Persona persona){
         if(persona == null){
                return Result.failure("la Persona viene nula");
            }
            if(persona.getNombre() == null && persona.getCorreo() == null && persona.getId() == 0){
                return Result.failure("La persona vienen con campos nulos");
            }
         return Result.success(persona, "Persona Valida");
    }   
}
