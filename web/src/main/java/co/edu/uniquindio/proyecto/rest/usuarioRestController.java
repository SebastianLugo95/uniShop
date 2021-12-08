package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Notificacion;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class usuarioRestController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> listar(){
        return usuarioServicio.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  obtener(@PathVariable("id") String idUsuario) {
        try {
            Usuario usuario = usuarioServicio.obtenerUsuario(idUsuario);
            return ResponseEntity.status(200).body(usuario);
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Notificacion(e.getMessage()));
        }

    }

    @PostMapping
    public ResponseEntity<Notificacion>  crear(@RequestBody Usuario usuario){
        try {
            usuarioServicio.registrarUsuario(usuario);
            return ResponseEntity.status(200).body(new Notificacion("El usuario se creó correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Notificacion(e.getMessage()));
        }

    }

    @PutMapping
    public ResponseEntity<Notificacion> actualizar(@RequestBody Usuario usuario){
        try {
            usuarioServicio.actualizarUsuario(usuario);
            return ResponseEntity.status(200).body(new Notificacion("El usuario se actualizó correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Notificacion(e.getMessage()));
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Notificacion>  eliminar(@PathVariable("id") String id) {
        try {
            usuarioServicio.eliminarUsuario(id);
            return ResponseEntity.status(200).body(new Notificacion("El usuario se eliminó correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Notificacion(e.getMessage()));
        }
    }
}
