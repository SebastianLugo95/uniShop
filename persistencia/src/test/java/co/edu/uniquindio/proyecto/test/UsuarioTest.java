package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    @Sql("classpath:usuarios.sql")
    public void registrarTest(){
        Ciudad ciudad = ciudadRepo.findById("1").orElse(null);
        Usuario usuario = new Usuario("754", "Julian", "julian@gmail.com", "8956", "Julian",  ciudad);

        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void eliminarTest(){
        Usuario usuario = usuarioRepo.findById("123").orElse(null);
        usuarioRepo.delete(usuario);

        Usuario usuarioEliminado = usuarioRepo.findById("123").orElse(null);
        Assertions.assertNull(usuarioEliminado);
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void actualizarTest(){
        Usuario usuario = usuarioRepo.findById("123").orElse(null);
        usuario.setPassword("11111");
        usuarioRepo.save(usuario);

        Usuario usuarioActualizado = usuarioRepo.findById("123").orElse(null);
        Assertions.assertEquals("11111", usuarioActualizado.getPassword());
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarTest(){
        List<Usuario> usuarios = usuarioRepo.findAll();
        Assertions.assertEquals(3, usuarios.size());
    }

}
