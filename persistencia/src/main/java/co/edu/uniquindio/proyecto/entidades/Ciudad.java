package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Entidad ciudad.
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
@ToString
public class Ciudad implements Serializable {

    @Id
    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false, length = 80)
    private String nombre;

    @OneToMany(mappedBy = "codigoCiudad")
    @ToString.Exclude
    private List<Producto> productosCiudad;

    @OneToMany(mappedBy = "codigoCiudad")
    @ToString.Exclude
    private List<Usuario> usuariosCiudad;

    /**
     * Constructor con los datos obligatorios.
     * @param codigo Código de la ciudad.
     * @param nombre Nombre de la ciudad.
     */
    public Ciudad(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

}
