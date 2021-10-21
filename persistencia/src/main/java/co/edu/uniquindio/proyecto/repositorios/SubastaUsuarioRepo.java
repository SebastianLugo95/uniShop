package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *Repositorio de la tabla SubastaUsuario.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */

/**
 * Se encarga de leer y modificar la tabla SubastaUsuario de la base de datos.
 */
@Repository
public interface SubastaUsuarioRepo extends JpaRepository<SubastaUsuario, String> {

}
