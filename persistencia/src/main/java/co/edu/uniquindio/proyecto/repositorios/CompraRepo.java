package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductosPorUsuario;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de la tabla Compra.
 * Se encarga de leer o modificar la tabla Compra de la base de datos.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@Repository
public interface CompraRepo extends JpaRepository<Compra, String> {

    @Query("select c.codigo, sum(dc.unidades*dc.precioProducto) as Total from DetalleCompra dc join dc.codigoCompra c where c.codigoUsuario.codigo =:codigo group by c")
    List<Object[]> valorTotalComprasUsuario(String codigo);

    @Query("select count (distinct d.codigoProducto) from Compra c join c.detallesCompra d where c.codigoUsuario.codigo = :codigo")
    Long obtenerListaProductosComprados(String codigo);

    @Query("select sum(d.precioProducto*d.unidades) from DetalleCompra  d where d.codigoProducto.codigoVendedor.codigo = :codigo")
    Long calcularTotalVentas(String codigo);

    @Query("select sum(d.precioProducto*d.unidades) from Compra c join c.detallesCompra d where c.codigoUsuario = :codigo")
    Long calcularTotalCompras(String codigo);

    @Query("select c, d from Compra c join c.detallesCompra d where c.codigoUsuario.codigo = :codigo")
    List<Object[]> obtenerComprasUsuario(String codigo);
}
