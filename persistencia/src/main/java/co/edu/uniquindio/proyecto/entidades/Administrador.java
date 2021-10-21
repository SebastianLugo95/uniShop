package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Administrador extends Persona {

    public Administrador(String codigo, String nombre, String email, String password) {
        super(codigo, nombre, email, password);
    }

}
