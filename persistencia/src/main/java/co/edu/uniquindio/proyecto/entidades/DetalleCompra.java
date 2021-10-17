package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DetalleCompra implements Serializable {

    @Id
    @Column(nullable = false)
    private String codigo;

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

    public DetalleCompra(String codigo, Compra codigoCompra, Producto codigoProducto, Integer unidades, Double precioProducto) {
        this.codigo = codigo;
        this.codigoCompra = codigoCompra;
        this.codigoProducto = codigoProducto;
        this.unidades = unidades;
        this.precioProducto = precioProducto;
    }
}
