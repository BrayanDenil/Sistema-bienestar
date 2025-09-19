/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.repository;
import gt.edu.umg.proyecto.Sistema.entity.Cliente;
import gt.edu.umg.proyecto.Sistema.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


/**
 *
 * @author Usuario
 */

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByClienteId(Long clienteId);
      List<Factura> findByFechaEmisionBetween(java.time.LocalDateTime inicio, java.time.LocalDateTime fin);
      List<Factura> findByCitaIdCita(Long citaId);
}
