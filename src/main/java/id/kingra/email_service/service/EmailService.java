package id.kingra.email_service.service;

public interface EmailService {

    void sendingEmail(String to, String subject, String body);
}
