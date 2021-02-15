package com.lookextreme.controller;

import com.lookextreme.Dao.EstilistaFacadeLocal;
import com.lookextreme.Dao.ProductosFacadeLocal;
import com.lookextreme.Dao.SalidaPorConsumoFacadeLocal;
import com.lookextreme.Dao.SalidaVentaFacadeLocal;
import com.lookextreme.Dao.UsuarioFacadeLocal;
import com.lookextreme.model.Estilista;
import com.lookextreme.model.Productos;
import com.lookextreme.model.Roles;
import com.lookextreme.model.SalidaPorConsumo;
import com.lookextreme.model.SalidaVenta;
import com.lookextreme.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@Named
@ViewScoped
public class ProductoController implements Serializable {

    @EJB
    private ProductosFacadeLocal prodEJB;
    private List<Productos> productosList;
    private Productos productos;
    private int salida;
    private int entrada;
    private int total;
    private String text;
    private Date fecha;
    private String text2;
    private int salidaV;

    @EJB
    private SalidaPorConsumoFacadeLocal salidaConsumoEJB;
    private SalidaPorConsumo salidaPorConsumo;
    private List<SalidaPorConsumo> listaSalidaFechas;

    @EJB
    private SalidaVentaFacadeLocal salidaVentaEJB;
    private SalidaVenta salidaVenta;
    private List<SalidaVenta> listaSalidaVenta;

    @EJB
    private UsuarioFacadeLocal EJBusuario;
    private Usuario usuario;

    @EJB
    private EstilistaFacadeLocal EJBestilista;
    private Estilista estilista;
    private List<SelectItem> estilistaListItem;

    public List<SalidaPorConsumo> getListaSalidaFechas() {
        return listaSalidaFechas;
    }

