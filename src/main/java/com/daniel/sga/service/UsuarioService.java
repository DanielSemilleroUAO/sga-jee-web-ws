/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.sga.service;

import com.daniel.sga.domain.Usuario;
import java.util.List;

/**
 *
 * @author adseglocdom
 */
public interface UsuarioService {
    
    public List<Usuario> findAllUsuarios();

    public Usuario findUsuarioById(Usuario persona);

    public Usuario findUsuarioByEmail(Usuario persona);

    public void insertUsuario(Usuario persona);

    public void updateUsuario(Usuario persona);

    public void deleteUsuario(Usuario persona);
    
}
