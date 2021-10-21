package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Entidad mensaje.
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
public class Mensaje implements Serializable {

    @Id
    @Column(nullable = false)
    private String codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Chat codigoChat;

    @Column(nullable = false)
    private String mensaje;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario emisor;

    @Column(nullable = false)
    private LocalDate fecha;

    /**
     * Constructor con los datos obligatorios.
     * @param codigo Código del mensaje.
     * @param codigoChat Código del chat al que se envía el mensaje.
     * @param mensaje Contenido del mensaje.
     * @param emisor Usuario que envio el mensaje.
     * @param fecha Fecha de envio.
     */
    public Mensaje(String codigo, Chat codigoChat, String mensaje, Usuario emisor, LocalDate fecha) {
        this.codigo = codigo;
        this.codigoChat = codigoChat;
        this.mensaje = mensaje;
        this.emisor = emisor;
        this.fecha = fecha;
    }
}
