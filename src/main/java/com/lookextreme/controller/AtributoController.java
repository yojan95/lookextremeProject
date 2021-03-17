/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.controller;


import com.lookextreme.Dao.NombreproductoFacadeLocal;
import com.lookextreme.model.Nombreproducto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author fabia
 */
@Named
@ViewScoped
public class AtributoController implements Serializable {

    @EJB
    private NombreproductoFacadeLocal prodEJB;
    private List<Nombreproducto> NList;
    private Nombreproducto nombreProducto;

    public List<Nombreproducto> getNList() {
        return NList;
    }

    public void setNList(List<Nombreproducto> NList) {
        this.NList = NList;
    }

    public Nombreproducto getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(Nombreproducto nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public NombreproductoFacadeLocal getProdEJB() {
        return prodEJB;
    }

    public void setProdEJB(NombreproductoFacadeLocal prodEJB) {
        this.prodEJB = prodEJB;
    }

    @PostConstruct
    public void init() {
        nombreProducto = new Nombreproducto();
        NList = this.prodEJB.findAll();
        ListarNombreproducto();

    }

    public void ListarNombreproducto() {
        try {
            NList = new ArrayList();
            NList = prodEJB.findAll();
        } catch (Exception e) {
            System.out.println("Error CONTROLLER CONSULTA");
        }
    }

    public void cargarNombres(Nombreproducto nombre) {
        try {
            this.nombreProducto = nombre;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    public void actualizarNombreproducto() {
        System.out.println("Nombre Actualizado");
        try {
            this.prodEJB.edit(nombreProducto);
        } catch (Exception e) {
            System.out.println("error al actualizar el nombre del producto" + e.getMessage());
        }
    }

    public void eliminarNombreproducto(Nombreproducto pro) {
        System.out.println("Nombre de Producto Eliminado ");
        try {
            this.prodEJB.remove(pro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "se elimino el Nombre de Producto con exito!"));
        } catch (Exception e) {
            System.out.println("error al eliminar el nombre del producto" + e.getMessage());
        }
    }

}
