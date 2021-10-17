package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ciudad implements Serializable {

    @Id
    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false, length = 80)
    private String nombre;

    @OneToMany(mappedBy = "codigoCiudad")
    @ToString.Exclude
    private List<Producto> productosCiudad;

    @OneToMany(mappedBy = "codigoCiudad")
    @ToString.Exclude
    private List<Usuario> usuariosCiudad;

    public Ciudad(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

}
