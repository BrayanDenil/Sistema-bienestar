/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.repository;

import gt.edu.umg.proyecto.Sistema.entity.Cita;
import gt.edu.umg.proyecto.Sistema.entity.Cliente;
import gt.edu.umg.proyecto.Sistema.entity.Servicio;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Usuario
 */
// Gestionar la cita
public interface CitaRepository extends JpaRepository<Cita, Long> {

  List<Cita> findByClienteAndEstado(Cliente cliente);
    List<Cita> findByServicioAndFechaHoraBetween(Servicio servicio, LocalDateTime start, LocalDateTime end);
    int countByServicioAndFechaHoraBetween(Servicio servicio, LocalDateTime start, LocalDateTime end);

    public boolean existsByServicioIdAndFechaHora(Long idServicio, LocalDateTime fechaHora);

    public List<Cita> findByClienteId(Long clienteId);

    public List<Cita> findByServicioId(Long servicioId);

    
}

