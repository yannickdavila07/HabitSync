package br.com.api.habitFlow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCodigoVerificacao(String emailDestinado, String codigo){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDestinado);
        message.setSubject("HabitSync - Código de Verificação");
        message.setText("Seu código de verificação para ativar a conta é: " + codigo +
                "\n\nEste código é válido por 15 minutos.");
        mailSender.send(message);
    }

}
