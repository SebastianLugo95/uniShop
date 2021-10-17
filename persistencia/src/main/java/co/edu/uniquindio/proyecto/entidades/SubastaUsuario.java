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

    public SubastaUsuario(String codigo, Subasta codigoSubasta, Usuario codigoUsuario, Double valor, LocalDate fechaSubasta) {
        this.codigo = codigo;
        this.codigoSubasta = codigoSubasta;
        this.codigoUsuario = codigoUsuario;
        this.valor = valor;
        this.fechaSubasta = fechaSubasta;
    }
}
