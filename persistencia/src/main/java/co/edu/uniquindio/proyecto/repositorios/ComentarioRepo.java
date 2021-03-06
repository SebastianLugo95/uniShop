package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *Repositorio de la tabla Comentario.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */

/**
 * Se encarga de leer y modificar la tabla Comentario de la base de datos.
 */
@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, String> {

    @Query("select c from Comentario c where c.calificacion between  :menor and :mayor")
    List<Comentario> listarComentariosRango(int menor, int mayor);

}
