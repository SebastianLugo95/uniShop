package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministradorServicioImpl implements AdministradorServicio {

    @Autowired
    private AdministradorRepo administradorRepo;

    @Override
    public Boolean esAdmin(String email) {
        Optional<Administrador> adminBuscado = administradorRepo.findByEmail(email);

        return adminBuscado.isPresent();
    }

    @Override
    public Administrador iniciarSesion(String email, String password) throws Exception {
        return administradorRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new Exception("Los datos de autenticación son incorrectos"));
    }
}
