package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Comentario implements Serializable {

    @Id
    @Column(nullable = false)
    private String codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto codigoProducto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario codigoUsuario;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private String respuesta;

    @Column(nullable = false)
    private LocalDate fechaComentario;

    @Column(nullable = false)
    private Integer calificacion;

    public Comentario(String codigo, Producto codigoProducto, Usuario codigoUsuario, String mensaje, String respuesta, LocalDate fechaComentario, Integer calificacion) {
        this.codigo = codigo;
        this.codigoProducto = codigoProducto;
        this.codigoUsuario = codigoUsuario;
        this.mensaje = mensaje;
        this.respuesta = respuesta;
        this.fechaComentario = fechaComentario;
        this.calificacion = calificacion;
    }
}
