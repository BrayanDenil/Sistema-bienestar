/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author Usuario
 */

@Entity
@Table(name = "Administrador")
public class Administrador extends Usuario{
    
    //Constructor para el JPA
    public Administrador(){
    
    }

    public Administrador(Long idUsuario, String nombre, String correo, String contraseña, String rol) {
        super(idUsuario, nombre, correo, contraseña, rol);
    }

     @Override
    public String toString() {
        return "Administrador{" +
                "id=" + getIdUsuario() +
                ", nombre='" + getNombre() + '\'' +
                ", correo='" + getCorreo() + '\'' +
                ", rol='" + getRol() + '\'' +
                '}';
    }
}

