/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.repository;

import gt.edu.umg.proyecto.Sistema.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */

   //Para Gestionar los usuarios 
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    
      Optional<Usuario> findByCorreo(String correo);  // Consulta por correo
    boolean existsByCorreo(String correo);          // Validación de integridad
}


