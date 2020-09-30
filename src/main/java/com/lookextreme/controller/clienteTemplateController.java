package com.lookextreme.controller;

import com.lookextreme.model.Usuario;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
//si señor
//igual haga el commit
//que?
// vuelvalo a generar, normal
@Named
@ViewScoped
public class clienteTemplateController implements Serializable {
    
    private String userName ;

    public String getUserName() {
        return userName;
    }        
    
    public void verificarSesion(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            if (usuario == null){
                context.getExternalContext().redirect("landing.xhtml");
            }else{
                userName = usuario.getNombre();
            }            
        } catch (Exception e) {
            System.out.println("clienteTemplateController Error : " + e.getMessage());            
        }
    }
}
