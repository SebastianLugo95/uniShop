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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaTest {

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Test
    public void crearCategoria(){
        Categoria categoria = new Categoria("834", "Aseo");

        Categoria categoriaGuardada = categoriaRepo.save(categoria);
        Assertions.assertNotNull(categoriaGuardada);
    }

    @Test
    @Sql("classpath:categoria.sql")
    public void eliminarCategoria(){
        Categoria categoria = categoriaRepo.findById("1").orElse(null);
        categoriaRepo.delete(categoria);

        Categoria categoriaEliminada = categoriaRepo.findById("1").orElse(null);
        Assertions.assertNull(categoriaEliminada);
    }

    @Test
    @Sql("classpath:categoria.sql")
    public void actualizarCategoria(){
        Categoria categoria = categoriaRepo.findById("1").orElse(null);
        categoria.setNombre("Mascotas");
        categoriaRepo.save(categoria);

        Categoria categoriaActualizada = categoriaRepo.findById("1").orElse(null);
        Assertions.assertEquals("Mascotas", categoriaActualizada.getNombre());
    }

    @Test
    @Sql("classpath:categoria.sql")
    public void listarCategorias(){
        List<Categoria> categorias = categoriaRepo.findAll();
        Assertions.assertEquals(3, categorias.size());

    }

}
