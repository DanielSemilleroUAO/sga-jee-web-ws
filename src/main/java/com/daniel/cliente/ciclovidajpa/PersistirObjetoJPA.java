/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.cliente.ciclovidajpa;

import com.daniel.sga.domain.Persona;
import javax.persistence.*;
import org.apache.logging.log4j.*;

/**
 *
 * @author adseglocdom
 */
public class PersistirObjetoJPA {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // Inicia la transación
        // Paso 1. Cre un nuevo objeto
        // Objeto en estado transitivo
        Persona persona = new Persona("Pedro", "Luna", "pl@gmail.com", "11223344");

        // Paso 2. Inicia la transacion
        tx.begin();

        // Paso 3. Ejecucción SQL
        em.persist(persona);
        log.debug("Objeto persistido - aun sin commit " + persona);

        // Paso 4. commit/rollback
        tx.commit();

        // Objeto en estado detached
        log.debug("Objeto persistido - estado detached " + persona);

        // Cerrramos el entity manager
        em.close();
    }

}
