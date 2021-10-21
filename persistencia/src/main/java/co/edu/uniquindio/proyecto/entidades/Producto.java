package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Entidad Producto.
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
public class Producto implements Serializable {

    @Id
    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private LocalDate fechaLimite;

    @Column(nullable = true)
    private Float descuento;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario codigoVendedor;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad codigoCiudad;

    @ManyToMany
    @ToString.Exclude
    private List<Categoria> categoriasProducto;

    @ElementCollection
    @ToString.Exclude
    private List<String> imagenesProducto;

    @OneToMany(mappedBy = "codigoProducto")
    @ToString.Exclude
    private List<DetalleCompra> detallesCompra;

    @ManyToMany(mappedBy = "productosFavoritos") //Para añadir a favoritos, el producto debe existir => Favorito depende de Producto.
    @ToString.Exclude
    private List<Usuario> favoritoUsuarios;

    @OneToMany(mappedBy = "codigoProducto")
    @ToString.Exclude
    private List<Comentario> comentariosProducto;

    @OneToMany(mappedBy = "codigoProducto")
    @ToString.Exclude
    private List<Subasta> subastasProducto;

    @OneToMany(mappedBy = "codigoProducto")
    @ToString.Exclude
    private List<Chat> chatsProducto;

    /**
     * Constructor con los datos obligatorios.
     * @param codigo Código del producto.
     * @param nombre Nombre del producto.
     * @param unidades Unidades disponibles.
     * @param descripcion Descripción del producto.
     * @param precio Precio del producto.
     * @param fechaLimite Fecha del producto.
     * @param descuento Descuento del producto.
     * @param codigoVendedor Código del vendedor.
     * @param codigoCiudad Codigo de la ciudad.
     */
    public Producto(String codigo, String nombre, Integer unidades, String descripcion, Double precio, LocalDate fechaLimite, Float descuento, Usuario codigoVendedor, Ciudad codigoCiudad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaLimite = fechaLimite;
        this.descuento = descuento;
        this.codigoVendedor = codigoVendedor;
        this.codigoCiudad = codigoCiudad;
    }
}