    public void setListaSalidaFechas(List<SalidaPorConsumo> listaSalidaFechas) {
        this.listaSalidaFechas = listaSalidaFechas;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public int getSalidaV() {
        return salidaV;
    }

    public void setSalidaV(int salidaV) {
        this.salidaV = salidaV;
    }

    public List<SalidaVenta> getListaSalidaVenta() {
        return listaSalidaVenta;
    }

    public void setListaSalidaVenta(List<SalidaVenta> listaSalidaVenta) {
        this.listaSalidaVenta = listaSalidaVenta;
    }
    
    

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estilista getEstilista() {
        return estilista;
    }

    public void setEstilista(Estilista estilista) {
        this.estilista = estilista;
    }

    public List<SelectItem> getEstilistaListItem() {
        return estilistaListItem;
    }

    public void setEstilistaListItem(List<SelectItem> estilistaListItem) {
        this.estilistaListItem = estilistaListItem;
    }

    public SalidaPorConsumo getSalidaPorConsumo() {
        return salidaPorConsumo;
    }

    public void setSalidaPorConsumo(SalidaPorConsumo salidaPorConsumo) {
        this.salidaPorConsumo = salidaPorConsumo;
    }

    public SalidaVenta getSalidaVenta() {
        return salidaVenta;
    }

    public void setSalidaVenta(SalidaVenta salidaVenta) {
        this.salidaVenta = salidaVenta;
    }

    public int getSalida() {
        return salida;
    }

    public void setSalida(int salida) {
        this.salida = salida;
    }

    public int getEntrada() {
        return entrada;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    public Productos getProducto() {
        return productos;
    }

    public void setProducto(Productos productos) {
        this.productos = productos;
    }

    @PostConstruct
    public void init() {
        //productosList = prodEJB.findAll();
        //prodList = nomEJB.findAll();
        productos = new Productos();
        salidaPorConsumo = new SalidaPorConsumo();
        salidaVenta = new SalidaVenta();
        usuario = new Usuario();
        estilista = new Estilista();
        productosList = this.prodEJB.findAll();
        listaSalidaVenta = this.salidaVentaEJB.findAll();
        salidaPorConsumo.setFechaSalidaConsumo(new Date());
        salidaVenta.setFechaSalidaVenta(new Date());
        listarEstilistas();
        FacesContext context = FacesContext.getCurrentInstance();
        ListarProductos();
    }

    public void ListarProductos() {
        try {
            productosList = new ArrayList();
            productosList = prodEJB.findAll();
        } catch (Exception e) {
            System.out.println("Error CONTROLLER CONSULTA");
        }
    }

    public void eliminarProducto(Productos pro) {
        System.out.println("Producto Eliminado ");

        try {
            prodEJB.remove(pro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "se elimino el producto con exito!"));
        } catch (Exception e) {
            System.out.println("Erro al eliminar el producto");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "No se pudo eliminar el producto"));

        }
    }

    public void cargarProductos(Productos productoss) {
        try {
            this.productos = productoss;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    public void actulizarProducto() {
        System.out.println("servicios actualizados");
        try {
            this.prodEJB.edit(productos);
        } catch (Exception e) {
            System.out.println("error al actualizar servicios" + e.getMessage());
        }
    }

    public void salidaDeProductos() {
        Integer resultado;
        resultado = null;
        try {
            if (salida <= productos.getCantidad()) {
                resultado = productos.getCantidad() - salida;
                text = Integer.toString(total = productos.getPrecio() * salida);
                productos.setCantidad(resultado);
                prodEJB.edit(productos);
                registrarSalida(salida);
                System.out.println("resultado operacion: " + resultado);
                System.out.println("total de productos salidos precio: " + text);
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('wdialog').show();");
            } else {
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('wdialog1').show();");
                System.out.println("el dato ingresado es mayor al que esta en el sistema");
            }

        } catch (ELException | NullPointerException e) {
            System.out.println(e.getMessage());

        }

    }

    public void registrarSalida(int result) {
        
        try {
            salidaPorConsumo.setCantidad(result);
            salidaPorConsumo.setEstado("inexistente");
            salidaPorConsumo.setProductosidCodigo(productos);
            salidaPorConsumo.setEstilistausuarioidUsuario(estilista);
            salidaConsumoEJB.create(salidaPorConsumo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void actionAgregarProducto() {
        Integer resultado;
        resultado = null;
        try {
            if (entrada > 0) {
                resultado = productos.getCantidad() + entrada;
                productos.setCantidad(resultado);
                prodEJB.edit(productos);
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('wdialog2').show();");
                System.out.println("resultado de la suma: " + resultado);
            } else {
                System.out.println("el dato ingresado no es equivalente");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarEstilistas() {
        estilistaListItem = new ArrayList();

        List<Usuario> listaEstilistas = new ArrayList();
        try {
            Roles rol = new Roles();
            rol.setIdRoles(2);
            listaEstilistas = EJBusuario.consultarRoll(rol);
            for (Usuario estilistaitem : listaEstilistas) {
                estilistaListItem.add(new SelectItem(estilistaitem.getIdUsuario(), estilistaitem.getNombre()));
            }
        } catch (Exception e) {
            System.out.println("listar-estilistas");
            System.out.println(e.getMessage());
        }
    }

    public void buscarPorFechas() {
        try {
            listaSalidaFechas = salidaConsumoEJB.buscarSalidaFecha(fecha);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
     public void salidaDeProductosPorVenta() {
        Integer resultado;
        resultado = null;
        try {
            if (salidaV <= productos.getCantidad()) {
                resultado = productos.getCantidad() - salidaV;
                text2 = Integer.toString(total = productos.getPrecio() * salidaV);
                productos.setCantidad(resultado);
                prodEJB.edit(productos);
                registrarSalidaVenta(salidaV,total);
                System.out.println("resultado operacion: " + resultado);
                System.out.println("total de productos salidos precio: " + text2);
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('wdialog').show();");
            } else {
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('wdialog1').show();");
                System.out.println("el dato ingresado es mayor al que esta en el sistema");
            }

        } catch (ELException | NullPointerException e) {
            System.out.println(e.getMessage());

        }

    }
     
     public void registrarSalidaVenta(int salida,Integer total){
         try{
             salidaVenta.setCantidad(salida);
             salidaVenta.setEstado("inexistente");
             salidaVenta.setProductosidCodigo(productos);
             salidaVenta.setTotal(total);
             salidaVentaEJB.edit(salidaVenta);
         }catch(Exception e){
             
         }
     }
             

}
