package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class MisProductosBean implements Serializable {
    @Getter @Setter
    private List<Producto> misProductos;

    @Autowired
    private ProductoServicio productosServicio;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar() {
        if(usuarioSesion != null) {
            try {
                this.misProductos = productosServicio.listarProductos(usuarioSesion.getCodigo());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String irADetalle(Integer id) {
        return "/detalle_producto?faces-redirect=true&amp;producto="+id;
    }
}
