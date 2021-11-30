package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase encargada de las pruebas unitarias para el CRUD del repositorio Subasta.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubastaTest {

    /**
     * Inyección del repositorio ProductoRepo.
     */
    @Autowired
    private ProductoRepo productoRepo;

    /**
     * Inyección del repositorio SubastaRepo.
     */
    @Autowired
    private SubastaRepo subastaRepo;

    /**
     * Prueba unitaria para insertar un registro a la tabla Subasta.
     *
     * Primero se debe consultar un producto para asociarlo a la subasta.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo subasta.sql
     */
    @Test
    @Sql("classpath:subasta.sql")
    public void crearSubasta(){
        Producto producto = productoRepo.findById("1").orElse(null);
        Subasta subasta = new Subasta("273", producto, LocalDate.now());

        Subasta subastaGuardada= subastaRepo.save(subasta);
        Assertions.assertNotNull(subastaGuardada);
    }

    /**
     * Prueba unitaria para eliminar por su ID un registro de la tabla Subasta.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo subasta.sql.
     */
    @Test
    @Sql("classpath:subasta.sql")
    public void eliminarSubasta(){
        Subasta subasta = subastaRepo.findById("245").orElse(null);
        subastaRepo.delete(subasta);

        Subasta subastaEliminada = subastaRepo.findById("245").orElse(null);
        Assertions.assertNull(subastaEliminada);
    }

    /**
     * Prueba unitaria para actualizar un registro buscado por su ID en la tabla Subasta.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo subasta.sql.
     */
    @Test
    @Sql("classpath:subasta.sql")
    public void actualizarSubasta(){
        Subasta subasta = subastaRepo.findById("245").orElse(null);
        LocalDate fecha = LocalDate.now();
        subasta.setFechaLimite(fecha);
        subastaRepo.save(subasta);

        Subasta subastaActualizada = subastaRepo.findById("245").orElse(null);
        Assertions.assertEquals(fecha, subastaActualizada.getFechaLimite());
    }

    /**
     * Prueba unitaria para consultar todos los registros de la tabla Subasta.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo subasta.sql.
     */
    @Test
    @Sql("classpath:subasta.sql")
    public void listarSubasta(){
        List<Subasta> subastas = subastaRepo.findAll();
        Assertions.assertEquals(3, subastas.size());

    }

    /**
     * Prueba unitaria para listar los productos de una subasta por categoria.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo subasta.sql.
     */
    @Test
    @Sql("classpath:subasta.sql")
    public void listarProductosSubastaPorCategoria(){
        List<Object[]> lista = subastaRepo.productosEnSubastaPorCategoria();
        System.out.println(lista);
    }
}
