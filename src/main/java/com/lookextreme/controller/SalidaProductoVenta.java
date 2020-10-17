/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.controller;

;
import com.lookextreme.Dao.ProductosFacadeLocal;
import com.lookextreme.Dao.SalidaVentaFacadeLocal;
import com.lookextreme.model.Administrador;
import com.lookextreme.model.Productos;
import com.lookextreme.model.SalidaVenta;
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
public class SalidaProductoVenta implements Serializable {

    @EJB
    private SalidaVentaFacadeLocal SalidaF;
    private SalidaVenta SalidaM;
    private Administrador administrador;
    private Usuario usuario;
    private Productos pro;

    @EJB
    private ProductosFacadeLocal productoEJB;
    private List<Productos> proList;

    @PostConstruct
    public void init() {
        pro = new Productos();
        SalidaM = new SalidaVenta();
        administrador = new Administrador();
        usuario = new Usuario();

    }

    public SalidaVentaFacadeLocal getSalidaF() {
        return SalidaF;
    }

    public void setSalidaF(SalidaVentaFacadeLocal SalidaF) {
        this.SalidaF = SalidaF;
    }

    public SalidaVenta getSalidaM() {
        return SalidaM;
    }

    public void setSalidaM(SalidaVenta SalidaM) {
        this.SalidaM = SalidaM;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Productos getPro() {
        return pro;
    }

    public void setPro(Productos pro) {
        this.pro = pro;
    }

    public ProductosFacadeLocal getProductoEJB() {
        return productoEJB;
    }

    public void setProductoEJB(ProductosFacadeLocal productoEJB) {
        this.productoEJB = productoEJB;
    }

    public List<Productos> getProList() {
        return proList;
    }

    public void setProList(List<Productos> proList) {
        this.proList = proList;
    }
    

    public void salidaRegistrada() {
        System.out.println("Producto Registrado");

        try {

            SalidaM.setProductosidCodigo(pro);
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            administrador.setUsuarioidUsuario(usuario.getIdUsuario());
            SalidaF.create(SalidaM);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    /*
          public void ConsumoRegistrado(){
        System.out.println("Producto Registrado");
        
        try {
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
     }*/
}

