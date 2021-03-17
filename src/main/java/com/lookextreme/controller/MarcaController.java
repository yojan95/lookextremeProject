/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.controller;

import com.lookextreme.Dao.MarcaFacadeLocal;
import com.lookextreme.model.Marca;
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
public class MarcaController implements Serializable {

    @EJB
    private MarcaFacadeLocal marEJB;
    private List<Marca> MList;
    private Marca marca;

    public List<Marca> getMList() {
        return MList;
    }

    public void setMList(List<Marca> MList) {
        this.MList = MList;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @PostConstruct
    public void init() {

        marca = new Marca();
        MList = this.marEJB.findAll();
        ListarMarca();

    }

    public void ListarMarca() {
        try {
            MList = new ArrayList();
            MList = marEJB.findAll();
        } catch (Exception e) {
            System.out.println("Error CONTROLLER CONSULTA");
        }
    }

    public void cargarMarca(Marca mar) {
        try {
            this.marca = mar;

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }

    }

    public void actualizarMarca() {
        System.out.println("Marca actualizada");
       
            this.marEJB.edit(marca);
        
    }

    public void eliminarMarca(Marca mar) {
        System.out.println("Nombre de la Marca Eliminado");
        try {
            this.marEJB.remove(mar);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "se elimino el Nombre de la Marca con exito!"));
        } catch (Exception e) {
        }

    }

}
