package gt.edu.umg.proyecto.Sistema.bienestar.Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;
/**
 *
 * @author Usuario
 */
@Component
public class JwtService {

    private static final String SECRET_KEY = "clave_secreta_segura";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    // 🔹 Generar token
    public String generarToken(String correo) {
        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // 🔹 Validar token
    public boolean validarToken(String token, String correo) {
        String correoExtraido = extraerCorreo(token);
        return (correoExtraido.equals(correo) && !estaExpirado(token));
    }

    // 🔹 Extraer correo
    public String extraerCorreo(String token) {
        return extraerClaims(token).getSubject();
    }

    // 🔹 Verificar expiración
    private boolean estaExpirado(String token) {
        return extraerClaims(token).getExpiration().before(new Date());
    }

    // 🔹 Extraer Claims
    private Claims extraerClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
 

