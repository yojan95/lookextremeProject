/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.controller;

import com.lookextreme.Dao.HorarioFacadeLocal;
import com.lookextreme.model.Horario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class HorarioController implements Serializable{
    
    @EJB
    private HorarioFacadeLocal horarioEJB;
    private List<Horario> listHorario;

    public List<Horario> getListHorario() {
        return listHorario;
    }

    public void setListHorario(List<Horario> listHorario) {
        this.listHorario = listHorario;
    }
    
    @PostConstruct
    public void init(){
        listHorario = horarioEJB.findAll();
    }
}
