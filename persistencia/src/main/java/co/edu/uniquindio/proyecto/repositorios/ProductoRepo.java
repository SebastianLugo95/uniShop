package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
public interface ProductoRepo extends JpaRepository<Producto, String> {

}
