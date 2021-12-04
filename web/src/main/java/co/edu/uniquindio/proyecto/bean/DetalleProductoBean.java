package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@ViewScoped
public class DetalleProductoBean {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

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

<<<<<<< HEAD
            nuevoComentario.setProducto(producto);
            nuevoComentario.setUsuario( usuarioServicio.obtenerUsuario(12));
            productoServicio.comentarProducto(nuevoComentario);
=======
            nuevoComentario.setCodigoProducto(producto);
            nuevoComentario.setCodigoUsuario( usuarioServicio.obtenerUsuario("1"));
            comentarioServicio.comentarProducto(nuevoComentario);
>>>>>>> 795f2b4bec5ec86e3766c75a708a7726d9f7f534
            this.comentarios.add(nuevoComentario);
            this.nuevoComentario = new Comentario();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}