package co.edu.uniquindio.proyecto.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ProductosPorUsuario {

    private String cedula;
    private String email;
    private Long registros;

}
