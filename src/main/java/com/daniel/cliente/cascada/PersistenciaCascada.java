/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.cliente.cascada;

import com.daniel.sga.domain.Persona;
import com.daniel.sga.domain.Usuario;
import javax.persistence.*;
import org.apache.logging.log4j.*;

/**
 *
 * @author adseglocdom
 */
public class PersistenciaCascada {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        Persona persona = new Persona("Santiago", "Hernandwz", "sh@gmail.com", "11223344");
        
        Usuario usuario = new Usuario("sherna", "11223344", persona);
        
        em.persist(usuario);
        
        tx.commit();
        
        log.debug("Persona: " + persona);
        log.debug("Usuario: " + usuario);
        
        em.close();
    }

}
