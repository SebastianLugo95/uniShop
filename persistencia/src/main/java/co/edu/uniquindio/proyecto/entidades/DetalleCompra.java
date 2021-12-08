package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entidad DetalleCompra.
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
public class DetalleCompra implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Compra codigoCompra;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto codigoProducto;

    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false)
    private Double precioProducto;

    /**
     * Constructor con los datos obligatorios
     * @param codigoCompra
     * @param codigoProducto
     * @param unidades
     * @param precioProducto
     */
    public DetalleCompra(Compra codigoCompra, Producto codigoProducto, Integer unidades, Double precioProducto) {
        this.codigoCompra = codigoCompra;
        this.codigoProducto = codigoProducto;
        this.unidades = unidades;
        this.precioProducto = precioProducto;
    }
}
