package com.correo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service //Indica que todo lo siguiente es un Servicio, en este caso, el de enviar un correo
// Le dice a Spring que esta clase es un servicio para usarla en otras partes.
public class EmailService {

    @Autowired //Inyect un objeto dentro de otro
    // Inyecta automáticamente el objeto encargado de enviar correos.
    private JavaMailSender mailSender;

    @Autowired
    // Inyecta el motor que procesa las plantillas HTML.
    private TemplateEngine templateEngine;

    public void enviarCorreoTemplate(String destinatario, String asunto,
            String nombreUsuario, String codigo)
            throws MessagingException {
        // Método que envía un correo usando plantilla HTML.

        MimeMessage message = mailSender.createMimeMessage();
        // Crea el correo como objeto.

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        // Ayuda a configurar el correo.
        // true = permite HTML y multipart
        // UTF-8 = soporta acentos y caracteres especiales

        Context context = new Context();
        // Crea el contexto para mandar datos al HTML.

        context.setVariable("nombre", nombreUsuario);
        context.setVariable("codigo", codigo);

        String contenidoHtml = templateEngine.process("email-template", context);
        // Procesa la plantilla HTML llamada email-template.html
        // y reemplaza las variables con sus valores.

        helper.setTo(destinatario);
        helper.setSubject(asunto);
        helper.setText(contenidoHtml, true);
        // Inserta el contenido HTML en el correo.
        // true = indica que es HTML.

        mailSender.send(message);
    }
}
