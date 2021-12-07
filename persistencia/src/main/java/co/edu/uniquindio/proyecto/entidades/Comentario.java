package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Entidad Comentario.
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
public class Comentario implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto codigoProducto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario codigoUsuario;

    @NotBlank
    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = true)
    private String respuesta;

    @Column(nullable = false)
    private LocalDate fechaComentario;

    @PositiveOrZero
    @Column(nullable = false)
    private Integer calificacion;

    public String getFechaEstilo(){
        return  fechaComentario.format(DateTimeFormatter.ISO_DATE);
    }


    /**
     * Constructor con los datos obligatorios
     * @param codigoProducto
     * @param codigoUsuario
     * @param mensaje
     * @param respuesta
     * @param fechaComentario
     * @param calificacion
     */
    public Comentario(Producto codigoProducto, Usuario codigoUsuario, String mensaje, String respuesta, LocalDate fechaComentario, Integer calificacion) {
        this.codigoProducto = codigoProducto;
        this.codigoUsuario = codigoUsuario;
        this.mensaje = mensaje;
        this.respuesta = respuesta;
        this.fechaComentario = fechaComentario;
        this.calificacion = calificacion;
    }
}
