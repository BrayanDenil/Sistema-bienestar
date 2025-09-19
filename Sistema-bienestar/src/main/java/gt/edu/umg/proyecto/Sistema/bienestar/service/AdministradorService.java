/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.service;

import gt.edu.umg.proyecto.Sistema.bienestar.repository.AdministradorRepository;
import gt.edu.umg.proyecto.Sistema.entity.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class AdministradorService {
    private final AdministradorRepository administradorRepository;
    
    @Autowired
    public AdministradorService(AdministradorRepository administradorRepository){
    this.administradorRepository = administradorRepository;
    
    }
    
    public Administrador registrarAdmnistrador(Administrador admin){
     if (administradorRepository.existsByCorreo(admin.getCorreo())){
     throw new IllegalArgumentException("Correo ya registrado");
     
     }
    return administradorRepository.save(admin);
    }
    
    
}
