/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.sga.cliente.criteria;

import com.daniel.sga.domain.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
import org.apache.logging.log4j.*;

/**
 *
 * @author adseglocdom
 */
public class PruebaApiCriteria {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Persona> criteriaQuery = null;
        Root<Persona> fromPersona = null;
        TypedQuery<Persona> query = null;
        Persona persona = null;
        List<Persona> personas = null;
        
        //Query Utilizando api de criteria
        //1. Consulta de todas las personas
        log.debug("\n1. Consulta de todas las personas");
        // Paso 1. El objeto entitymanager crea instanicia criteria builder
        cb = em.getCriteriaBuilder();
        // Paso 2. Se crea un objeto CriteriaQuery
        criteriaQuery = cb.createQuery(Persona.class);
        // Paso 3. Se el objetoo raiz
        fromPersona = criteriaQuery.from(Persona.class);
        // Paso 4. Seleccionamos lo necesario del from
        criteriaQuery.select(fromPersona);
        // Paso 5. Creamos el query typesafe
        query = em.createQuery(criteriaQuery);
        // Paso 6. Ejecutamos la consulta
        personas = query.getResultList();
        
        mostrarPersonas(personas);
        
        //2-a Consulta de la persona por id = 1
        log.debug("\n2-a. Consulta de la persona id = 1");
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Persona.class);
        fromPersona = criteriaQuery.from(Persona.class);
        criteriaQuery.select(fromPersona).where(cb.equal(fromPersona.get("idPersona"), 1));
        persona = em.createQuery(criteriaQuery).getSingleResult();
        log.debug(persona);
        
        //2-b Consulta de la persona por id = 1
        log.debug("\n2-b. Consulta de la persona id = 1");
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Persona.class);
        fromPersona = criteriaQuery.from(Persona.class);
        criteriaQuery.select(fromPersona);
        // La clase Predicate permite agregar varios criterios dinamicamente
        List<Predicate> criterios = new ArrayList<>();
        // Verificamos si tenemos criterios que agregar
        Integer idPersonaParam = 1;
        ParameterExpression<Integer> parameter = cb.parameter(Integer.class, "idPersona");
        criterios.add(cb.equal(fromPersona.get("idPersona"), parameter));
        
        // Se agregan los criterios
        if (criterios.isEmpty()) {
            throw new RuntimeException("Sin criterios");
        } else if(criterios.size() == 1){
            criteriaQuery.where(criterios.get(0));
        } else {
            criteriaQuery.where(cb.and(criterios.toArray(new Predicate[0])));
        }
        
        query = em.createQuery(criteriaQuery);
        query.setParameter("idPersona", idPersonaParam);
        
        // Se ejecuta el query
        persona = query.getSingleResult();
        log.debug(persona);
    }

    private static void mostrarPersonas(List<Persona> personas) {
        for (Persona persona : personas) {
            log.debug(persona);
        }
    }
}
