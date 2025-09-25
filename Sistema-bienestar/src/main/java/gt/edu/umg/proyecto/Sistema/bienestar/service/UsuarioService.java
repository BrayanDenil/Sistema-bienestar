/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.service;


import gt.edu.umg.proyecto.Sistema.bienestar.repository.UsuarioRepository;
import gt.edu.umg.proyecto.Sistema.entity.Usuario;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 *
 * @author Usuario
 */

    @Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public Usuario crearUsuario(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new IllegalArgumentException("Correo ya existe");
        }
        return usuarioRepository.save(usuario);
    }

    
    public Usuario actualizar(Long id, @Valid Usuario usuario) {
        return usuarioRepository.findById(id).map(u -> {
            u.setNombre(usuario.getNombre());
            u.setCorreo(usuario.getCorreo());
            u.setContraseña(usuario.getContraseña());
            u.setRol(usuario.getRol());
            u.setEstado(usuario.getEstado());
            return usuarioRepository.save(u);
        }).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Object buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }
}

