/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.repository;
import gt.edu.umg.proyecto.Sistema.entity.Cliente;
import gt.edu.umg.proyecto.Sistema.entity.Factura;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Usuario
 */
@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    // Facturas por cita
    List<Factura> findByCitaIdCita(Long citaId);

    // Facturas por rango de fechas
    List<Factura> findByFechaEmisionBetween(LocalDateTime inicio, LocalDateTime fin);

    // Opcional: facturas de un cliente a través de la cita
    List<Factura> findByCitaClienteIdOrderByFechaEmisionDesc(Long clienteId);

}