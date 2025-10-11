package gt.edu.umg.proyecto.Sistema.bienestar.service;

import gt.edu.umg.proyecto.Sistema.entity.Usuario;
import gt.edu.umg.proyecto.Sistema.entity.Cliente;
import gt.edu.umg.proyecto.Sistema.entity.Administrador;
import gt.edu.umg.proyecto.Sistema.bienestar.repository.UsuarioRepository;
import gt.edu.umg.proyecto.Sistema.bienestar.repository.ClienteRepository;
import gt.edu.umg.proyecto.Sistema.bienestar.repository.AdministradorRepository;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Usuario
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final AdministradorRepository administradorRepository;
    private final PasswordEncoder passwordEncoder;

    // Crear usuario general
    public Usuario crearUsuario(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        usuario.setEstado(true);

        return usuarioRepository.save(usuario);
    }

    // Crear cliente
    public Cliente crearCliente(Cliente cliente) {
        if (usuarioRepository.existsByCorreo(cliente.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        cliente.setContraseña(passwordEncoder.encode(cliente.getContraseña()));
        cliente.setRol("CLIENTE");
        cliente.setEstado(true);

        return clienteRepository.save(cliente);
    }

    // Crear administrador
    public Administrador crearAdministrador(Administrador admin) {
        if (usuarioRepository.existsByCorreo(admin.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        admin.setContraseña(passwordEncoder.encode(admin.getContraseña()));
        admin.setRol("ADMIN");
        admin.setEstado(true);

        return administradorRepository.save(admin);
    }

    // Listar todos
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar por ID
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Actualizar
    public Usuario actualizar(Long id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        existente.setNombre(usuario.getNombre());
        existente.setCorreo(usuario.getCorreo());
        existente.setRol(usuario.getRol());

        if (usuario.getContraseña() != null && !usuario.getContraseña().isEmpty()) {
            existente.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        }

        return usuarioRepository.save(existente);
    }

    // Eliminar
    public void eliminarUsuario(Long id) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        usuarioRepository.delete(existente);
    }

    // Autenticación
    public Map<String, Object> autenticar(String correo, String contrasena) {
        Map<String, Object> respuesta = new HashMap<>();

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        if (!passwordEncoder.matches(contrasena, usuario.getContraseña())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        if (!usuario.getEstado()) {
            throw new IllegalStateException("Usuario inactivo");
        }

        respuesta.put("mensaje", "Login exitoso");
        respuesta.put("usuario", usuario.getNombre());
        respuesta.put("rol", usuario.getRol());
        respuesta.put("id", usuario.getIdUsuario());

        return respuesta;
    }
}