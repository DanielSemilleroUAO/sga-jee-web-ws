/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.sga.service;

import com.daniel.sga.datos.UsuarioDao;
import com.daniel.sga.domain.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author adseglocdom
 */
@Stateless
public class UsuarioServiceImpl implements UsuarioService{
    
    @Inject
    private UsuarioDao usuarioDao;

    @Override
    public List<Usuario> findAllUsuarios() {
        return usuarioDao.findAllUsuarios();
    }

    @Override
    public Usuario findUsuarioById(Usuario persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario findUsuarioByEmail(Usuario persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertUsuario(Usuario persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUsuario(Usuario persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUsuario(Usuario persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
