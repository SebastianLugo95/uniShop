package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Entidad Usuario.
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
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> telefonos;

    @Column(nullable = false, unique = true)
    private String username;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Ciudad codigoCiudad;

    @OneToMany(mappedBy = "codigoVendedor")
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> productosVenta;

    @OneToMany(mappedBy = "codigoUsuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "codigoUsuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Compra> compras;

    @OneToMany(mappedBy = "codigoUsuario")
    @ToString.Exclude
    @JsonIgnore
    private List<SubastaUsuario> subastasUsuario;

    @OneToMany(mappedBy = "usuarioComprador")
    @ToString.Exclude
    @JsonIgnore
    private List<Chat> chats;

    //@OneToMany(mappedBy = "usuario")
    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> productosFavoritos;

    /**
     * Constructor con los parametros obligatorios
     * @param codigo
     * @param nombre
     * @param email
     * @param password
     * @param username
     * @param codigoCiudad
     */
    public Usuario(String codigo, String nombre, String email, String password, String username, Ciudad codigoCiudad) {
        super(codigo, nombre, email, password);
        this.username = username;
        this.codigoCiudad= codigoCiudad;
    }
}
