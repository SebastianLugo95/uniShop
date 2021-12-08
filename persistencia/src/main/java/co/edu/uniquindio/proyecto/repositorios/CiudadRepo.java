package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de la tabla Ciudad.
 * Se encarga de leer o modificar la tabla Ciudad de la base de datos.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    Optional<Ciudad> findByNombre(String nombreCiudad);

    @Query("select u from Ciudad c join c.usuariosCiudad u where c.nombre = :nombre")
    List<Usuario> listarUsuarios(String nombre);


}
