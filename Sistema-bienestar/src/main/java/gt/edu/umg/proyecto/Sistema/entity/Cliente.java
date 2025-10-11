package gt.edu.umg.proyecto.Sistema.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name= "Clientes", uniqueConstraints ={
    @UniqueConstraint(columnNames = {"correo"}),
    @UniqueConstraint(columnNames = {"dpi"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PrimaryKeyJoinColumn(name="idUsuarion")// une la llave priamaria de usuario con cliente
public class Cliente extends Usuario {

  
    
    @OneToMany(mappedBy = "cliente")
    private List<Cita> citas;
    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas;
    
    @Column(nullable = false)
    @NotBlank(message = "El dpi es obligatorio")
    @Size(max= 20, message = "El dpi no debe superar los 20 caracteres")
    private String dpi;
    
    
    @Column(nullable = false, length = 60)
    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 60, message = "El teléfono no debe superar los 60 caracteres")
    private String telefono;
    
    @Column(nullable = false, length = 150)
    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 150, message = "La dirección no debe superar los 150 caracteres")
    private String direccion;
    
    @Column(length = 500)
    @Size(max = 500, message = "El historial de sesiones no debe superar los 500 caracteres")
    private String historialsesiones;

    
 
   
    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

   
       
    

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }
    
    

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHistorialsesiones() {
        return historialsesiones;
    }

    public void setHistorialsesiones(String historialsesiones) {
        this.historialsesiones = historialsesiones;
    }
   
  // Métodos personalizados
    public boolean autenticar(String correoIngresado, String contraseñaIngresada) {
        return this.getCorreo().equals(correoIngresado)
                && this.getContraseña().equals(contraseñaIngresada);
    }

    public void actualizarDatos(String nuevoTelefono, String nuevaDireccion) {
        this.setTelefono(nuevoTelefono);
        this.setDireccion(nuevaDireccion);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dpi='" + dpi + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", historialsesiones='" + historialsesiones + '\'' +
                '}';
    }
}
    
    

