/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.controller;
import gt.edu.umg.proyecto.Sistema.bienestar.service.FacturaService;
import gt.edu.umg.proyecto.Sistema.entity.Factura;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {
      private final FacturaService facturaService;

    // Crear factura
    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestParam Long citaId,
                                                @RequestParam double monto,
                                                @RequestParam double impuesto) {
        // Aquí deberías obtener la cita desde un CitaService (no incluido)
        Factura factura = facturaService.generarFactura(new gt.edu.umg.proyecto.Sistema.entity.Cita(citaId), monto, impuesto);
        return ResponseEntity.ok(factura);
    }

    // Obtener factura por ID
    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerFactura(@PathVariable Long id) {
        return ResponseEntity.ok(facturaService.obtenerFactura(id));
    }

    // Listar todas las facturas
    @GetMapping
    public ResponseEntity<List<Factura>> listarFacturas() {
        return ResponseEntity.ok(facturaService.listarFacturas());
    }

    // Listar facturas por cita
    @GetMapping("/cita/{citaId}")
    public ResponseEntity<List<Factura>> facturasPorCita(@PathVariable Long citaId) {
        return ResponseEntity.ok(facturaService.obtenerFacturasPorCita(citaId));
    }

    // Listar facturas por cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Factura>> facturasPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(facturaService.obtenerFacturasPorCliente(clienteId));
    }

    // Listar facturas por rango de fechas
    @GetMapping("/rango-fechas")
    public ResponseEntity<List<Factura>> facturasPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(facturaService.obtenerFacturasPorFecha(inicio, fin));
    }

    // Actualizar factura
    @PutMapping("/{id}")
    public ResponseEntity<Factura> actualizarFactura(@PathVariable Long id,
                                                     @RequestParam double monto,
                                                     @RequestParam double impuesto) {
        return ResponseEntity.ok(facturaService.actualizarFactura(id, monto, impuesto));
    }

    // Eliminar factura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFactura(id);
        return ResponseEntity.noContent().build();
    }
}

