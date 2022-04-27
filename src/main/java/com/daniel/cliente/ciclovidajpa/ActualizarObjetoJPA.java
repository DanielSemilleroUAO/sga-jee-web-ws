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
public class ActualizarObjetoJPA {
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // Inicia la transación
        // Paso 1. Iniciar una transacion
        tx.begin();

        // Paso 2. Ejecuccion SQL SELECT
        Persona persona = em.find(Persona.class, 1);

        // Paso 3. Termina transaccion
        tx.commit();
        
        // Objeto en estado detached
        log.debug("Objeto persistido - estado detached " + persona);
        
        // Paso 4. Set Value
        persona.setApellido("Rodriguez");
        
        // Paso 5. Inicia una nueva transacción
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        
        // Paso 6. Modificamos el objeto
        em.merge(persona);
        
        // Paso 7. Termina transaccion 2
        tx2.commit();

        // Objeto en estado detached - modificado
        log.debug("Objeto persistido - estado detached " + persona);

        // Cerrramos el entity manager
        em.close();
    }
}
