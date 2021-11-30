package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *Repositorio de la tabla DetalleCompra.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */

/**
 * Se encarga de leer y modificar la tabla DetalleCompra de la base de datos.
 */
@Repository
public interface DetalleCompraRepo extends JpaRepository<DetalleCompra, String> {

}
