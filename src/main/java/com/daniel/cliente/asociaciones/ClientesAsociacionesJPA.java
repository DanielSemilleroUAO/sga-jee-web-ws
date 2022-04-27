/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.cliente.asociaciones;

import com.daniel.sga.domain.Persona;
import com.daniel.sga.domain.Usuario;
import java.util.List;
import javax.persistence.*;
import org.apache.logging.log4j.*;

/**
 *
 * @author adseglocdom
 */
public class ClientesAsociacionesJPA {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        List<Persona> personas = em.createNamedQuery("Persona.findAll").getResultList();
        
        // Cerramos la conexión
        em.close();
        
        
        // Imprimir todos los objetos tipo persona
        for (Persona persona : personas) {
            log.debug("Persona: " + persona);
            for (Usuario usuario : persona.getUsuarios()) {
                log.debug("Usuario:" + usuario);
            }
        }
    }
    
}
