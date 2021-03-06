package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.AdministradorServicioImpl;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private boolean admin;

    @Getter @Setter
    private String email,password;

    @Getter @Setter
    Usuario usuarioSesion;

    @Getter @Setter
    Administrador adminSesion;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private AdministradorServicioImpl administradorServicio;

    @Getter
    @Setter
    private ArrayList<ProductoCarrito> productosCarrito;

    @Getter
    @Setter
    private Double subTotal;

    @Autowired
    private ProductoServicio productoServicio;


    @PostConstruct
    public void inicializador(){
        this.subTotal = 0.0;
        this.productosCarrito = new ArrayList<>();
        admin = false;
    }

    public String iniciarSesion(){
        if(!email.isEmpty()&&!password.isEmpty()){
            if(esAdmin()) {
                loguearAdmin();
            } else {
                try {
                    usuarioSesion = usuarioServicio.iniciarSesion(email, password);
                    autenticado = true;
                } catch (Exception e) {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                    FacesContext.getCurrentInstance().addMessage("login-bean", fm);
                }
            }
            return "/index?faces-redirect=true";
        }
        return null;
    }

    private boolean esAdmin() {
        return administradorServicio.esAdmin(email);
    }

    private void loguearAdmin() {
        try {
            adminSesion = administradorServicio.iniciarSesion(email, password);
            admin = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String recuperarConstrasena(){
        if(!email.isEmpty()){
            try {
                usuarioServicio.recuperarContrasena(email);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Correo enviado satisfactoriamente");
                FacesContext.getCurrentInstance().addMessage("recuperar-bean", fm);
            }catch (Exception e){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("recuperar-bean", fm);
            }
        }
        return "";
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public void agregarAlCarrito(Integer id, Double precio, String nombre, String imagen){
        ProductoCarrito pc = new ProductoCarrito(id, nombre, imagen, precio, 1);
        if(!productosCarrito.contains(pc)){
            productosCarrito.add(pc);
            subTotal += precio;

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto agregado al carrito");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        }

    }

    public void eliminarDelCarrito(int indice){
        subTotal -= productosCarrito.get(indice).getPrecio();
        productosCarrito.remove(indice);
    }

    public void actualizarSubtotal(){
        subTotal = 0.0;
        for (ProductoCarrito p : productosCarrito){
            subTotal += p.getPrecio()*p.getUnidades();
        }

    }

    public void comprar(){
        if(usuarioSesion!= null && !productosCarrito.isEmpty()){
            try {
                productoServicio.comprarProductos(usuarioSesion, productosCarrito, "PSE");
                productosCarrito.clear();
                subTotal = 0.0;
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra realizada satisfactoriamente");
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }catch (Exception e){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }

        }

    }

    public boolean estaLogueado() {
        return admin || autenticado;
    }
}