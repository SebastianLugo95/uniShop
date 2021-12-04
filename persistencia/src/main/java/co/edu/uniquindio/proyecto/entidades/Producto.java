package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto implements Serializable {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @PositiveOrZero
    @Column(nullable = false)
    private Integer unidades;

    @NotBlank(message = "La descripción del producto es obligatoria")
    @Column(nullable = false)
    private String descripcion;

    @Positive
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
    @JoinColumn(nullable = true)
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
    public Producto(Integer codigo, String nombre, Integer unidades, String descripcion, Double precio, LocalDate fechaLimite, Float descuento, Usuario codigoVendedor, Ciudad codigoCiudad) {
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

    public String getImagenPrincipal() {
        if(imagenesProducto != null && !imagenesProducto.isEmpty()) {
            return imagenesProducto.get(0);
        }

        return "default.png";
    }
}
