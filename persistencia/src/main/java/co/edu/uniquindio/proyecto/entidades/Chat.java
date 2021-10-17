package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Chat implements Serializable {

    @Id
    @Column(nullable = false)
    private String codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuarioComprador;

    @ManyToOne //1 producto tiene muchos chats pero, 1 chat solo puede tener 1 producto.
    @JoinColumn(nullable = false)
    private Producto codigoProducto;

    @OneToMany(mappedBy = "codigoChat")
    @ToString.Exclude
    private List<Mensaje> mensajesChat;

    public Chat(String codigo,  Usuario usuarioComprador, Producto codigoProducto){
        this.codigo = codigo;
        this.usuarioComprador = usuarioComprador;
        this.codigoProducto = codigoProducto;
    }
}
