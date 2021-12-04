package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    public void registrarTest(){
        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);
        Usuario usuario = new Usuario("754", "Julian", "julian@gmail.com", "8956", "Julian",  ciudad);
        try {
            Usuario respuesta = usuarioServicio.registrarUsuario(usuario);
            Assertions.assertNotNull(respuesta);
            usuarioServicio.registrarUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void eliminarTest(){
        try{
            usuarioServicio.eliminarUsuario("123");
            Assertions.assertTrue(true);
        }catch(Exception e){
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void listarTest(){
        List<Usuario> lista = usuarioServicio.listarUsuarios();
        lista.forEach(System.out::println);
    }

    @Test
    public void actualizarTest(){
        try {
            Usuario u = usuarioServicio.obtenerUsuario("123");
            u.setPassword("reposadito");
            usuarioServicio.actualizarUsuario(u);
            Usuario respuesta = usuarioServicio.actualizarUsuario(u);
            Assertions.assertEquals("reposadito", respuesta.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginTest(){
        try {
            Usuario usuario = usuarioServicio.iniciarSesion("julian@gmail.com", "8956");
            Assertions.assertNotNull(usuario);
        } catch (Exception e) {
            Assertions.assertTrue(false, e.getMessage());
        }
    }


}
