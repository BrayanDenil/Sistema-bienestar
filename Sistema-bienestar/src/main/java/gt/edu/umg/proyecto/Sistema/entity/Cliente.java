/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name= "Clientes")
public class Cliente extends Usuario {
    
    @Column(nullable = false, length = 60)
    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 60, message = "El teléfono no debe superar los 60 caracteres")
    private String telefono;
    
    @Column(nullable = false, length = 150)
    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 150, message = "La dirección no debe superar los 150 caracteres")
    private String direccion;
    
    @Column(length = 500)
    @Size(max = 500, message = "El historial de sesiones no debe superar los 500 caracteres")
    private String historialsesiones;
    
    //Constructor para JPA
    public Cliente(){

    } 

    public Cliente(String telefono, String direccion, String historialsesiones, Long idUsuario, String nombre, String correo, String contraseña, String rol) {
        super(idUsuario, nombre, correo, contraseña, rol);
        this.telefono = telefono;
        this.direccion = direccion;
        this.historialsesiones = historialsesiones;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHistorialsesiones() {
        return historialsesiones;
    }

    public void setHistorialsesiones(String historialsesiones) {
        this.historialsesiones = historialsesiones;
    }
   
    public boolean autenticar(String correoIngresado,String contraseñaIngresada){
        return this.getCorreo().equals(correoIngresado)&& 
               this.getContraseña().equals(contraseñaIngresada);
    
    
    
    
    }
    
    public void actualizarDatos(String nuevoTelefono,String nuevaDireccion){
        
        this.setTelefono(nuevoTelefono);
        this.setDireccion(nuevaDireccion);
    
    
    
    }

    @Override
    public String toString() {
        return "Cliente{" + "telefono=" + telefono + ", direccion=" + direccion + ", historialsesiones=" + historialsesiones + '}';
    }
    
    
    
}
