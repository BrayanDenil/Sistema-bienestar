/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.service;

import gt.edu.umg.proyecto.Sistema.bienestar.repository.SesionRepository;
import gt.edu.umg.proyecto.Sistema.entity.Cliente;
import gt.edu.umg.proyecto.Sistema.entity.Sesion;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import gt.edu.umg.proyecto.Sistema.bienestar.Exception.ResourceNotFoundException;

/**
 *
 * @author Usuario
 */

    
@Service
@RequiredArgsConstructor
public class SesionService {


   private final SesionRepository sesionRepository ;

    // 🔹 Historial de sesiones por cliente
    public List<Sesion> obtenerHistorialCliente(Cliente cliente) {
        return sesionRepository.findByClienteOrderByFechaHoraDesc(cliente);
    }

    // 🔹 Buscar sesión por ID
    public Sesion obtenerPorId(Long id) {
        return sesionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sesión no encontrada"));
    }
}