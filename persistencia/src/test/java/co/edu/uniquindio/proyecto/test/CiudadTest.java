package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    public void crearCiudad(){
        Ciudad ciudad = new Ciudad("9", "Manizales");

        Ciudad ciudadGuardada = ciudadRepo.save(ciudad);
        Assertions.assertNotNull(ciudadGuardada);
    }

    @Test
    @Sql("classpath:ciudad.sql")
    public void eliminarCiudad(){
        Ciudad ciudad = ciudadRepo.findById("1").orElse(null);
        ciudadRepo.delete(ciudad);

        Ciudad ciudadEliminada = ciudadRepo.findById("1").orElse(null);
        Assertions.assertNull(ciudadEliminada);

    }

    @Test
    @Sql("classpath:ciudad.sql")
    public void actualizarCiudad(){
        Ciudad ciudad = ciudadRepo.findById("1").orElse(null);
        ciudad.setNombre("Valledupar");
        ciudadRepo.save(ciudad);

        Ciudad ciudadActualizada = ciudadRepo.findById("1").orElse(null);
        Assertions.assertEquals("Valledupar", ciudadActualizada.getNombre());
    }

    @Test
    @Sql("classpath:ciudad.sql")
    public void listarCiudad(){
        List<Ciudad> ciudades = ciudadRepo.findAll();
        Assertions.assertEquals(3, ciudades.size());
    }

}
