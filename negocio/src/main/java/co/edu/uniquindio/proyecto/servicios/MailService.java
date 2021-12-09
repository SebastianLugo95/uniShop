package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Mail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MailService {
    Mail sendSimpleMail(Mail mail);

    Mail sendHtmlMail(Mail mail);

    Mail sendAttachMail(Mail mail);

    Page<Mail> listMail(Pageable pageable, Mail mail);

    void sendAndSave(Mail mail) throws Exception;

    void deleteByIdIn(Integer[] ids);
}
