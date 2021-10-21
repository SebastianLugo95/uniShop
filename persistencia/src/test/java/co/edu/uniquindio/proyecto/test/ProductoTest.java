package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * Clase encargarda de las pruebas uitarias para el CRUD del repositorio Producto.
 *
 * @author Kevin Orlando Franco Ballejo.
 * @author Mauricio Martinez Mateus.
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {

    /**
     * Inyección de repositorio ProductoRepo.
     */
    @Autowired
    private ProductoRepo productoRepo;

    /**
     * Inyección de repositorio UsuarioRepo.
     */
    @Autowired
    private UsuarioRepo usuarioRepo;

    /**
     * Inyección de repositorio CiudadRepo.
     */
    @Autowired
    private CiudadRepo ciudadRepo;

    /**
     * Prueba unitaria para insertar un registro a la tabla Producto.
     *
     * Primero se debe consultar una ciudad y un usuario para asociarlos al producto.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo producto.sql.
     */
    @Test
    @Sql("classpath:productos.sql")
    public void crearProducto() {
        Ciudad ciudad = ciudadRepo.findById("1").orElse(null);
        Usuario usuario = usuarioRepo.findById("1").orElse(null);
        Producto producto = new Producto("786", "Computador portatil ASUS X407S", 100, "Portatil para trabajos de hogar / offimatica xd", 1500000.0, LocalDate.now(), 30.5f, usuario, ciudad);

        Producto productoGuardado = productoRepo.save(producto);
        Assertions.assertNotNull(productoGuardado);
    }

    /**
     * Prueba unitaria para eliminar un registro por su ID de la tabla Producto.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo producto.sql.
     */
    @Test
    @Sql("classpath:productos.sql")
    public void eliminarProducto(){
        Producto producto = productoRepo.findById("2").orElse(null);
        productoRepo.delete(producto);

        Producto productoEliminado = productoRepo.findById("2").orElse(null);
        Assertions.assertNull(productoEliminado);

    }

    /**
     * Prueba unitaria para actualizar un registro buscado por su ID en la tabla Producto.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo producto.sql.
     */
    @Test
    @Sql("classpath:productos.sql")
    public void actualizarProducto(){
        Producto producto = productoRepo.findById("2").orElse(null);
        producto.setNombre("Nintendo Switch");
        productoRepo.save(producto);

        Producto productoActualizado = productoRepo.findById("2").orElse(null);
        Assertions.assertEquals("Nintendo Switch", productoActualizado.getNombre());
    }

    /**
     * Prueba unitaria para consultar todos los registros de la tabla Producto.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo producto.sql.
     */
    @Test
    @Sql("classpath:productos.sql")
    public void listarProductos(){
        List<Producto> productos = productoRepo.findAll();
        Assertions.assertEquals(3, productos.size());
    }













}
