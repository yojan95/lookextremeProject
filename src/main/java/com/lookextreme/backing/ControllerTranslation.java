/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.backing;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named
public class ControllerTranslation implements Serializable{
    private static final long serialVersionUID = 84157941310458440L;
    
    private String local;
    
    public ControllerTranslation(){
        local = "Es";
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
        public void cambiarIdioma(String idioma){
        if (idioma.equals("EN")) {
            //FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.ENGLISH);
            local = idioma;
        }else{
            //FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.getDefault());
            local = "ES";
        }
    }
}
