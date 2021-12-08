package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Entidad compra.
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
public class Compra implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario codigoUsuario;

    @Column(nullable = false)
    private LocalDate fechaCompra;

    @Column(nullable = false)
    private String medioPago;

    @OneToMany(mappedBy = "codigoCompra")
    @ToString.Exclude
    private List<DetalleCompra> detallesCompra;

    /**
     * Constructor con los datos obligatorios.
     * @param codigoUsuario Código del usuario que hizo la compra.
     * @param fechaCompra Fecha de la compra.
     * @param medioPago Medio de pago.
     */
    public Compra(Usuario codigoUsuario, LocalDate fechaCompra, String medioPago) {
        this.codigoUsuario = codigoUsuario;
        this.fechaCompra = fechaCompra;
        this.medioPago = medioPago;
    }
}
