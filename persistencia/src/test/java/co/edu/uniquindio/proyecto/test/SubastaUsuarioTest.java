package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaUsuarioRepo;
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
public class SubastaUsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private SubastaRepo subastaRepo;

    @Autowired
    private SubastaUsuarioRepo subastaUsuarioRepo;

    @Test
    @Sql("classpath:subastaUsuario.sql")
    public void crearSubastaUsuario() {
        Usuario usuario = usuarioRepo.findById("123").orElse(null);
        Subasta subasta = subastaRepo.findById("245").orElse(null);
        SubastaUsuario subastaUsuario = new SubastaUsuario("578", subasta, usuario, 30000.0, LocalDate.now());

        SubastaUsuario subastaUsuarioGuardada = subastaUsuarioRepo.save(subastaUsuario);
        Assertions.assertNotNull(subastaUsuarioGuardada);
    }

    @Test
    @Sql("classpath:subastaUsuario.sql")
    public void eliminarSubastaUsuario() {
        SubastaUsuario subastaUsuario = subastaUsuarioRepo.findById("784").orElse(null);
        subastaUsuarioRepo.delete(subastaUsuario);

        SubastaUsuario subastaUsuarioEliminado = subastaUsuarioRepo.findById("784").orElse(null);
        Assertions.assertNull(subastaUsuarioEliminado);
    }

    @Test
    @Sql("classpath:subastaUsuario.sql")
    public void actualizarSubastaUsuario() {
        SubastaUsuario subastaUsuario = subastaUsuarioRepo.findById("784").orElse(null);
        subastaUsuario.setValor(50000.0);
        subastaUsuarioRepo.save(subastaUsuario);

        SubastaUsuario subastaUsuarioActualizado = subastaUsuarioRepo.findById("784").orElse(null);
        Assertions.assertEquals(50000.0, subastaUsuarioActualizado.getValor());

    }

    @Test
    @Sql("classpath:subastaUsuario.sql")
    public void listarSusbastaUsuario() {
        List<SubastaUsuario> subastasUsuario = subastaUsuarioRepo.findAll();
        Assertions.assertEquals(3, subastasUsuario.size());

    }

}


