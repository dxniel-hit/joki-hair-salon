package co.edu.uniquindio.jokihairstyle.services.implementation;

import co.edu.uniquindio.jokihairstyle.services.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

// This class must not invoke Admin, AdminService or AdminRepository. Just to make sure.
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendDiscountEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Código de descuento en Joki Hairstyle!");
        message.setText("Puedes usar este código en Joki Hairstyle para recibir un 10% de descuento en tu próxima cita.\n Código: " + generateRndVerificationCode());
        mailSender.send(message);
    }

    private String generateRndVerificationCode() {

        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(rnd.nextInt(10));
        }
        return sb.toString();
    }
}