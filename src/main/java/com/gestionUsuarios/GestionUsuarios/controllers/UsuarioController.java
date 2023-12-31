package com.gestionUsuarios.GestionUsuarios.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gestionUsuarios.GestionUsuarios.dao.UsuarioDao;
import com.gestionUsuarios.GestionUsuarios.models.Usuario;
import com.gestionUsuarios.GestionUsuarios.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;


@RestController // Para indicarle que esta clase es un controlador.
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;
    
    @RequestMapping(value = "usuarios")
    public List<Usuario> getUsuarios(@RequestHeader(value="Autorization") String token) { 

        if (!validarToken(token)) { return null; }

        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);

        return usuarioId != null;
    }

    @RequestMapping(value = "usuario", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario) { 

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "usuario/{id}", method = RequestMethod.DELETE)
    public void eliminarUsuario(@RequestHeader(value="Autorization") String token, @PathVariable Long id) { 

        if (!validarToken(token)) { return; }

        usuarioDao.eliminar(id);
    }

}