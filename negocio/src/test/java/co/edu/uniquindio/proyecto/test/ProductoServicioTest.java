package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ProductoServicioTest {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    public void obtenerProductoTest(){
        try {
            Usuario vendedor = usuarioServicio.obtenerUsuario("123");
            Ciudad ciudad = ciudadRepo.findById(1).orElse(null);
            Producto producto = new Producto(786, "Computador portatil ASUS X407S", 100, "Portatil para trabajos de hogar / offimatica xd", 1500000.0, LocalDate.now(), 30.5f, vendedor, ciudad);
            Producto publicado = productoServicio.publicarProducto(producto);
            Assertions.assertNotNull(publicado);
        }catch(Exception e){
            Assertions.assertTrue(false, e.getMessage());
        }
    }



}
