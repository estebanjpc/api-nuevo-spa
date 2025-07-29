package com.app.controllers;

import com.app.entity.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Operation(
        summary = "Iniciar sesión",
        description = "Autentica al usuario y devuelve un token JWT"
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        return ResponseEntity.ok("Este endpoint está protegido por JwtAuthenticationFilter");
    }
}