package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Compra implements Serializable {

    @Id
    @Column(nullable = false)
    private String codigo;

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

    public Compra(String codigo, Usuario codigoUsuario, LocalDate fechaCompra, String medioPago) {
        this.codigo = codigo;
        this.codigoUsuario = codigoUsuario;
        this.fechaCompra = fechaCompra;
        this.medioPago = medioPago;
    }
}
