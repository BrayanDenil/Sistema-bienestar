/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.repository;

import gt.edu.umg.proyecto.Sistema.entity.Notificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Usuario
 */
public interface NotificacionRepository  extends JpaRepository<Notificacion, Long>{
  List<Notificacion> findByCliendteId(Long clienteId);
  List<Notificacion> findByTipo(String Tipo);
  List<Notificacion> findByEstadoFalse();
  List<Notificacion> findByClienteId(Long clienteId);
  
}
