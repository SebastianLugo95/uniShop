package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CompraRepo compraRepo;

    @Test
    @Sql("classpath:compra.sql")
    public void crearCompra(){
        Usuario usuario = usuarioRepo.findById("123").orElse(null);
        Compra compra = new Compra("740", usuario, LocalDate.now(), "PSE");

        Compra compraGuardada = compraRepo.save(compra);
        Assertions.assertNotNull(compraGuardada);
    }

    @Test
    @Sql("classpath:compra.sql")
    public void eliminarCompra(){
        Compra compra = compraRepo.findById("1").orElse(null);
        compraRepo.delete(compra);

        Compra compraEliminada = compraRepo.findById("1").orElse(null);
        Assertions.assertNull(compraEliminada);
    }

    @Test
    @Sql("classpath:compra.sql")
    public void actualizarCompra(){
        Compra compra = compraRepo.findById("1").orElse(null);
        compra.setMedioPago("A Cuotas");
        compraRepo.save(compra);

        Compra compraActualizada = compraRepo.findById("1").orElse(null);
        Assertions.assertEquals("A Cuotas", compraActualizada.getMedioPago());
    }

    @Test
    @Sql("classpath:compra.sql")
    public void listarCompras(){
        List<Compra> compras = compraRepo.findAll();
        Assertions.assertEquals(3, compras.size());
    }

}
