/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.repository;

import gt.edu.umg.proyecto.Sistema.entity.Servicio;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Usuario
 */
  //Para Gestionar Servicio
public interface ServicioRepository  extends JpaRepository<Servicio, Long> {
    
    
    // Buscar servicios por nombre (parcial, ignorando mayúsculas/minúsculas)
    List<Servicio> findByNombreContainingIgnoreCase(String nombre);

    // Listar solo servicios activos
    List<Servicio> findByActivoTrue();

    // Buscar servicios activos por nombre (combinado)
    List<Servicio> findByNombreContainingIgnoreCaseAndActivoTrue(String nombre);

    // Listar servicios activos ordenados por nombre
    List<Servicio> findByActivoTrueOrderByNombreAsc();

    public Servicio save(Optional<Servicio> servicio);
}

