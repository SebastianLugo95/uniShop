package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Entity;

/**
 * Entidad Administrador.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Administrador extends Persona {

    /**
     * Constructor con los datos obligatorios
     * @param codigo
     * @param nombre
     * @param email
     * @param password
     */
    public Administrador(String codigo, String nombre, String email, String password) {
        super(codigo, nombre, email, password);
    }

}
