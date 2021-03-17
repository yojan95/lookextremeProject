/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.controller;

import com.lookextreme.Dao.EstilistaFacadeLocal;
import com.lookextreme.Dao.HorarioFacadeLocal;
import com.lookextreme.Dao.UsuarioFacadeLocal;
import com.lookextreme.model.Estilista;
import com.lookextreme.model.Horario;
import com.lookextreme.model.Roles;
import com.lookextreme.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class HorarioController implements Serializable {

    @EJB
    private HorarioFacadeLocal horarioEJB;
    private List<Horario> listHorario;
    private Horario horarioE;

    @EJB
    private UsuarioFacadeLocal EJBusuario;
    private Usuario usuario;

    @EJB
    private EstilistaFacadeLocal EJBestilista;
    private Estilista estilista;
    private List<SelectItem> estilistaListItem;

    public Horario getHorarioE() {
        return horarioE;
    }

    public void setHorarioE(Horario horarioE) {
        this.horarioE = horarioE;
    }

    public Estilista getEstilista() {
        return estilista;
    }

    public void setEstilista(Estilista estilista) {
        this.estilista = estilista;
    }

    public List<SelectItem> getEstilistaListItem() {
        return estilistaListItem;
    }

    public void setEstilistaListItem(List<SelectItem> estilistaListItem) {
        this.estilistaListItem = estilistaListItem;
    }

    public List<Horario> getListHorario() {
        return listHorario;
    }

    public void setListHorario(List<Horario> listHorario) {
        this.listHorario = listHorario;
    }

    @PostConstruct
    public void init() {
        horarioE = new Horario();
        estilista = new Estilista();
        listHorario = horarioEJB.findAll();
        listarEstilistas();
    }

    /*
    =================
    lista los estilistas mediante un selectItem y muestralos por el nombre obteniendo su rol 
    =================
     */
    public void listarEstilistas() {
        estilistaListItem = new ArrayList();

        List<Usuario> listaEstilistas = new ArrayList();
        try {
            Roles rol = new Roles();
            rol.setIdRoles(2);
            listaEstilistas = EJBusuario.consultarRoll(rol);
            for (Usuario estilistaitem : listaEstilistas) {
                estilistaListItem.add(new SelectItem(estilistaitem.getIdUsuario(), estilistaitem.getNombre()));
            }
        } catch (Exception e) {
            System.out.println("listar-estilistas");
            System.out.println(e.getMessage());
        }
    }

    public boolean ValidarHorarioEstilista() {
        listHorario = horarioEJB.findAll();
        Horario hour = new Horario();
        boolean estado = true;

        try {

            for (int i = 0; i < listHorario.size(); i++) {
                if (listHorario.get(i).getEstilistausuarioidUsuario().getUsuarioidUsuario().equals(estilista.getUsuarioidUsuario())) {
                    hour.setEstilistausuarioidUsuario(listHorario.get(i).getEstilistausuarioidUsuario());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "ya existe el registro"));
                    estado = true;
                    break;
                } else {
                    estado = false;
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return estado;
    }

    public void registrarHorario() {
        boolean estadod;
        Short dias = 2;
        try {
            estadod = ValidarHorarioEstilista();
            if (estadod == false) {
                horarioE.setDias(dias);
                horarioE.setEstilistausuarioidUsuario(estilista);
                horarioEJB.create(horarioE);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro el horario"));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
