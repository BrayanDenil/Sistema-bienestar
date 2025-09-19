/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.repository;

import gt.edu.umg.proyecto.Sistema.entity.Servicio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Usuario
 */
  //Para Gestionar Servicio
public interface ServicioRepository  extends JpaRepository<Servicio, Long> {
    List<Servicio> findByNombreContaining(String nombre);  

       // Buscar servicios activos
    List<Servicio> findByActivoTrue();

    // Buscar servicios por nombre (ejemplo de búsqueda parcial)
    List<Servicio> findByNombreContainingIgnoreCase(String nombre);
      
}

