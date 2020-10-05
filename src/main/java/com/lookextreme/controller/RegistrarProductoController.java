
package com.lookextreme.controller;

import com.lookextreme.Dao.ProductosFacadeLocal;
import com.lookextreme.Dao.NombreproductoFacadeLocal;
import com.lookextreme.Dao.MarcaFacadeLocal;
import com.lookextreme.model.Administrador;
import com.lookextreme.model.Productos;
import com.lookextreme.model.Categorias;
import com.lookextreme.model.Marca;
import com.lookextreme.model.Nombreproducto;     
import com.lookextreme.Dao.CategoriasFacadeLocal;
import com.lookextreme.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class RegistrarProductoController implements Serializable {
    @EJB
    private ProductosFacadeLocal productoEJB;    
    private Productos productos;
    private Administrador administrador;
    private Categorias categorias;
    private Marca marcas;
    private Nombreproducto nombrep;
    private Usuario usuario ;
    
    
    @EJB 
    private CategoriasFacadeLocal categoriasEJB;
    private List<Categorias> categoriasList;
    
    @EJB
    private NombreproductoFacadeLocal nombreEJB;
    private List<Nombreproducto> nombrepList;
    
    @EJB
    private MarcaFacadeLocal marcaEJB;
    private List<Marca> marcaList;
    
    
    @PostConstruct 
    
    public void init(){
        productos = new Productos();
        administrador = new Administrador();
        marcas = new Marca();
        categorias = new Categorias();
        nombrep = new Nombreproducto();
        usuario = new Usuario();
        categoriasList = categoriasEJB.findAll();
        nombrepList = nombreEJB.findAll();
        marcaList = marcaEJB.findAll();
                
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }

    public Marca getMarcas() {
        return marcas;
    }

    public void setMarcas(Marca marcas) {
        this.marcas = marcas;
    }

    public Nombreproducto getNombrep() {
        return nombrep;
    }

    public void setNombrep(Nombreproducto nombrep) {
        this.nombrep = nombrep;
    }

    public List<Marca> getMarcaList() {
        return marcaList;
    }

    public void setMarcaList(List<Marca> marcaList) {
        this.marcaList = marcaList;
    }

  


    public List<Nombreproducto> getNombrepList() {
        return nombrepList;
    }

    public void setNombrepList(List<Nombreproducto> nombrepList) {
        this.nombrepList = nombrepList;
    }

    public List<Categorias> getCategoriasList() {
        return categoriasList;
    }

    public void setCategoriasList(List<Categorias> categoriasList) {
        this.categoriasList = categoriasList;
    }
    
    public void registrarProductos(){
        System.out.println("Producto Registrado");
        
        try {
            productos.setCategoriasIdcategorias(categorias);
            productos.setNombreProductoidNombreProducto(nombrep);            
            productos.setMarcaIdmarca(marcas);            
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            administrador.setUsuarioidUsuario(usuario.getIdUsuario());
            productos.setAdministradorusuarioidUsuario(administrador);
            productos.setEstado("registrado");
            productoEJB.create(productos);
        }catch (Exception e){
            System.out.println(e.getMessage()); 
            
        }
        
    }
    
   
   
    
    
    
    
    
    
   
}
