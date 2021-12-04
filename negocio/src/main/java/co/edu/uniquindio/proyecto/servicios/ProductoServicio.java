package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface ProductoServicio {

    Producto publicarProducto(Producto producto) throws Exception;

    void actualizarProducto(Producto producto) throws Exception;

    void eliminarProducto(Integer codigo) throws Exception;

    Producto obtenerProducto(Integer codigo) throws Exception;

    List<Producto> listarProductos(Categoria categoria);

    void comentarProducto(String mensaje, Double calificacion, Usuario usuario, Producto producto) throws Exception;

    void guardarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception;

    void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    void comprarProductos(Compra compra);

    List<Producto> buscarProducto(String nombreProducto, String[] filtros);

    List<Producto> listarProductos(String codigoUsuario) throws Exception;

    List<Categoria> listarCategorias();

    Categoria obtenerCategoria(Integer id) throws Exception;
}
