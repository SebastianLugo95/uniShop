package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
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
 * Clase encargada de las pruebas unitarias para el CRUD del repositorio Compra.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    /**
     * Inyección del repositorio UsuarioRepo.
     */
    @Autowired
    private UsuarioRepo usuarioRepo;

    /**
     * Inyección del repositorio CompraRepo.
     */
    @Autowired
    private CompraRepo compraRepo;

    /**
     * Prueba unitaria para insertar un registro a la tabla Compra.
     *
     * Primero se debe consultar un usuario para asociarlo a la compra.
     */
    @Test
    @Sql("classpath:compra.sql")
    public void crearCompra(){
        Usuario usuario = usuarioRepo.findById("123").orElse(null);
        Compra compra = new Compra(usuario, LocalDate.now(), "PSE");

        Compra compraGuardada = compraRepo.save(compra);
        Assertions.assertNotNull(compraGuardada);
    }

    /**
     * Prueba unitaria para eliminar por su ID un registro de la tabla Compra.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo compra.sql.
     */
    @Test
    @Sql("classpath:compra.sql")
    public void eliminarCompra(){
        Compra compra = compraRepo.findById("1").orElse(null);
        compraRepo.delete(compra);

        Compra compraEliminada = compraRepo.findById("1").orElse(null);
        Assertions.assertNull(compraEliminada);
    }

    /**
     * Prueba unitaria para actualizar un registro buscado por su ID en la tabla Compra.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo compra.sql.
     */
    @Test
    @Sql("classpath:compra.sql")
    public void actualizarCompra(){
        Compra compra = compraRepo.findById("1").orElse(null);
        compra.setMedioPago("A Cuotas");
        compraRepo.save(compra);

        Compra compraActualizada = compraRepo.findById("1").orElse(null);
        Assertions.assertEquals("A Cuotas", compraActualizada.getMedioPago());
    }

    /**
     * Prueba unitaria para consultar todos los registros de la tabla Compra.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo compra.sql.
     */
    @Test
    @Sql("classpath:compra.sql")
    public void listarCompras(){
        List<Compra> compras = compraRepo.findAll();
        Assertions.assertEquals(3, compras.size());
    }

    /**
     * Prueba unitaria para consultar todos los registros de la tabla Compra.
     *
     * Se insertan datos de prueba con las instrucciones SQL en el archivo compra.sql.
     */
    @Test
    @Sql("classpath:compra.sql")
    public void listarValorTotalComprasUsuario(){
        List<Object[]> lista = compraRepo.valorTotalComprasUsuario("124");
        System.out.println("lista");
    }

}
