package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de la tabla Subasta.
 * Se encarga de leer o modificar la tabla Subasta de la base de datos.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@Repository
public interface SubastaRepo extends JpaRepository<Subasta, String> {

    @Query("select c.nombre, count(s.codigoProducto) from Subasta s join s.codigoProducto.categoriasProducto c group by c")
    List<Object[]> productosEnSubastaPorCategoria();

    @Query("select max(d.valor) from Subasta s join s.subastasUsuario d where s.codigo = :codigo")
    Double obtenerValorMasAlto(String codigo);

    @Query("select s from Subasta s where current_timestamp < s.fechaLimite")
    List<Subasta> ListarSubastasDisponibles();


}
