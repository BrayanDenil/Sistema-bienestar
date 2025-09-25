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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "Notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotificacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "La notificación debe estar asociada a un cliente")
    private Cliente cliente;

    @Column(nullable = false, length = 500)
     @NotBlank(message = "El mensaje no puede estar vacío")
    @Size(max = 500, message = "El mensaje no puede exceder 500 caracteres")
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fechaEnvio;

    @Column(nullable = false)
    private Boolean estado; // true = enviada, false = pendiente

    // Constructor vacío (requerido por JPA)
    public Notificacion() {}

    // Constructor con parámetros
    public Notificacion(Cliente cliente, String mensaje) {
        this.cliente = cliente;
        this.mensaje = mensaje;
        this.fechaEnvio = LocalDateTime.now();
        this.estado = false; // inicialmente pendiente
    }

    // Getters y Setters
    public Long getIdNotificacion() {
        return idNotificacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    // Método para enviar la notificación
    public void enviar() {
        this.estado = true;
        this.fechaEnvio = LocalDateTime.now();
        // Aquí podrías agregar lógica real de envío (correo, push, SMS, etc.)
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "idNotificacion=" + idNotificacion +
                ", cliente=" + (cliente != null ? cliente.getNombre() : null) +
                ", mensaje='" + mensaje + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                ", estado=" + estado +
                '}';
    }
}
