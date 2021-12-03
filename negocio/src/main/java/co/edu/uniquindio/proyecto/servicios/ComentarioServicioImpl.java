package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServicioImpl implements ComentarioServicio {
    @Autowired
    private ComentarioRepo comentarioRepo;

    @Override
    public Comentario comentarProducto(Comentario comentario) throws Exception {
        try {
            return comentarioRepo.save(comentario);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
