package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.exception.DataException;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Entidad Subasta.
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
public class Subasta implements Serializable {

    @Id
    private String codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto codigoProducto;

    @Column(nullable = false)
    private LocalDate fechaLimite;

    @OneToMany(mappedBy = "codigoSubasta")
    @ToString.Exclude
    private List<SubastaUsuario> subastasUsuario;

    /**
     * Constructor con los parametros obligatorios
     * @param codigo
     * @param codigoProducto
     * @param fechaLimite
     */
    public Subasta(String codigo, Producto codigoProducto, LocalDate fechaLimite) {
        this.codigo = codigo;
        this.codigoProducto = codigoProducto;
        this.fechaLimite = fechaLimite;
    }
}
