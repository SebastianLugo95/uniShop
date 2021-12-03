package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Trim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 *
 * Clase encargarda de las pruebas uitarias para el CRUD del repositorio Chat.
 *
 * @author Kevin Orlando Franco Ballejo.
 * @author Mauricio Martinez Mateus.
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatTest {

    /**
     * Inyección de repositorio UsuarioRepo.
     */
    @Autowired
    private UsuarioRepo usuarioRepo;

    /**
     * Inyección de repositorio ProductoRepo.
     */
    @Autowired
    private ProductoRepo productoRepo;

    /**
     * Inyección de repositorio ChatRepo.
     */
    @Autowired
    private ChatRepo chatRepo;

    /**
     * Prueba unitaria para registrar un registro a la tabla Chat.
     *
     * Primero se debe consultar un usuario y un producto para asociarlos al chat.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo chat.sql.
     */
    @Test
    @Sql("classpath:chat.sql")
    public void crearChat(){
        Usuario usuario = usuarioRepo.findById("1").orElse(null);
        Producto producto = productoRepo.findById(2).orElse(null);
        Chat chat = new Chat("834", usuario, producto);

        Chat chatGuardado = chatRepo.save(chat);
        Assertions.assertNotNull(chatGuardado);
    }

    /**
     * Prueba unitaria para eliminar un registro por su ID de la tabla Chat.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo chat.sql.
     */
    @Test
    @Sql("classpath:chat.sql")
    public void eliminarChat(){
        Chat chat = chatRepo.findById("564").orElse(null);
        chatRepo.delete(chat);

        Chat chatEliminado = chatRepo.findById("564").orElse(null);
        Assertions.assertNull(chatEliminado);
    }

    /**
     * Prueba unitaria para actualizar un registro buscado por su ID en la tabla Chat.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo chat.sql.
     */
    @Test
    @Sql("classpath:chat.sql")
    public void actualizarChat(){
        Producto producto = productoRepo.findById(2).orElse(null);
        Chat chat = chatRepo.findById("564").orElse(null);
        chat.setCodigoProducto(producto);
        chatRepo.save(chat);

        Chat chatActualizado = chatRepo.findById("564").orElse(null);
        Assertions.assertEquals(producto, chatActualizado.getCodigoProducto());
    }

    /**
     * Prueba unitaria para consultar todos los registros de la tabla Chat.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo chat.sql.
     */
    @Test
    @Sql("classpath:chat.sql")
    public void listarChats(){
        List<Chat> chats = chatRepo.findAll();
        Assertions.assertEquals(3, chats.size());

    }

}
