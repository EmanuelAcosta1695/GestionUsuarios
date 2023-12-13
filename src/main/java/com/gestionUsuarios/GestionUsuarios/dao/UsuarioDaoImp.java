package com.gestionUsuarios.GestionUsuarios.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gestionUsuarios.GestionUsuarios.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

@Repository // Hace referencia a la bd. Que puede acceder al repositorio de la bd.
@Transactional // Le da la funcioalidad a esta clase de poder armar las consultas sql la bd.
public class UsuarioDaoImp implements UsuarioDao {

    @PersistenceContext // Handle a set of entities which hold data to be persisted in some persistence store (e.g. a database)
    EntityManager entityManager; // nos sirve para hacer la conexion con la bd

    @Override
    public List<Usuario> getUsuarios() {

        // Ponemo en nombre de la clase, no de la tabla, por estamos trabajando con esos objetos
        String query = "FROM Usuario"; // traer todos los usuarios

        // El entity ejecuta la query en la bd
        return entityManager.createQuery(query, Usuario.class).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);

        if (usuario != null) {
            entityManager.remove(usuario);
        } else {
            // Manejar el caso cuando el usuario no se encuentra
            throw new EntityNotFoundException("No se encuentra el usuario con el ID: " + id);
        }
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email"; // traer todos los usuarios

        // El entity ejecuta la query en la bd
        List<Usuario> lista = entityManager.createQuery(query, Usuario.class) //  Le indicamos a Hibernate que la lista debe contener objetos de tipo Usuario
            .setParameter("email", usuario.getEmail())
            .getResultList();
        
        // si la lista esta vacia, que no arroje un null pointer except
        if (lista.isEmpty()) {
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())) {
            return lista.get(0);
        }
        return null;
    }
    
}
