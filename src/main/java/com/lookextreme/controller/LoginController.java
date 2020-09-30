/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.controller;

import com.lookextreme.Dao.UsuarioFacadeLocal;
import com.lookextreme.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class LoginController implements Serializable {

    @EJB
    UsuarioFacadeLocal EJBUsuario;
    private Usuario usuario;
    private UIComponent buttonLogin;

    public UIComponent getButtonLogin() {
        return buttonLogin; 
    }

    public void setButtonLogin(UIComponent buttonLogin) {
        this.buttonLogin = buttonLogin;
    }

    
    
    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String iniciarSesion() {
        System.out.println("verificando usuario");
        String redireccion = null;
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            usuario = EJBUsuario.iniciarSesion(usuario);
            if (usuario != null) {
                context.getExternalContext().getSessionMap().put("usuario", usuario);
                redireccion = redireccionarUsuario(usuario.getRolesidRoles().getIdRoles());
            } else {
                FacesMessage message;                
                message = new FacesMessage("Usuario y/o contraseña incorrectos.");
                context.addMessage(null, message);
                redireccion = "login";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return redireccion;
    }

    public String redireccionarUsuario(int rol) {
        switch (rol) {
            case 1:
                return "indexAdministrador";                
            case 2:
                return "indexEstilista";                
            default:
            return "indexCliente";                
        }
    }

    public String redireccionarRegistrarse() {
        return "registrarse";
    }
}
