/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.sga.web;

import com.daniel.sga.domain.Persona;
import com.daniel.sga.service.PersonaService;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adseglocdom
 */
@WebServlet("/personas")
public class PersonaServlet extends HttpServlet{
    
    @Inject
    PersonaService personaService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<Persona> personas = personaService.listarPersonas();
        
        System.out.println("Personas:" +personas);
        req.setAttribute("personas", personas);
        req.getRequestDispatcher("/listadoPersonas.jsp").forward(req, resp);

    }
    
    
    
}
