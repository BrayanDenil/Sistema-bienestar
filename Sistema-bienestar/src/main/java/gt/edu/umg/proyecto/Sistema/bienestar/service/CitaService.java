/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.service;

import gt.edu.umg.proyecto.Sistema.bienestar.repository.CitaRepository;
import gt.edu.umg.proyecto.Sistema.entity.Cita;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class CitaService {
    private final CitaRepository citaRepository;

    @Autowired
    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    // Registrar una nueva cita
    public Cita reservarCita(Cita cita) {
        if (citaRepository.existsByServicioIdAndFechaHora(
                cita.getServicio().getIdServicio(), cita.getFechaHora())) {
            throw new IllegalArgumentException("Ya existe una cita para este servicio en esa fecha y hora.");
        }
        cita.reservar();
        return citaRepository.save(cita);
    }

    // Cancelar cita por ID
    public void cancelarCita(Long idCita) {
        Optional<Cita> citaOpt = citaRepository.findById(idCita);
        if (citaOpt.isPresent()) {
            Cita cita = citaOpt.get();
            cita.cancelar();
            citaRepository.save(cita);
        } else {
            throw new IllegalArgumentException("La cita con ID " + idCita + " no existe.");
        }
    }

    // Buscar todas las citas de un cliente
    public List<Cita> obtenerCitasPorCliente(Long clienteId) {
        return citaRepository.findByClienteId(clienteId);
    }

    // Buscar todas las citas de un servicio
    public List<Cita> obtenerCitasPorServicio(Long servicioId) {
        return citaRepository.findByServicioId(servicioId);
    }

    // Listar todas las citas
    public List<Cita> listarTodas() {
        return citaRepository.findAll();
    }
}
