
package com.lookextreme.controller;

import com.lookextreme.Dao.ProductosFacadeLocal;
import com.lookextreme.model.Administrador;
import com.lookextreme.model.Productos;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class RegistrarProductoController implements Serializable {
    @EJB
    private ProductosFacadeLocal productoEJB;
    private Productos productos;
    private Administrador administrador;
    
            
            
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
        
    }
    public void registrarProductos(){
        System.out.println("Producto Registrado");
        try {
            administrador.setUsuarioidUsuario(1);
            productos.setAdministradorusuarioidUsuario(administrador);
            productos.setEstado("registrado");
            productos.setMarca("marcel france");
            productos.setNombre("esmalte");
            productoEJB.create(productos);
        }catch (Exception e){
            System.out.println(e.getMessage()); 
            
        }
        
    }
    
    
    
    
    
    
   
}
