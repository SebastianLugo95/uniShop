package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
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
public class ComentarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Test
    @Sql("classpath:comentario.sql")
    public void crearComentario(){
        Usuario usuario = usuarioRepo.findById("1").orElse(null);
        Producto producto = productoRepo.findById("1").orElse(null);
        Comentario comentario = new Comentario("739", producto, usuario, "Messirve", "si, a bueno", LocalDate.now(), 5);

        Comentario comentarioGuardado = comentarioRepo.save(comentario);
        Assertions.assertNotNull(comentarioGuardado);
    }

    @Test
    @Sql("classpath:comentario.sql")
    public void eliminarComentario(){
        Comentario comentario = comentarioRepo.findById("748").orElse(null);
        comentarioRepo.delete(comentario);

        Comentario comentarioEliminado = comentarioRepo.findById("748").orElse(null);
        Assertions.assertNull(comentarioEliminado);
    }

    @Test
    @Sql("classpath:comentario.sql")
    public void actualizarComentario(){
        Comentario comentario = comentarioRepo.findById("748").orElse(null);
        comentario.setMensaje("No messirve");
        comentarioRepo.save(comentario);

        Comentario comentarioActualizado = comentarioRepo.findById("748").orElse(null);
        Assertions.assertEquals("No messirve", comentarioActualizado.getMensaje());
    }

    @Test
    @Sql("classpath:comentario.sql")
    public void listarComentarios(){
        List<Comentario> comentarios = comentarioRepo.findAll();
        Assertions.assertEquals(3, comentarios.size());
    }

}
