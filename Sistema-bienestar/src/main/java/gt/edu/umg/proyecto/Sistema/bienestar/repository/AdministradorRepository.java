/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.repository;

import gt.edu.umg.proyecto.Sistema.entity.Administrador;
import gt.edu.umg.proyecto.Sistema.entity.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Repository
public interface AdministradorRepository  extends JpaRepository <Administrador,Long>{
    
    Optional<Administrador> findByCorreo(String correo); 
    boolean existsByCorreo(String correo);

    
    
}
