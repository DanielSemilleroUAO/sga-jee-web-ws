/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.cliente.jpql;

import com.daniel.sga.domain.Persona;
import com.daniel.sga.domain.Usuario;
import java.util.Iterator;
import java.util.List;
import javax.persistence.*;
import org.apache.logging.log4j.*;

/**
 *
 * @author adseglocdom
 */
public class PruebaJPQL {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        String jpql = null;
        Query q = null;
        List<Persona> personas = null;
        Persona persona = null;
        Iterator iter = null;
        Object[] tupla = null;
        List<String> nombres = null;
        List<Usuario> usuarios = null;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        // 1. Todos los objetos tipo persona
        log.debug("\n1.Consulta de todas las personas");
        jpql = "select p from Persona p";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        // 2. Consulta de persona por id
        log.debug("\n2.Consulta de persona con id = 1");
        jpql = "select p from Persona p where p.idPersona = 1";
        persona = (Persona) em.createQuery(jpql).getSingleResult();
        log.debug(persona);
        
        // 3. Consulta de persona por nombre
        log.debug("\n3.Consulta de persona con nombre = Daniel");
        jpql = "select p from Persona p where p.nombre = 'Daniel'";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        // 4. Consulta de datos individuales
        log.debug("\n4.Consulta de datos individuales");
        jpql = "select p.nombre as Nombre, p.apellido as Apellido, p.email as Email from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();
            String nombre = (String) tupla[0];
            String apellido = (String) tupla[1];
            String email = (String) tupla[2];
            log.debug("nombre: " + nombre + ", apellido: " + apellido + ", email: " + email);
        }
        
        // 5. Obtiene el objeto persona y el id
        log.debug("\n5.Obtiene el objeto persona y el id");
        jpql = "select p, p.idPersona from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();
            persona = (Persona) tupla[0];
            int idPersona = (int) tupla[1];
            log.debug("objeto persona: " + persona );
            log.debug("idPersona: " + idPersona );
        }
        
        // 6. Consulta todas las personas
        log.debug("\n6.Consulta todas las personas");
        jpql = "select new com.daniel.sga.domain.Persona(p.idPersona) from Persona p";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        
        // 7. Regresar el valor mínimo y máximo del idPersona
        log.debug("\n7.Regresar el valor mínimo y máximo del idPersona");
        jpql = "select min(p.idPersona) as MinId, max(p.idPersona) as MaxId, count(p.idPersona) as Contador from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();
            Integer minId = (Integer) tupla[0];
            Integer maxId = (Integer) tupla[1];
            Long count = (Long) tupla[2];
            log.debug("minId: " + minId );
            log.debug("maxId: " + maxId );
            log.debug("count: " + count );
        }
        
        // 8. Cuenta los nombres que son distintos
        log.debug("\n8.Cuenta los nombres que son distintos");
        jpql = "select count(distinct p.nombre) from Persona p";
        Long contador = (Long) em.createQuery(jpql).getSingleResult();
        log.debug("Numero de persona con nombre distinto: " + contador );
        
        // 9. Concatena y convierte a mayúsculas el nombre y apellido
        log.debug("\n9.Concatena y convierte a mayúsculas el nombre y apellido");
        jpql = "select CONCAT(p.nombre, ' ', p.apellido) as Nombre from Persona p";
        nombres = em.createQuery(jpql).getResultList();
        for (String nombreCompleto : nombres) {
            log.debug(nombreCompleto);
        }
        
        // 10. Obtiene persona por id, parametro id
        log.debug("\n10. Obtiene persona por id, parametro id");
        int idPersona = 1;
        jpql = "select p from Persona p where p.idPersona = :id";
        q = em.createQuery(jpql);
        q.setParameter("id", idPersona);
        persona = (Persona) q.getSingleResult();
        log.debug(persona);
        
        // 11. Obtiene personas que contienen una letra a en el nombre sin importar mayuscula o minuscula
        log.debug("\n11. Obtiene personas que contienen una letra a en el nombre sin importar mayuscula o minuscula");
        jpql = "select p from Persona p where upper(p.nombre) like upper(:parametro)";
        String parametro = "%a%";
        q = em.createQuery(jpql);
        q.setParameter("parametro", parametro);
        personas = q.getResultList();
        mostrarPersonas(personas);
        
        // 12. Uso between
        log.debug("\n12. Uso between");
        jpql = "select p from Persona p where p.idPersona between :param1 and :param2";
        int parametroUno = 1;
        int parametroDos = 2;
        q = em.createQuery(jpql);
        q.setParameter("param1", parametroUno);
        q.setParameter("param2", parametroDos);
        personas = q.getResultList();
        mostrarPersonas(personas);
        
        // 13. Uso ordenamiento
        log.debug("\n13. Uso ordenamiento");
        jpql = "select p from Persona p where p.idPersona > 1 order by p.nombre desc, p.apellido desc";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        // 14. Uso subquery
        log.debug("\n14. Uso subquery");
        jpql = "select p from Persona p where p.idPersona in (select min(p1.idPersona) from Persona p1)";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        // 15. Uso join lazy loading -> retrasada (Cuando se solicitan)
        log.debug("\n15. Uso join lazy loading");
        jpql = "select u from Usuario u join u.persona p";
        usuarios = em.createQuery(jpql).getResultList();
        mostrarUsuarios(usuarios);
        
        // 16. Uso de left join eager loading 
        log.debug("\n16. Uso de left join eager loading ");
        jpql = "select u from Usuario u left join fetch u.persona";
        usuarios = em.createQuery(jpql).getResultList();
        mostrarUsuarios(usuarios);
       
        
    }

    private static void mostrarPersonas(List<Persona> personas) {
        for (Persona persona : personas) {
            log.debug(persona);
        }
    }

    private static void mostrarUsuarios(List<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            log.debug(usuario);
        }
    }
    
}
