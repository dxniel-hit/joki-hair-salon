package co.edu.uniquindio.jokihairstyle.services.interfaces;


public interface EmailService {

    void sendDiscountEmail(String toEmail);
    void sendVerificationMail(String to, String verCode);
}