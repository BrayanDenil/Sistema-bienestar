/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.service;

import gt.edu.umg.proyecto.Sistema.bienestar.repository.ServicioRepository;
import gt.edu.umg.proyecto.Sistema.entity.Servicio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;

    @Autowired
    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    // Crear un nuevo servicio
    public Servicio crearServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    // Actualizar un servicio existente
    public Servicio actualizarServicio(Long idServicio, String nombre, String descripcion,
                                       Double precio, Integer duracionMinutos, Boolean activo) {
        Optional<Servicio> servicioOpt = servicioRepository.findById(idServicio);
        if (servicioOpt.isPresent()) {
            Servicio servicio = servicioOpt.get();
            servicio.actualizar(nombre, descripcion, precio, duracionMinutos, activo);
            return servicioRepository.save(servicio);
        } else {
            throw new IllegalArgumentException("Servicio no encontrado con ID: " + idServicio);
        }
    }

    // Listar todos los servicios
    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }

    // Listar solo los servicios activos
    public List<Servicio> listarServiciosActivos() {
        return servicioRepository.findByActivoTrue();
    }

    // Obtener servicio por ID
    public Optional<Servicio> obtenerServicio(Long idServicio) {
        return servicioRepository.findById(idServicio);
    }

    // Desactivar un servicio
    public Servicio desactivarServicio(Long idServicio) {
        Optional<Servicio> servicioOpt = servicioRepository.findById(idServicio);
        if (servicioOpt.isPresent()) {
            Servicio servicio = servicioOpt.get();
            servicio.setActivo(false);
            return servicioRepository.save(servicio);
        } else {
            throw new IllegalArgumentException("Servicio no encontrado con ID: " + idServicio);
        }
    }
}