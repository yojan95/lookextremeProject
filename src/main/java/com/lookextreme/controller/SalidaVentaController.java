/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.controller;

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
public class SalidaVentaController implements Serializable {

    @EJB
    private SalidaVentaFacadeLocal salidaVenta;
    private SalidaVenta SalidaM;
    private Administrador administrador;
    private Usuario usuario;
    private Productos pro;
    private ProductosFacadeLocal prof;

    @EJB
    private ProductosFacadeLocal productoEJB;
    private List<Productos> proList;

    @PostConstruct
    public void init() {
        pro = new Productos();
        SalidaM = new SalidaVenta();
        administrador = new Administrador();
        usuario = new Usuario();
        proList = productoEJB.findAll();

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
            salidaVenta.create(SalidaM);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

}

