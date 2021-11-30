package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de la tabla Categoria.
 * Se encarga de leer o modificar la tabla Categoria de la base de datos.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, String> {

    @Query("select c.nombre, avg(cm.calificacion) as promedio from Categoria c join Producto p join Comentario cm group by c order by promedio")
    List<Object[]> listarCategoriasPorCalificacion();

    @Query ("select c.codigo, max(dc.codigoProducto) as maxVendido from  Compra c join c.detallesCompra dc join dc.codigoProducto.categoriasProducto ca where ca.codigo =:codigo")
    List<Object[]> productoMasVendidoPorCategoria(String codigo);
}
