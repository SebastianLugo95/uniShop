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
public class Categoria implements Serializable {

    @Id
    @Column(nullable = false, length = 10)
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "categoriasProducto")
    private List<Producto> productos;

    public Categoria(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}
