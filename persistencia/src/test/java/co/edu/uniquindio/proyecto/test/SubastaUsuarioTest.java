package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaUsuarioRepo;
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
 *
 * Clase encargarda de las pruebas uitarias para el CRUD del repositorio SubastaUsuario.
 *
 * @author Kevin Orlando Franco Ballejo.
 * @author Mauricio Martinez Mateus.
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubastaUsuarioTest {

    /**
     * Inyección de repositorio UsuarioRepo.
     */
    @Autowired
    private UsuarioRepo usuarioRepo;

    /**
     * Inyección de repositorio SubastaRepo.
     */
    @Autowired
    private SubastaRepo subastaRepo;

    /**
     * Inyección de repositorio SubastaUsuarioRepo.
     */
    @Autowired
    private SubastaUsuarioRepo subastaUsuarioRepo;

    /**
     * Prueba unitaria para insertar un registro a la tabla SubastaUsuario.
     *
     * Primero se debe consultar un usuario y una subasta para asociarlos a la SubustaUsuario.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo subastaUsuario.sql.
     */
    @Test
    @Sql("classpath:subastaUsuario.sql")
    public void crearSubastaUsuario() {
        Usuario usuario = usuarioRepo.findById("123").orElse(null);
        Subasta subasta = subastaRepo.findById("245").orElse(null);
        SubastaUsuario subastaUsuario = new SubastaUsuario("578", subasta, usuario, 30000.0, LocalDate.now());

        SubastaUsuario subastaUsuarioGuardada = subastaUsuarioRepo.save(subastaUsuario);
        Assertions.assertNotNull(subastaUsuarioGuardada);
    }

    /**
     * Prueba unitaria para eliminar un registro por su ID de la tabla SubastaUsuario.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo subastaUsuario.sql.
     */
    @Test
    @Sql("classpath:subastaUsuario.sql")
    public void eliminarSubastaUsuario() {
        SubastaUsuario subastaUsuario = subastaUsuarioRepo.findById("784").orElse(null);
        subastaUsuarioRepo.delete(subastaUsuario);

        SubastaUsuario subastaUsuarioEliminado = subastaUsuarioRepo.findById("784").orElse(null);
        Assertions.assertNull(subastaUsuarioEliminado);
    }

    /**
     * Prueba unitaria para actualizar un registro buscado por su ID en la tabla SubastaUsuario.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo subastaUsuario.sql.
     */
    @Test
    @Sql("classpath:subastaUsuario.sql")
    public void actualizarSubastaUsuario() {
        SubastaUsuario subastaUsuario = subastaUsuarioRepo.findById("784").orElse(null);
        subastaUsuario.setValor(50000.0);
        subastaUsuarioRepo.save(subastaUsuario);

        SubastaUsuario subastaUsuarioActualizado = subastaUsuarioRepo.findById("784").orElse(null);
        Assertions.assertEquals(50000.0, subastaUsuarioActualizado.getValor());

    }

    /**
     * Prueba unitaria para consultar todos los registros de la tabla SubastaUsuario.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo subastaUsuario.sql.
     */
    @Test
    @Sql("classpath:subastaUsuario.sql")
    public void listarSusbastaUsuario() {
        List<SubastaUsuario> subastasUsuario = subastaUsuarioRepo.findAll();
        Assertions.assertEquals(3, subastasUsuario.size());
    }
}


