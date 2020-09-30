
package com.lookextreme.controller;

import com.lookextreme.Dao.ProductosFacadeLocal;
import com.lookextreme.model.Administrador;
import com.lookextreme.model.Productos;
import com.lookextreme.model.Categorias;
import com.lookextreme.model.Marca;
import com.lookextreme.model.Nombreproducto;     
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.faces.model.SelectItem;

@Named
@ViewScoped
public class RegistrarProductoController implements Serializable {
    @EJB
    private ProductosFacadeLocal productoEJB;
    private Productos productos;
    private Administrador administrador;
    private List<SelectItem> categoriaListItem;
    private List<SelectItem> marcaListItem;
    private List<SelectItem> nombreListItem;
    private Categorias categorias;
    private Marca marcas;
    private Nombreproducto nombrep;

    public List<SelectItem> getCategoriaListItem() {
        return categoriaListItem;
    }

    public void setCategoriaListItem(List<SelectItem> categoriaListItem) {
        this.categoriaListItem = categoriaListItem;
    }
    
    public List<SelectItem> getMarcaListItem(){
        return marcaListItem;        
       
    }
  
    public void setMarcaListItem(List<SelectItem> marcaListItem){
        this.marcaListItem = marcaListItem;
        
    }
    
    public List<SelectItem> getNombreproductoListItem(){
        return nombreListItem;
        
    }
    
    public void setNombreListItem (List<SelectItem> nombreListItem){
        this.nombreListItem = marcaListItem;
        
    }
    
    
            
            
    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }
    @PostConstruct 
    
    public void init(){
        productos = new Productos();
        administrador = new Administrador();
        listarC();
        listarM();
        
    }
    public void registrarProductos(){
        System.out.println("Producto Registrado");
        try {
            administrador.setUsuarioidUsuario(1);
            productos.setAdministradorusuarioidUsuario(administrador);
            productos.setEstado("registrado");
            productoEJB.create(productos);
        }catch (Exception e){
            System.out.println(e.getMessage()); 
            
        }
        
    }
    /*
        public void listarEstilistas() {        
        estilistaListItem = new ArrayList();
        
        try {
            List<Usuario> listaEstilistas = new ArrayList();
            Roles rol = new Roles();
            rol.setIdRoles(2);
            listaEstilistas = EJBusuario.consultarRoll(rol);
            for (Usuario usuario : listaEstilistas) {
                estilistaListItem.add(new SelectItem(usuario.getIdUsuario(), usuario.getNombre()));
            }
        } catch (Exception e) {            
            System.out.println("listar-estilistas");
            System.out.println(e.getMessage());
        }       
    }
    */
    public void listarC (){
        
        categoriaListItem = new ArrayList();
        try{
            List<Categorias> listar = new ArrayList();            
            for (Categorias categoria : listar ){
                categoriaListItem.add(new SelectItem(categoria.getIdcategorias(), categoria.getCaNombre()));
            }
        }catch (Exception e ){
            System.out.println(e.getMessage());
            
        }
    }
        public void listarM (){
        
        marcaListItem = new ArrayList();
        try{
            List<Marca> listar = new ArrayList();            
            for (Marca marcas : listar ){
                marcaListItem.add(new SelectItem(marcas.getIdmarca(), marcas.getMaNombre()));
            }
        }catch (Exception e ){
            System.out.println(e.getMessage());
            
        }
    }
        
        public void ListarN(){
            
            nombreListItem = new ArrayList();
            try{
                List<Nombreproducto> listar = new ArrayList();
                nombreListItem.add(new SelectItem(nombrep.getIdNombreProducto(), nombrep.getNombreProductocol()));
                
            }catch (Exception e ){
                System.out.println(e.getMessage());
            }
            
            
        }
   
    
    
    
    
    
    
   
}
