package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Service
public class MailServiceImpl implements MailService {

    private static final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public Mail sendSimpleMail(Mail mail) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getUsername());
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());
        message.setTo(mail.getTo());
        message.setSentDate(mail.getSendDate());
        try {
            javaMailSender.send(message);
            log.info("success to send mail .");
        } catch (MailException e) {
            e.printStackTrace();
            log.info("failed to send mail .");
        }
        return mail;
    }

    @Override
    public Mail sendHtmlMail(Mail mail) {
        return null;
    }

    @Override
    public Mail sendAttachMail(Mail mail) {
        return null;
    }

    @Override
    public Page<Mail> listMail(Pageable pageable, Mail mail) {
        return null;
    }

    @Override
    public void sendAndSave(Mail mail) throws Exception {

    }

    @Override
    public void deleteByIdIn(Integer[] ids) {

    }
}