/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.service;
import gt.edu.umg.proyecto.Sistema.bienestar.repository.ClienteRepository;
import gt.edu.umg.proyecto.Sistema.entity.Cliente;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;


/**
 *
 * @author Usuario
 */

@Service
public class ClienteService {

  @Autowired
    private ClienteRepository clienteRepository;
   private final PasswordEncoder passwordEncoder = null;

    public Cliente registrarCliente(Cliente cliente) {
         if (clienteRepository.existsByEmail(cliente.getCorreo()))
            throw new IllegalArgumentException("Email ya registrado");
        if (clienteRepository.existsByDpi(cliente.getDpi()))
            throw new IllegalArgumentException("DPI ya registrado");
        
            /*Cliente cliente = Cliente.builder()
                .nombre(cliente.getNombre())
                .dpi(cliente.getDpi())
                .email(cliente.getCorreo())
                .telefono(cliente.getTelefono())
                .direccion(cliente.getDireccion())
                .fechaCreacion(cliente.getFechaCreacion()!= null ?
                        LocalDate.parse(cliente.getFechaCreacion(), DateTimeFormatter.ISO_DATE) : null)
                .password(passwordEncoder.encode(cliente.getPassword()))
                .activo(true)
                .build();*/
        
        return clienteRepository.save(cliente);
    }

    public Cliente editarCliente(Long id, Cliente clienteActualizado) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setCorreo(clienteActualizado.getCorreo());
        cliente.setTelefono(clienteActualizado.getTelefono());
        cliente.setDireccion(clienteActualizado.getDireccion());
        return clienteRepository.save(cliente);
    }

    public void desactivarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setEstado(false);
        clienteRepository.save(cliente);
    }

    public Cliente getClienteById(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long clienteId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
