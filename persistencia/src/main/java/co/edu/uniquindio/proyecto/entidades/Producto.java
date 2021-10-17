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
