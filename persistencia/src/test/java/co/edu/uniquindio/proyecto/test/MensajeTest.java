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

/**
 * Clase encargada de las pruebas unitarias para el CRUD del repositorio Mensaje.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MensajeTest {

    /**
     * Inyección del repositorio UsuarioRepo.
     */
    @Autowired
    private UsuarioRepo usuarioRepo;

    /**
     * Inyección del repositorio ChatRepo.
     */
    @Autowired
    private ChatRepo chatRepo;

    /**
     * Inyección del repositorio MensajeRepo.
     */
    @Autowired
    private MensajeRepo mensajeRepo;

    /**
     * Prueba unitaria para insertar un registro a la tabla Mensaje.
     *
     * Primero se debe consultar un usuario para asociarlo como emisor al mensaje.
     * Después se consulta un chat para asociarle el mensaje.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo mensaje.sql
     */
    @Test
    @Sql("classpath:mensaje.sql")
    public void crearMensaje(){
        Usuario usuario = usuarioRepo.findById("123").orElse(null);
        Chat chat = chatRepo.findById("564").orElse(null);
        Mensaje mensaje = new Mensaje("894", chat, "Buen producto", usuario, LocalDate.now());

        Mensaje mensajeGuardado = mensajeRepo.save(mensaje);
        Assertions.assertNotNull(mensajeGuardado);
    }

    /**
     * Prueba unitaria para eliminar por su ID un registro de la tabla Mensaje.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo mensaje.sql.
     */
    @Test
    @Sql("classpath:mensaje.sql")
    public void eliminarMensaje(){
        Mensaje mensaje = mensajeRepo.findById("748").orElse(null);
        mensajeRepo.delete(mensaje);

        Mensaje mensajeEliminado = mensajeRepo.findById("748").orElse(null);
        Assertions.assertNull(mensajeEliminado);
    }

    /**
     * Prueba unitaria para actualizar un registro buscado por su ID en la tabla Mensaje.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo mensaje.sql.
     */
    @Test
    @Sql("classpath:mensaje.sql")
    public void actualizarMensaje(){
        Mensaje mensaje = mensajeRepo.findById("748").orElse(null);
        mensaje.setMensaje("Adios");
        mensajeRepo.save(mensaje);

        Mensaje mensajeActualizado= mensajeRepo.findById("748").orElse(null);
        Assertions.assertEquals("Adios", mensajeActualizado.getMensaje());
    }

    /**
     * Prueba unitaria para consultar todos los registros de la tabla Mensaje.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo mensaje.sql.
     */
    @Test
    @Sql("classpath:mensaje.sql")
    public void listarMensajes(){
        List<Mensaje> mensajes = mensajeRepo.findAll();
        Assertions.assertEquals(3, mensajes.size());
    }

}
