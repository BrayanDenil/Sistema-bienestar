/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.service;

import gt.edu.umg.proyecto.Sistema.bienestar.Exception.ResourceNotFoundException;
import gt.edu.umg.proyecto.Sistema.bienestar.repository.FacturaRepository;
import gt.edu.umg.proyecto.Sistema.entity.Cita;
import gt.edu.umg.proyecto.Sistema.entity.Factura;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Usuario
 */
@Service
@RequiredArgsConstructor
@Transactional
public class FacturaService {

    private final FacturaRepository facturaRepository;

    // Crear factura
    public Factura generarFactura(Cita cita, double monto, double impuesto) {
        Factura factura = new Factura(cita, monto, impuesto);
        return facturaRepository.save(factura);
    }

    // Obtener factura por ID
    public Factura obtenerFactura(Long idFactura) {
        return facturaRepository.findById(idFactura)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Factura no encontrada con ID: " + idFactura));
    }

    // Listar todas las facturas
    public List<Factura> listarFacturas() {
        return facturaRepository.findAll();
    }

    // Listar facturas por cita
    public List<Factura> obtenerFacturasPorCita(Long citaId) {
        return facturaRepository.findByCitaIdCita(citaId);
    }

    // Listar facturas por cliente
    public List<Factura> obtenerFacturasPorCliente(Long clienteId) {
        return facturaRepository.findByCitaClienteIdOrderByFechaEmisionDesc(clienteId);
    }

    // Listar facturas por rango de fechas
    public List<Factura> obtenerFacturasPorFecha(LocalDateTime inicio, LocalDateTime fin) {
        return facturaRepository.findByFechaEmisionBetween(inicio, fin);
    }

    // Actualizar factura
    public Factura actualizarFactura(Long idFactura, double monto, double impuesto) {
        Factura factura = obtenerFactura(idFactura);
        factura.setMonto(monto);
        factura.setImpuesto(impuesto);
        return facturaRepository.save(factura);
    }

    // Eliminar factura
    public void eliminarFactura(Long idFactura) {
        if (!facturaRepository.existsById(idFactura)) {
            throw new ResourceNotFoundException("Factura no encontrada con ID: " + idFactura);
        }
        facturaRepository.deleteById(idFactura);
    }
}