/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.service;

import gt.edu.umg.proyecto.Sistema.bienestar.repository.ClienteRepository;
import gt.edu.umg.proyecto.Sistema.entity.Cliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Usuario
 */
@Service
public class ClienteService {
    
    
    private final ClienteRepository clienteRepository;
    
    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
   
    public Cliente registrarCliente(Cliente cliente) {
        if (clienteRepository.existsByCorreo(cliente.getCorreo())) {
            throw new IllegalArgumentException("Correo ya registrado");
        }
        return clienteRepository.save(cliente);
    }
    public List<Cliente> listarClientes() {
    return clienteRepository.findAll();
}
}
    

