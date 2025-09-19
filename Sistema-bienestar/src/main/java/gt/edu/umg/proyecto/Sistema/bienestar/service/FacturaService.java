/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.service;

import gt.edu.umg.proyecto.Sistema.bienestar.repository.FacturaRepository;
import gt.edu.umg.proyecto.Sistema.entity.Cita;
import gt.edu.umg.proyecto.Sistema.entity.Factura;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;

    @Autowired
    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    // Generar factura a partir de una cita
    public Factura generarFactura(Cita cita, double monto, double impuesto) {
        Factura factura = new Factura(cita, LocalDateTime.now(), monto, impuesto);
        factura.generarFactura();  // Calcula el total y asigna la fecha
        return facturaRepository.save(factura);
    }

    // Obtener factura por ID
    public Optional<Factura> obtenerFactura(Long idFactura) {
        return facturaRepository.findById(idFactura);
    }

    // Listar todas las facturas
    public List<Factura> listarFacturas() {
        return facturaRepository.findAll();
    }

    // Obtener facturas de una cita específica
    public List<Factura> obtenerFacturasPorCita(Long citaId) {
        return facturaRepository.findByCitaIdCita(citaId);
    }

    // Eliminar factura por ID (opcional)
    public void eliminarFactura(Long idFactura) {
        facturaRepository.deleteById(idFactura);
    }}