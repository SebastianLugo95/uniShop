package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements  ProductoServicio{
    private final ProductoRepo productoRepo;
    private final CategoriaRepo categoriaRepo;
    private final CompraRepo compraRepo;
    private final DetalleCompraRepo detalleCompraRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo, CategoriaRepo categoriaRepo, CompraRepo compraRepo, DetalleCompraRepo detalleCompraRepo) {
        this.productoRepo = productoRepo;
        this.categoriaRepo = categoriaRepo;
        this.compraRepo = compraRepo;
        this.detalleCompraRepo = detalleCompraRepo;
    }

    @Override
    public Producto publicarProducto(Producto producto) throws Exception {
        try{
            return productoRepo.save(producto);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void actualizarProducto(Producto producto) throws Exception {

    }

    @Override
    public void eliminarProducto(Integer codigo) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigo);

        if(producto.isEmpty()){
            throw new Exception("El codigo del producto no existe");
        }

        productoRepo.deleteById(codigo);
    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws Exception {
        return productoRepo.findById(codigo).orElseThrow( () -> new Exception("El codigo del producto no es valido"));
    }

    @Override
    public List<Producto> listarProductos(Categoria categoria) {
        return productoRepo.listarPorCategoria(categoria);
    }

    @Override
    public void comentarProducto(String mensaje, Double calificacion, Usuario usuario, Producto producto) throws Exception {

    }

    @Override
    public void guardarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void comprarProductos(Compra compra) {

    }

    @Override
    public List<Producto> buscarProducto(String nombreProducto, String[] filtros) {
        return productoRepo.buscarProductoNombre(nombreProducto);
    }

    @Override
    public List<Producto> listarProductos(String codigoUsuario) throws Exception {
        return null;
    }

    @Override
    public List<Producto> listarTodosProductos() {
        return productoRepo.findAll();
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public Categoria obtenerCategoria(Integer id) throws Exception {
        return categoriaRepo.findById(id).orElseThrow(() -> new Exception("El id no corresponde a ninguna categoría"));
    }

    @Override
    public Integer calcularPromedioCalificacion(Integer codigo) throws Exception {
        Optional<Producto> productoBuscado = productoRepo.findById(codigo);

        if(productoBuscado.isEmpty()) throw new Exception("El codigo del producto no existe");

        return productoRepo.calcularPromedioCalificacion(codigo);
    }

    @Override
    public Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, String medioPago) throws Exception{
        try {
            Compra c = new Compra();
            c.setFechaCompra(LocalDate.now());
            c.setCodigoUsuario(usuario);
            c.setMedioPago(medioPago);

            Compra compraGuardada = compraRepo.save(c);

            DetalleCompra dc;
            for (ProductoCarrito p : productos){
                dc = new DetalleCompra();
                dc.setCodigoCompra(compraGuardada);
                dc.setPrecioProducto(p.getPrecio());
                dc.setUnidades((p.getUnidades()));
                dc.setCodigoProducto(productoRepo.findById(p.getCodigo()).get());
                detalleCompraRepo.save(dc);
            }
            return compraGuardada;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
