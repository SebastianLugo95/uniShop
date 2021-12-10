package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;

public interface AdministradorServicio {

    Boolean esAdmin(String email);

    Administrador iniciarSesion(String email, String password) throws Exception;
}
