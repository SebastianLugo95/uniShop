package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Component
@ViewScoped
public class DetalleProductoBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Value("#{param['producto']}")
    private String codigoProducto;

    @Getter
    @Setter
    private Producto producto;

    @Getter
    @Setter
    private Comentario nuevoComentario;

    @Getter
    @Setter
    private List<Comentario> comentarios;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar() throws Exception {
        nuevoComentario=new Comentario();

        if(codigoProducto!=null && !codigoProducto.isEmpty()){
            Integer codigo = Integer.parseInt(codigoProducto);
            producto = productoServicio.obtenerProducto(codigo);
            this.comentarios= producto.getComentariosProducto();
        }
    }

    public void crearComentario(){

        try {
            if(usuarioSesion != null) {
                nuevoComentario.setCodigoProducto(producto);
                nuevoComentario.setCodigoUsuario(usuarioSesion);
                nuevoComentario.setFechaComentario(LocalDate.now());
                comentarioServicio.comentarProducto(nuevoComentario);

                this.comentarios.add(nuevoComentario);
                this.nuevoComentario = new Comentario();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer calificacionPromedio() {
        try {
            if(codigoProducto != null && !codigoProducto.isEmpty()) {
                return productoServicio.calcularPromedioCalificacion(Integer.parseInt(codigoProducto));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}