/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.repository;

import gt.edu.umg.proyecto.Sistema.entity.Cita;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Usuario
 */
// Gestionar la cita
public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Buscar citas por cliente usando el ID heredado de Usuario
    List<Cita> obtenerCitasPorCliente(Long clienteId);
    // Buscar citas por estado
    List<Cita> findByEstado(Boolean estado);

    // Buscar citas por servicio
    List<Cita> findByServicioId(Long servicioId);

    // Buscar citas en una fecha/hora específica
    List<Cita> findByFechaHora(LocalDateTime fechaHora);

    // Verificar si ya existe una cita en la misma fecha y hora con el mismo servicio
    boolean existsByServicioIdAndFechaHora(Long servicioId, LocalDateTime fechaHora);

    
}

