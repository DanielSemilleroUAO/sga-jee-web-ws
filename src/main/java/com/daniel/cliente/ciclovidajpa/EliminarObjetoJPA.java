/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.cliente.ciclovidajpa;

import com.daniel.sga.domain.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author adseglocdom
 */
public class EliminarObjetoJPA {
    
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // Inicia la transación
        // Paso 1. Iniciar una transacion
        tx.begin();

        // Paso 2. Ejecuccion SQL SELECT
        Persona persona = em.find(Persona.class, 4);
        log.debug("Objeto encontrado: " + persona);

        // Paso 3. Ejecuccion SQL DELETE
        em.remove(persona);
        
        // Paso 4. Termina la transaccion
        tx.commit();
        
        log.debug("Objeto eliminado");

        // Cerrramos el entity manager
        em.close();
    }
    
}
