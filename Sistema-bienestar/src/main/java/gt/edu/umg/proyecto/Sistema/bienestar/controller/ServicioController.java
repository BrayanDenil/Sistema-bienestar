/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.controller;

import gt.edu.umg.proyecto.Sistema.entity.Servicio;
import gt.edu.umg.proyecto.Sistema.bienestar.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
/**
 *
 * @author Usuario
 */

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    @Autowired
    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    // Crear un nuevo servicio
    @PostMapping
    public ResponseEntity<Servicio> crearServicio(@RequestBody Servicio servicio) {
        try {
            Servicio creado = servicioService.crearServicio(servicio);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Listar todos los servicios
    @GetMapping
    public ResponseEntity<List<Servicio>> listarServicios() {
        List<Servicio> lista = servicioService.listarServicios();
        return ResponseEntity.ok(lista);
    }

    //Listar solo servicios activos
    @GetMapping("/activos")
    public ResponseEntity<List<Servicio>> listarServiciosActivos() {
        List<Servicio> activos = servicioService.listarServiciosActivos();
        return ResponseEntity.ok(activos);
    }

    // Obtener servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerServicio(@PathVariable Long id) {
        Optional<Servicio> servicio = servicioService.obtenerServicio(id);
        return servicio.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar un servicio
    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizarServicio(
            @PathVariable Long id,
            @RequestBody Servicio servicio) {
        try {
            Servicio actualizado = servicioService.actualizarServicio(
                    id,
                    servicio.getNombre(),
                    servicio.getDescripcion(),
                    servicio.getPrecio(),
                    servicio.getDuracionMinutos(),
                    servicio.getActivo()
            );
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Desactivar un servicio
    @PutMapping("/{id}/desactivar")
    public ResponseEntity<Servicio> desactivarServicio(@PathVariable Long id) {
        try {
            Servicio desactivado = servicioService.desactivarServicio(id);
            return ResponseEntity.ok(desactivado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
