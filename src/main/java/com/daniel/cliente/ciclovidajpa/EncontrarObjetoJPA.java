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
public class EncontrarObjetoJPA {
    
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // Inicia la transación
        // Paso 1. Iniciar una transaccion
        // Objeto en estado transitivo
        tx.begin();

        // Paso 2. Ejecucción SQL SELECT
        Persona persona = em.find(Persona.class, 1);
        log.debug("Objeto persistido - aun sin commit " + persona);

        // Paso 3. Termina la transaccion
        tx.commit();

        // Objeto en estado detached
        log.debug("Objeto persistido - estado detached " + persona);

        // Cerrramos el entity manager
        em.close();
    }

    
}
