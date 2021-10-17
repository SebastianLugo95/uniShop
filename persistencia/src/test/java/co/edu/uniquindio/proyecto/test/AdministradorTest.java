package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {

    @Autowired
    private AdministradorRepo administradorRepo;

    @Test
    public void registrarTest(){
        Administrador administrador = new Administrador("123", "Pepito", "pepe@gmail.com", "12345");

        Administrador administradorGuardado = administradorRepo.save(administrador);
        Assertions.assertNotNull(administradorGuardado);

    }

    @Test
    @Sql("classpath:administrador.sql")
    public void eliminarTest(){
        administradorRepo.deleteById("123");

        Administrador administradorBuscado = administradorRepo.findById("123").orElse(null);
        Assertions.assertNull(administradorBuscado);

    }

    @Test
    @Sql("classpath:administrador.sql")
    public void actualizarTest(){
        Administrador administradorBuscado = administradorRepo.findById("124").orElse(null);

        administradorBuscado.setEmail("maria_correoNuevo@gmail.com");
        administradorRepo.save(administradorBuscado);

        Administrador administradorGuardado = administradorRepo.findById("124").orElse(null);
        Assertions.assertEquals("maria_correoNuevo@gmail.com", administradorGuardado.getEmail());

    }

    @Test
    @Sql("classpath:administrador.sql")
    public void listarTest(){
        List<Administrador> administradores = administradorRepo.findAll();
        administradores.forEach(administrador -> System.out.println(administrador));

    }
}
