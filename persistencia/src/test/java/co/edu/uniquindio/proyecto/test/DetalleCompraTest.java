package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 *
 * Clase encargarda de las pruebas uitarias para el CRUD del repositorio DetalleCompra.
 *
 * @author Kevin Orlando Franco Ballejo.
 * @author Mauricio Martinez Mateus.
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetalleCompraTest {

    /**
     * Inyección de repositorio ProductoRepo.
     */
    @Autowired
    private ProductoRepo productoRepo;

    /**
     * Inyección de repositorio CompraRepo.
     */
    @Autowired
    private CompraRepo compraRepo;

    /**
     * Inyección de repositorio DetalleCompraRepo.
     */
    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    /**
     * Prueba unitaria para insertar un registro a la tabla DetalleCompra.
     *
     * Primero se debe consultar un producto y una compra para asociarlos al detalleCompra.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo detalleCompra.sql.
     */
    @Test
    @Sql("classpath:detalleCompra.sql")
    public void crearDetalleCompra(){
        Producto producto = productoRepo.findById(1).orElse(null);
        Compra compra = compraRepo.findById("1").orElse(null);
        DetalleCompra detalleCompra =new DetalleCompra("839", compra, producto, 50, 80000.0);

        DetalleCompra detalleCompraGuardada = detalleCompraRepo.save(detalleCompra);
        Assertions.assertNotNull(detalleCompraGuardada);
    }


    /**
     * Prueba unitaria para eliminar un registro por su ID de la tabla DetalleCompra.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo detalleCompra.sql.
     */
    @Test
    @Sql("classpath:detalleCompra.sql")
    public void eliminarDetalleCompra(){
        DetalleCompra detalleCompra = detalleCompraRepo.findById("840").orElse(null);
        detalleCompraRepo.delete(detalleCompra);

            DetalleCompra detalleCompraEliminada = detalleCompraRepo.findById("840").orElse(null);
        Assertions.assertNull(detalleCompraEliminada);
    }

    /**
     * Prueba unitaria para actualizar un registro buscado por su ID en la tabla DetalleCompra.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo detalleCompra.sql.
     */
    @Test
    @Sql("classpath:detalleCompra.sql")
    public void actualizarDetalleCompra(){
        DetalleCompra detalle = detalleCompraRepo.findById("840").orElse(null);
        detalle.setUnidades(25);
        detalleCompraRepo.save(detalle);

        DetalleCompra detalleActualizado = detalleCompraRepo.findById("840").orElse(null);
        Assertions.assertEquals(25, detalleActualizado.getUnidades());
    }

    /**
     * Prueba unitaria para consultar todos los registros de la tabla DetalleCompra.
     *
     * Se integran datos de la prueba con las instrucciones SQL del archivo detalleCompra.sql.
     */
    @Test
    @Sql("classpath:detalleCompra.sql")
    public void listarDetalleCompra(){
        List<DetalleCompra> detallesCompra = detalleCompraRepo.findAll();
        Assertions.assertEquals(3, detallesCompra.size());
    }
}
