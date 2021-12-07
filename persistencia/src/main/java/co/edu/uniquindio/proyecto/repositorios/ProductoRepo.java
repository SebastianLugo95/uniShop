package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.dto.ProductosPorUsuario;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 *Repositorio de la tabla Producto.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */

/**
 * Se encarga de leer y modificar la tabla Producto de la base de datos.
 */
@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    @Query("select c from Comentario c where c.codigoProducto.codigo =:codigo and c.respuesta is null ")
    List<Comentario> listarComentariosSinRespuesta(Integer codigo);

    Page<Producto> findAll(Pageable paginador);

    @Query("select p.codigoVendedor.nombre from Producto p where p.codigo = :id")
    String obtenerNombreVendedor(String id);

    @Query("select c from Producto p join p.comentariosProducto c where p.codigo = :codigo")
    List<Comentario> obtenerComentarios(Integer codigo);

    @Query("select p, c from Producto p left join p.comentariosProducto c")
    List<Object[]> listarProductosYComentarios();

    @Query("select distinct c.codigoUsuario from Producto p join p.comentariosProducto c where p.codigo = :id")
    List<Usuario> listarUsuariosComentarios(Integer id);

    @Query("select new co.edu.uniquindio.proyecto.dto.ProductoValido(p.nombre, p.descripcion, p.precio, p.codigoCiudad) from Producto p where :fechaActual < p.fechaLimite")
    List<ProductoValido> listarProductosValidos(LocalDateTime fechaActual);

    @Query("select c, count(p) from Producto  p join p.categoriasProducto c group by c")
    List<Object[]> obtenerTotalProductosPorCategoria();

    @Query("select p from Producto  p where p.comentariosProducto is empty ")
    List<Producto> obtenerProductosSinComentarios();

    @Query("select  p from Producto  p where p.nombre like concat('%', :nombre, '%') ")
    List<Producto> buscarProductoNombre(String nombre);

    @Query("select new co.edu.uniquindio.proyecto.dto.ProductosPorUsuario(p.codigoVendedor.codigo, p.codigoVendedor.email, count(p)) from Producto p group by  p.codigoVendedor")
    List<ProductosPorUsuario> obtenerProductosEnVenta();

    @Query("select  c, count(p) as total from Producto  p join p.categoriasProducto c group by c order by total desc")
    List<Producto> obtenerCategoriaMasUsada();

    @Query("select avg(c.calificacion) from Producto p join p.comentariosProducto c where p.codigo = :codigo")
    Double obtenerPromedioCalificaciones(Integer codigo);

    @Query("select p from Producto p where :categoria member of p.categoriasProducto")
    List<Producto> listarPorCategoria(Categoria categoria);

    @Query("select avg(c.calificacion) from Comentario c where c.codigoProducto.codigo = :codigo")
    Integer calcularPromedioCalificacion(Integer codigo);
}
