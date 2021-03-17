/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.controller;

import com.lookextreme.Dao.CategoriasFacadeLocal;
import com.lookextreme.model.Categorias;
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
public class CategoriaController implements Serializable {
    
    @EJB
    private CategoriasFacadeLocal catEJB;
    private List<Categorias> CList;
    private Categorias categorias;

    public List<Categorias> getCList() {
        return CList;
    }

    public void setCList(List<Categorias> CList) {
        this.CList = CList;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }
    
    @PostConstruct
    public void init() {       
        
        categorias = new Categorias();
        CList = catEJB.findAll();
        ListarCategorias();
        
    }
    
    public void ListarCategorias(){
        try {
            CList = new ArrayList();
            CList = catEJB.findAll();           
            
        } catch (Exception e) {
            System.out.println("Error en la carga de la lista");
        }
    }
    

     public void cargarCategorias (Categorias cat) {
        try {
            this.categorias = cat;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }
     public void actualizarCategorias() {
        System.out.println("Nombre de la Categoria Actualizado");
        try {
            this.catEJB.edit(categorias);
        } catch (Exception e) {
            System.out.println("error al actualizar el nombre del producto" + e.getMessage());
        }
    }
      public void eliminarCategorias(Categorias cat) {
        System.out.println("Nombre de la Categoria Eliminado ");
        try {
            this.catEJB.remove(cat);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "se elimino el Nombre de la Categoria con exito!"));
        } catch (Exception e) {
            System.out.println("error al eliminar el nombre del producto" + e.getMessage());
        }
    }
    
}
