/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.controller;

import com.lookextreme.Dao.CitaFacadeLocal;
import com.lookextreme.Dao.EstilistaFacadeLocal;
import com.lookextreme.Dao.UsuarioFacadeLocal;
import com.lookextreme.model.Cita;
import com.lookextreme.model.Estilista;
import com.lookextreme.model.Roles;
import com.lookextreme.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class EstilistaController implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioEJB;
    private Usuario usuario;
    private Roles roles;

    @EJB
    private EstilistaFacadeLocal estilistaEJB;
    private Estilista estilista;

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Estilista getEstilista() {
        return estilista;
    }

    public void setEstilista(Estilista estilista) {
        this.estilista = estilista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @PostConstruct
    public void init() {
        estilista = new Estilista();
        roles = new Roles();
        usuario = new Usuario();
    }

    public void registrarUsuarioEstilista() {
        System.out.println("estilista-registrado");
        try {
            roles.setIdRoles(2);
            usuario.setRolesidRoles(roles);
            usuarioEJB.create(usuario);
            registrarEstilista(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Estilista Registrado"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void registrarEstilista(Usuario usuario) {
        System.out.println("estilista-satisfactoriamente");
        try {

            estilista.setUsuarioidUsuario(usuario.getIdUsuario());
            estilista.setUsuario(usuario);
            estilistaEJB.create(estilista);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
