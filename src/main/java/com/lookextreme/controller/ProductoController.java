
package com.lookextreme.controller;


import com.lookextreme.Dao.ProductosFacadeLocal;
import com.lookextreme.model.Productos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ProductoController implements Serializable {
    
     @EJB
    private ProductosFacadeLocal prodEJB;
    private List<Productos> productosList;
    private Productos producto;
    

    
    
    
    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }
    @PostConstruct
    public void init() {
        //productosList = prodEJB.findAll();
        //prodList = nomEJB.findAll();
        producto = new Productos();
        productosList = this.prodEJB.findAll();
        FacesContext context = FacesContext.getCurrentInstance();
        
        ListarProductos();
    }
    
  
    
    
    public void ListarProductos(){
        try {
                productosList = new ArrayList();
                productosList = prodEJB.findAll();
            } catch (Exception e) {
                System.out.println("Error CONTROLLER CONSULTA");
            }
        }
    
  
    
    public void eliminarProducto(Productos pro){
        System.out.println("Producto Eliminado ");
        
        try {
            prodEJB.remove(pro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "se elimino el producto con exito!"));
             } catch (Exception e) {
            System.out.println("Erro al eliminar el producto");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "No se pudo eliminar el producto"));
       
             }
    }
    
     public void cargarProductos(Productos pro){
        try{
            this.producto = pro;
        }catch(Exception e){
            System.out.println(""+e.getMessage());
        }
    }
     public void actulizarProducto(){
        System.out.println("servicios actualizados");
        try{
            this.prodEJB.edit(producto);
        }catch(Exception e){
            System.out.println("error al actualizar servicios"+e.getMessage());
        }
    }
  
        
        
    }
            
    
    