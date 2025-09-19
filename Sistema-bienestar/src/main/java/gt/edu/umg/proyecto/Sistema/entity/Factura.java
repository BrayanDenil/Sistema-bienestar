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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "Facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;

    @OneToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    @Column(nullable = false)
    private LocalDateTime fechaEmision;

    @Column(nullable = false)
    private double monto;

    @Column(nullable = false)
    private double impuesto;

    @Column(nullable = false)
    private double total;

    // Constructor vacío para JPA
    public Factura() {}

    // Constructor con parámetros
    public Factura(Cita cita, LocalDateTime fechaEmision, double monto, double impuesto) {
        this.cita = cita;
        this.fechaEmision = fechaEmision;
        this.monto = monto;
        this.impuesto = impuesto;
        this.total = calcularTotal();
    }

    // Getters y setters
    public Long getIdFactura() {
        return idFactura;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
        this.total = calcularTotal();
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
        this.total = calcularTotal();
    }

    public double getTotal() {
        return total;
    }

    // Métodos de negocio
    public double calcularTotal() {
        return monto + impuesto;
    }

    public void generarFactura() {
        this.fechaEmision = LocalDateTime.now();
        this.total = calcularTotal();
    }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", cita=" + (cita != null ? cita.getIdCita() : null) +
                ", fechaEmision=" + fechaEmision +
                ", monto=" + monto +
                ", impuesto=" + impuesto +
                ", total=" + total +
                '}';
    }
}
