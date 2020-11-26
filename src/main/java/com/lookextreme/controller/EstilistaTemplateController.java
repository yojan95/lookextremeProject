package com.lookextreme.controller;

import com.lookextreme.model.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class EstilistaTemplateController implements Serializable {

    String userName;

    public String getUserName() {
        return userName;
    }

    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            if (usuario == null) {
                context.getExternalContext().redirect("landing.xhtml");
            } else {
                userName = usuario.getNombre();
            }
        } catch (Exception e) {
            System.out.println("estilistaTemplateController Error : " + e.getMessage());
        }
    }

    public void cerrarSesion() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().clear();
        context.getExternalContext().redirect("landing.xhtml");
    }
}
