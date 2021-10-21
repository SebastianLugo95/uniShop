package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.expression.spel.ast.NullLiteral;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Entidad SubastaUsuario.
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
public class SubastaUsuario implements Serializable {

    @Id
    @Column(nullable = false)
    private String codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Subasta codigoSubasta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario codigoUsuario;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private LocalDate fechaSubasta;

    /**
     * Constructor con los datos obligatorios.
     * @param codigo Codigo de la subasta del usuario.
     * @param codigoSubasta Codigo de la subasta.
     * @param codigoUsuario Codigo del usuario que hizo la subasta.
     * @param valor Valor de la subasta.
     * @param fechaSubasta Fecha de la subasta.
     */
    public SubastaUsuario(String codigo, Subasta codigoSubasta, Usuario codigoUsuario, Double valor, LocalDate fechaSubasta) {
        this.codigo = codigo;
        this.codigoSubasta = codigoSubasta;
        this.codigoUsuario = codigoUsuario;
        this.valor = valor;
        this.fechaSubasta = fechaSubasta;
    }
}
