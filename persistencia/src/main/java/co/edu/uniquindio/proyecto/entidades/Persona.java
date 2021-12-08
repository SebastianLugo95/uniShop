package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Entidad Persona.
 *
 * @author Mauricio Martinez Mateus.
 * @author Kevin Orlando Franco Ballejo
 * @author Sebastian Lugo Mateus.
 * @author Stiven Herrera Sierra.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@AllArgsConstructor
@ToString
public class Persona implements Serializable {

    @Id
    @Column(length = 10)
    @EqualsAndHashCode.Include
    private String codigo;

    @NotBlank
    @Column(nullable = false, length = 100)
    @Length(max = 150)
    private String nombre;

    @NotBlank
    @Column(nullable = false)
    @Email
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

}
