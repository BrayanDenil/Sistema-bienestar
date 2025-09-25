/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Usuario
 */


@Entity
@Inheritance(strategy= InheritanceType.JOINED)//Para Aplicara la herncia a Cliente y admin
public class Usuario {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idUsuario;
    
    @Column(nullable =false,length = 100)
    @NotBlank(message= "El Nombre no puede estar Vacio")
    private String nombre;
    
   @Column(nullable =false,unique= true,length = 100)
   @NotBlank(message ="El correo el obliagario")
   @Email(message = "Correo no Valido")
    private String correo;
   
   
   @Column(nullable=false)
   @NotBlank(message ="La contraseña el obligatoria")
   @Size(min= 6, message = "La contraseña debe tener al menos 6 caracteres")
   private String contraseña;
   
   
   @Column (nullable= false,length = 20)
    @NotBlank(message ="El rol es obligatorio")
   private String rol;
   
   
   @Column (nullable = false)
    private Boolean estado= true;

    public Usuario() {
    }

    public Usuario(Long idUsuario, String nombre, String correo, String contraseña, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }
      
    
    
    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    
    
    public void autenticar(){
    
    }
    
    public void actualizarDatos(){
    
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", correo=" + correo + ", contrase\u00f1a=" + contraseña + ", rol=" + rol + ", estado=" + estado + '}';
    }
    
    
}

