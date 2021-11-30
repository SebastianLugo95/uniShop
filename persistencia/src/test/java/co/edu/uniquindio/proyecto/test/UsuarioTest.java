package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.UsuarioYProducto;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Clase encargada de las pruebas unitarias para el CRUD del repositorio Usuario.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    /**
     * Inyección del repositorio UsuarioRepo.
     */
    @Autowired
    private UsuarioRepo usuarioRepo;

    /**
     * Inyección del repositorio CiudadRepo.
     */
    @Autowired
    private CiudadRepo ciudadRepo;

    /**
     * Prueba unitaria para insertar un registro a la tabla Usuario.
     *
     * Primero se debe consultar una ciudad para asociarla al usuario.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo usuarios.sql
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void registrarTest(){
        Ciudad ciudad = ciudadRepo.findById("1").orElse(null);
        Usuario usuario = new Usuario("754", "Julian", "julian@gmail.com", "8956", "Julian",  ciudad);

        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);

    }

    /**
     * Prueba unitaria para eliminar por su ID un registro de la tabla Usuario.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo usuarios.sql.
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void eliminarTest(){
        Usuario usuario = usuarioRepo.findById("123").orElse(null);
        usuarioRepo.delete(usuario);

        Usuario usuarioEliminado = usuarioRepo.findById("123").orElse(null);
        Assertions.assertNull(usuarioEliminado);
    }

    /**
     * Prueba unitaria para actualizar un registro buscado por su ID en la tabla Usuario.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo usuarios.sql.
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void actualizarTest(){
        Usuario usuario = usuarioRepo.findById("123").orElse(null);
        usuario.setPassword("11111");
        usuarioRepo.save(usuario);

        Usuario usuarioActualizado = usuarioRepo.findById("123").orElse(null);
        Assertions.assertEquals("11111", usuarioActualizado.getPassword());
    }

    /**
     * Prueba unitaria para consultar todos los registros de la tabla Usuario.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo usuarios.sql.
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void listarTest(){
        List<Usuario> usuarios = usuarioRepo.findAll();
        Assertions.assertEquals(3, usuarios.size());
    }

    /**
     * Prueba unitaria para filtar nombres de la tabla Usuario.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo usuarios.sql.
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void filtarNombreTest(){
        List<Usuario> lista = usuarioRepo.findAllByNombreContains("Carlos");
        lista.forEach(usuario ->  System.out.println(usuario));
    }

    /**
     * Prueba unitaria para filtar email de la tabla Usuario.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo usuarios.sql.
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void filtarEmailTest(){
        Optional<Usuario> usuario = usuarioRepo.findByEmail("carlos@gmail.com");

        if(usuario.isPresent()){
            System.out.println(usuario.get());
        }
        else{
            System.out.println("No existe ese correo");
        }

    }

    /**
     * Prueba unitaria para paginar los usuarios de la tabla Usuario.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo usuarios.sql.
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void paginarListaTest(){

        Pageable paginador = PageRequest.of(0, 2);

        Page<Usuario> lista = usuarioRepo.findAll(paginador);
        System.out.println(lista.stream().collect(Collectors.toList()));
    }

    /**
     * Prueba unitaria para ordenar los usuarios de la tabla Usuario.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo usuarios.sql.
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void ordenarListaTest(){

        List<Usuario> lista = usuarioRepo.findAll(Sort.by("nombre"));
        System.out.println(lista);
    }

    /**
     * Prueba unitaria para obtener los usuarios y sus productos.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo usuarios.sql.
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosYProductos(){
        List<UsuarioYProducto> lista = usuarioRepo.listarUsuarioYProductos();
        lista.forEach(System.out::println);
    }

}
