/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.sga.web;

import com.daniel.sga.domain.Persona;
import com.daniel.sga.service.PersonaService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author adseglocdom
 */
@Named("personaBean")
@RequestScoped
public class PersonaBean {
    
    @Inject
    private PersonaService personaService;
    
    private Persona personaSeleccionada;
    
    List<Persona> personas;
    
    public PersonaBean(){
        
    }
    
    @PostConstruct
    public void inicializar(){
        // iniciamos las variables
        personas = personaService.listarPersonas();
        personaSeleccionada = new Persona();
    }
    
    public void editListener(RowEditEvent event){
        Persona persona = (Persona) event.getObject();
        personaService.modificarPersona(persona);
    }
    
    public void agregarPersona(){
        this.personaService.registrarPersona(personaSeleccionada);
        personas.add(personaSeleccionada);
        personaSeleccionada = null;
    }
    
    public void eliminarPersona(){
        personaService.eliminarPersona(personaSeleccionada);
        personas.remove(personaSeleccionada);
        personaSeleccionada = null;
    }
    
    public void reiniciarPersonaSeleccionada(){
        personaSeleccionada = new Persona();
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
    
    
    
}
