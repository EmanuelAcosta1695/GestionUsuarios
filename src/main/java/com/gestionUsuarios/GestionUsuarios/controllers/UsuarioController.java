package com.gestionUsuarios.GestionUsuarios.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gestionUsuarios.GestionUsuarios.dao.UsuarioDao;
import com.gestionUsuarios.GestionUsuarios.models.Usuario;


@RestController // Para indicarle que esta clase es un controlador.
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping(value = "usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id) { 

        Usuario usuario = new Usuario();

        usuario.setId(id);
        usuario.setNombre("Roberto");
        usuario.setApellido("Perez");
        usuario.setEmail("rperez@mail.com");
        usuario.setTelefono("123123123");

        return usuario;
    }

    
    @RequestMapping(value = "usuarios")
    public List<Usuario> getUsuarios() { 

        return usuarioDao.getUsuarios();
    }

}