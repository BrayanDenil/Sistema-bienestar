/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.service;

import gt.edu.umg.proyecto.Sistema.bienestar.repository.NotificacionRepository;
import gt.edu.umg.proyecto.Sistema.entity.Cliente;
import gt.edu.umg.proyecto.Sistema.entity.Notificacion;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    @Autowired
    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    // Registrar una nueva notificación pendiente
    public Notificacion crearNotificacion(Cliente cliente, String mensaje) {
        Notificacion notificacion = new Notificacion(cliente, mensaje);
        return notificacionRepository.save(notificacion);
    }

    // Enviar una notificación específica
    public Notificacion enviarNotificacion(Long idNotificacion) {
        Optional<Notificacion> notificacionOpt = notificacionRepository.findById(idNotificacion);
        if (notificacionOpt.isPresent()) {
            Notificacion notificacion = notificacionOpt.get();
            notificacion.enviar();
            return notificacionRepository.save(notificacion);
        } else {
            throw new IllegalArgumentException("Notificación no encontrada con ID: " + idNotificacion);
        }
    }

    // Listar todas las notificaciones
    public List<Notificacion> listarNotificaciones() {
        return notificacionRepository.findAll();
    }

    // Listar notificaciones de un cliente
    public List<Notificacion> listarPorCliente(Long clienteId) {
        return notificacionRepository.findByClienteId(clienteId);
    }

    // Listar notificaciones pendientes
    public List<Notificacion> listarPendientes() {
        return notificacionRepository.findByEstadoFalse();
    }
}