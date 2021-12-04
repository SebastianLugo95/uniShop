package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 * Clase encargada de las pruebas unitarias para el CRUD del repositorio Ciudad.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    /**
     * Inyección del repositorio CiudadRepo.
     */
    @Autowired
    private CiudadRepo ciudadRepo;

    /**
     * Prueba unitaria para insertar un registro a la tabla Ciudad.
     */
    @Test
    public void crearCiudad(){
        Ciudad ciudad = new Ciudad(9, "Manizales");

        Ciudad ciudadGuardada = ciudadRepo.save(ciudad);
        Assertions.assertNotNull(ciudadGuardada);
    }

    /**
     * Prueba unitaria para eliminar por su ID un registro de la tabla Ciudad.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo ciudad.sql.
     */
    @Test
    @Sql("classpath:ciudad.sql")
    public void eliminarCiudad(){
        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);
        ciudadRepo.delete(ciudad);

        Ciudad ciudadEliminada = ciudadRepo.findById(1).orElse(null);
        Assertions.assertNull(ciudadEliminada);

    }

    /**
     * Prueba unitaria para actualizar un registro buscado por su ID en la tabla Ciudad.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo ciudad.sql.
     */
    @Test
    @Sql("classpath:ciudad.sql")
    public void actualizarCiudad(){
        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);
        ciudad.setNombre("Valledupar");
        ciudadRepo.save(ciudad);

        Ciudad ciudadActualizada = ciudadRepo.findById(1).orElse(null);
        Assertions.assertEquals("Valledupar", ciudadActualizada.getNombre());
    }

    /**
     * Prueba unitaria para consultar todos los registros de la tabla Ciudad.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo ciudad.sql.
     */
    @Test
    @Sql("classpath:ciudad.sql")
    public void listarCiudad(){
        List<Ciudad> ciudades = ciudadRepo.findAll();
        Assertions.assertEquals(3, ciudades.size());
    }

    /**
     * Prueba unitaria para consultar los usuarios que viven en una determindad ciudad.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo ciudad.sql.
     */
    @Test
    @Sql("classpath:ciudad.sql")
    public void listarUsuarios(){
        List<Usuario> usuarios = ciudadRepo.listarUsuarios("Armenia");
        Assertions.assertEquals(2, usuarios.size());
    }

}
