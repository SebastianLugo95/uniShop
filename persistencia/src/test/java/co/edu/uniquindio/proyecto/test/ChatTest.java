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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private ChatRepo chatRepo;

    @Test
    @Sql("classpath:chat.sql")
    public void crearChat(){
        Usuario usuario = usuarioRepo.findById("1").orElse(null);
        Producto producto = productoRepo.findById("2").orElse(null);
        Chat chat = new Chat("834", usuario, producto);

        Chat chatGuardado = chatRepo.save(chat);
        Assertions.assertNotNull(chatGuardado);
    }

    @Test
    @Sql("classpath:chat.sql")
    public void eliminarChat(){
        Chat chat = chatRepo.findById("564").orElse(null);
        chatRepo.delete(chat);

        Chat chatEliminado = chatRepo.findById("564").orElse(null);
        Assertions.assertNull(chatEliminado);
    }

    @Test
    @Sql("classpath:chat.sql")
    public void actualizarChat(){
        Producto producto = productoRepo.findById("2").orElse(null);
        Chat chat = chatRepo.findById("564").orElse(null);
        chat.setCodigoProducto(producto);
        chatRepo.save(chat);

        Chat chatActualizado = chatRepo.findById("564").orElse(null);
        Assertions.assertEquals(producto, chatActualizado.getCodigoProducto());
    }

    @Test
    @Sql("classpath:chat.sql")
    public void listarChats(){
        List<Chat> chats = chatRepo.findAll();
        Assertions.assertEquals(3, chats.size());

    }

}
