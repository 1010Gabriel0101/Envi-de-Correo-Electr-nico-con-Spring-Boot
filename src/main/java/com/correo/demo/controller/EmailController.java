package com.correo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.correo.demo.service.EmailService;

import jakarta.mail.MessagingException;
// Importa la excepción por errores al enviar correos.

@RestController
// Le dice a Spring que esta clase controlará peticiones web.
public class EmailController {

    @Autowired //Autowired es para pasar un objeto a otro objeto
    // Inyecta automáticamente el servicio de correos.
    private EmailService emailService;

    @GetMapping("/enviar-correo")
    // Indica que cuando entren a esta URL: /enviar-correo, se ejecutará el siguiente método.
    public String enviar(@RequestParam String email,
            @RequestParam String nombre) {
        // Método que recibe email y nombre desde la URL.

        try {
            // Intenta ejecutar el envío del correo.

            String codigo = "ABC-123";
            // Simula un código de bienvenida o seguridad.

            emailService.enviarCorreoTemplate(
                    email,
                    "Correo enviado con SpringBoot",
                    nombre,
                    codigo
            );
            return "Correo enviado con éxito a: " + email;
        } catch (MessagingException e) {
            return "Error al enviar el correo: " + e.getMessage();
        }
    }
}
