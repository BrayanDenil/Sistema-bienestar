
import gt.edu.umg.proyecto.Sistema.bienestar.Config.JwtUtil;

import gt.edu.umg.proyecto.Sistema.bienestar.repository.UsuarioRepository;
import gt.edu.umg.proyecto.Sistema.entity.Usuario;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioService(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtUtil = jwtUtil;
    }

    public Usuario crearUsuario(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new IllegalArgumentException("Correo ya existe");
        }
        if (usuario.getContraseña() == null || usuario.getContraseña().isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Long id, @Valid Usuario usuario) {
        return usuarioRepository.findById(id).map(u -> {
            if (!u.getCorreo().equals(usuario.getCorreo()) 
                && usuarioRepository.existsByCorreo(usuario.getCorreo())) {
                throw new IllegalArgumentException("El correo ya está en uso");
            }

            u.setNombre(usuario.getNombre());
            u.setCorreo(usuario.getCorreo());

            if (usuario.getContraseña() != null && !usuario.getContraseña().isBlank()) {
                u.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
            }

            u.setRol(usuario.getRol());
            u.setEstado(usuario.getEstado());

            return usuarioRepository.save(u);
        }).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    public Map<String, Object> autenticar(String correo, String contrasena) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(contrasena, usuario.getContraseña())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtil.generarToken(usuario.getCorreo());

        return Map.of(
            "token", token,
            "correo", usuario.getCorreo(),
            "rol", usuario.getRol()
        );
    }}

