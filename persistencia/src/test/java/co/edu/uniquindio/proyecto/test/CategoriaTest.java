package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 * Clase encargada de las pruebas unitarias para el CRUD del repositorio Categoria.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaTest {

    /**
     * Inyección del repositorio CategoriaRepo.
     */
    @Autowired
    private CategoriaRepo categoriaRepo;

    /**
     * Prueba unitaria para insertar un registro a la tabla Categoría.
     */
    @Test
    public void crearCategoria(){
        Categoria categoria = new Categoria("834", "Aseo");

        Categoria categoriaGuardada = categoriaRepo.save(categoria);
        Assertions.assertNotNull(categoriaGuardada);
    }

    /**
     * Prueba unitaria para eliminar por su ID un registro de la tabla Categoria.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo categoria.sql.
     */
    @Test
    @Sql("classpath:categoria.sql")
    public void eliminarCategoria(){
        Categoria categoria = categoriaRepo.findById("1").orElse(null);
        categoriaRepo.delete(categoria);

        Categoria categoriaEliminada = categoriaRepo.findById("1").orElse(null);
        Assertions.assertNull(categoriaEliminada);
    }

    /**
     * Prueba unitaria para actualizar un registro buscado por su ID en la tabla Categoria.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo categoria.sql.
     */
    @Test
    @Sql("classpath:categoria.sql")
    public void actualizarCategoria(){
        Categoria categoria = categoriaRepo.findById("1").orElse(null);
        categoria.setNombre("Mascotas");
        categoriaRepo.save(categoria);

        Categoria categoriaActualizada = categoriaRepo.findById("1").orElse(null);
        Assertions.assertEquals("Mascotas", categoriaActualizada.getNombre());
    }

    /**
     * Prueba unitaria para consultar todos los registros de la tabla Categoria.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo categoria.sql.
     */
    @Test
    @Sql("classpath:categoria.sql")
    public void listarCategorias(){
        List<Categoria> categorias = categoriaRepo.findAll();
        Assertions.assertEquals(3, categorias.size());

    }

}
