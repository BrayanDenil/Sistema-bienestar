/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.controller;

import gt.edu.umg.proyecto.Sistema.bienestar.service.ClienteService;
import gt.edu.umg.proyecto.Sistema.bienestar.service.SesionService;
import gt.edu.umg.proyecto.Sistema.entity.Cliente;
import gt.edu.umg.proyecto.Sistema.entity.Sesion;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */

    @RestController
@RequestMapping("/api/sesiones")
@RequiredArgsConstructor
public class SesionController {

    private final SesionService sesionService;
    private final ClienteService clienteService;

   // Historial por cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Sesion>> historialCliente(@PathVariable Long clienteId) {
        Cliente cliente = clienteService.obtenerClientePorId(clienteId);
        return ResponseEntity.ok(sesionService.obtenerHistorialCliente(cliente));
    }

    // Obtener detalle de sesión
    @GetMapping("/{id}")
    public ResponseEntity<Sesion> detalleSesion(@PathVariable Long id) {
        return ResponseEntity.ok(sesionService.obtenerPorId(id));
    }
}
    

