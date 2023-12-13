package com.gestionUsuarios.GestionUsuarios.dao;

import java.util.List;

import com.gestionUsuarios.GestionUsuarios.models.Usuario;


public interface UsuarioDao {
    
    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
