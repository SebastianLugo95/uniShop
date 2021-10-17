package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MensajeTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private MensajeRepo mensajeRepo;

    @Test
    @Sql("classpath:mensaje.sql")
    public void crearMensaje(){
        Usuario usuario = usuarioRepo.findById("123").orElse(null);
        Chat chat = chatRepo.findById("564").orElse(null);
        Mensaje mensaje = new Mensaje("894", chat, "Buen producto", usuario, LocalDate.now());

        Mensaje mensajeGuardado = mensajeRepo.save(mensaje);
        Assertions.assertNotNull(mensajeGuardado);
    }

    @Test
    @Sql("classpath:mensaje.sql")
    public void eliminarMensaje(){
        Mensaje mensaje = mensajeRepo.findById("748").orElse(null);
        mensajeRepo.delete(mensaje);

        Mensaje mensajeEliminado = mensajeRepo.findById("748").orElse(null);
        Assertions.assertNull(mensajeEliminado);
    }

    @Test
    @Sql("classpath:mensaje.sql")
    public void actualizarMensaje(){
        Mensaje mensaje = mensajeRepo.findById("748").orElse(null);
        mensaje.setMensaje("Adios");
        mensajeRepo.save(mensaje);

        Mensaje mensajeActualizado= mensajeRepo.findById("748").orElse(null);
        Assertions.assertEquals("Adios", mensajeActualizado.getMensaje());
    }

    @Test
    @Sql("classpath:mensaje.sql")
    public void listarMensajes(){
        List<Mensaje> mensajes = mensajeRepo.findAll();
        Assertions.assertEquals(3, mensajes.size());
    }

}
