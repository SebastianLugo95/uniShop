package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Notificacion;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class productoRestController {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public List<Producto> listar() {
        return productoServicio.listarTodosProductos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> obtener(@PathVariable("codigo") Integer codigoProducto) {
        try {
            Producto producto = productoServicio.obtenerProducto(codigoProducto);
            return ResponseEntity.status(200).body(producto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Notificacion(e.getMessage()));
        }

    }

    @PostMapping
    public ResponseEntity<Notificacion> crear(@RequestBody Producto producto) {
        try {
            productoServicio.publicarProducto(producto);
            return ResponseEntity.status(200).body(new Notificacion("El producto se creó correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Notificacion(e.getMessage()));
        }

    }

    @PutMapping
    public ResponseEntity<Notificacion> actualizar(@RequestBody Producto producto) {
        try {
            productoServicio.actualizarProducto(producto);
            return ResponseEntity.status(200).body(new Notificacion("El producto se actualizó correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Notificacion(e.getMessage()));
        }

    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Notificacion> eliminar(@PathVariable("codigo") Integer codigoProducto) {
        try {
            productoServicio.eliminarProducto(codigoProducto);
            return ResponseEntity.status(200).body(new Notificacion("El producto se eliminó correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Notificacion(e.getMessage()));
        }
    }

    @GetMapping("/categorias/{codigo}")
    public ResponseEntity<?> obtenerPorCategoria(@PathVariable("codigo") Integer codigo) {
        try {
            Categoria categoria = productoServicio.obtenerCategoria(codigo);
            List<Producto> lista = productoServicio.listarProductosCategoria(categoria);
            return ResponseEntity.status(200).body(lista);
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Notificacion(e.getMessage()));
        }
    }

    @GetMapping("/ciudades/{codigo}")
    public ResponseEntity<?> obtenerPorCiudad(@PathVariable("codigo") Integer codigo) {
        try {
            Ciudad ciudad = productoServicio.obtenerCiudad(codigo);
            List<Producto> lista = productoServicio.listarProductosCiudad(ciudad);
            return ResponseEntity.status(200).body(lista);
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Notificacion(e.getMessage()));
        }
    }

}


