/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.controller;

import com.lookextreme.model.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class administradorTemplateController implements Serializable {
    private String userName ;

    public String getUserName() {
        return userName;
    }        
    
    public void verificarSesion(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            if (usuario == null || usuario.getRolesidRoles().getIdRoles() != 1){
                context.getExternalContext().redirect("landing.xhtml");
            }else{
                userName = usuario.getNombre();
                System.out.println("username :"+ userName);
            }            
        } catch (IOException e) {
            System.out.println("administradorTemplateController Error : " + e.getMessage());            
        }
    }
    
    public void cerrarSesion() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().clear();
        context.getExternalContext().redirect("landing.xhtml");
    }
}
