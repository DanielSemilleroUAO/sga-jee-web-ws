/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.sga.web;

import com.daniel.sga.domain.Usuario;
import com.daniel.sga.service.UsuarioService;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author adseglocdom
 */
@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet{
    
    @Inject
    UsuarioService usuarioService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<Usuario> usuarios = usuarioService.findAllUsuarios();
        
        System.out.println("Usuarios:" +usuarios);
        req.setAttribute("usuarios", usuarios);
        req.getRequestDispatcher("/listadoUsuarios.jsp").forward(req, resp);

    }
    
}
