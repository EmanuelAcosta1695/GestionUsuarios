package com.gestionUsuarios.GestionUsuarios.dao;

import java.util.List;

import com.gestionUsuarios.GestionUsuarios.models.Usuario;


public interface UsuarioDao {
    
    List<Usuario> getUsuarios();
}
