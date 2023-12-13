package com.gestionUsuarios.GestionUsuarios.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity // para indicar que es una entidad que hace referencia a la bd
@Table(name = "usuarios") // le indicamos a que tabla apunta
public class Usuario {

    // Al poner las variables como privadas, solo podemos
    // acceder a ellas con getters y setters.
    @Getter @Setter @Column(name = "id")
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // hibernate no se da cuenta que hace referencia a una columna. Entonces lo especificamos.
    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apellido")
    private String apellido;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "telefono")
    private String telefono;

    @Getter @Setter @Column(name = "password")
    private String password;

    // CON LOMBOK NO ES NECESARIO ESTO POR SUS DECORADORES
    // public Long getId() {
    //     return id;
    // }
    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public String getNombre() {
    //     return nombre;
    // }
    // public void setNombre(String nombre) {
    //     this.nombre = nombre;
    // }

    // public String getApellido() {
    //     return apellido;
    // }
    // public void setApellido(String apellido) {
    //     this.apellido = apellido;
    // }

    // public String getEmail() {
    //     return email;
    // }
    // public void setEmail(String email) {
    //     this.email = email;
    // }

    // public String getTelefono() {
    //     return telefono;
    // }
    // public void setTelefono(String telefono) {
    //     this.telefono = telefono;
    // }

    // public String getPassword() {
    //     return password;
    // }
    // public void setPassword(String password) {
    //     this.password = password;
    // }
    
}
