package com.gestionUsuarios.GestionUsuarios.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gestionUsuarios.GestionUsuarios.models.Usuario;

import jakarta.persistence.EntityManager;
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
        return entityManager.createQuery(query).getResultList();
    }
    
}
