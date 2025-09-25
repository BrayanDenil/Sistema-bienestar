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
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */

@Entity
@Table(name = "Citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    @Column(nullable = false)
    @NotNull(message = "La fecha y hora de la cita es obligatoria")
    @Future(message = "La fecha y hora de la cita debe ser en el futuro")
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    @NotNull(message = "El estado de la cita es obligatorio")
    private Boolean estado; // true = activa, false = cancelada

    @ManyToOne(optional =false)
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "El cliente es obligatorio")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false)
    @NotNull(message = "El servicio es obligatorio")
    private Servicio servicio;

    @Column(length = 500)
    private String notas;

    // Constructor para JPA
    public Cita() {}

    // Constructor con parámetros
    public Cita(LocalDateTime fechaHora, Boolean estado, Cliente cliente, Servicio servicio, String notas) {
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.cliente = cliente;
        this.servicio = servicio;
        this.notas = notas;
    }

    // Getters y Setters
    public Long getIdCita() {
        return idCita;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    // Métodos de negocio
    public void reservar() {
        this.estado = true;
    }

    public void cancelar() {
        this.estado = false;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "idCita=" + idCita +
                ", fechaHora=" + fechaHora +
                ", estado=" + estado +
                ", cliente=" + (cliente != null ? cliente.getNombre() : "null") +
                ", servicio=" + (servicio != null ? servicio.toString() : "null") +
                ", notas='" + notas + '\'' +
                '}';
    }
}