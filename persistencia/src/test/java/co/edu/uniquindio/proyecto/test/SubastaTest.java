package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.hibernate.bytecode.internal.bytebuddy.BytecodeProviderImpl;
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
public class SubastaTest {

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private SubastaRepo subastaRepo;

    @Test
    @Sql("classpath:subasta.sql")
    public void crearSubasta(){
        Producto producto = productoRepo.findById("1").orElse(null);
        Subasta subasta = new Subasta("273", producto, LocalDate.now());

        Subasta subastaGuardada= subastaRepo.save(subasta);
        Assertions.assertNotNull(subastaGuardada);
    }

    @Test
    @Sql("classpath:subasta.sql")
    public void eliminarSubasta(){
        Subasta subasta = subastaRepo.findById("245").orElse(null);
        subastaRepo.delete(subasta);

        Subasta subastaEliminada = subastaRepo.findById("245").orElse(null);
        Assertions.assertNull(subastaEliminada);
    }

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

    @Test
    @Sql("classpath:subasta.sql")
    public void listarSubasta(){
        List<Subasta> subastas = subastaRepo.findAll();
        Assertions.assertEquals(3, subastas.size());

    }
}
