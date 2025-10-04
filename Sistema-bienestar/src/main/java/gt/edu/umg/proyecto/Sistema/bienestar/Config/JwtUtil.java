/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.proyecto.Sistema.bienestar.Config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;


/**
 *
 * @author Usuario
 */
@Component
public class JwtUtil {
    private final String secret = "claveSecreta123"; // ⚠️ cámbiala en prod
    private final long expiracion = 1000 * 60 * 60; // 1 hora

    // Generar token con correo como "subject"
    public String generarToken(String correo) {
        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiracion))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Extraer correo desde el token
    public String extraerCorreo(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
    
