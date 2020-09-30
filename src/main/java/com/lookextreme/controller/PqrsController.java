package com.lookextreme.controller;

import com.lookextreme.Dao.PqrsFacadeLocal;
import com.lookextreme.model.Cliente;
import com.lookextreme.model.Pqrs;
import com.lookextreme.model.TipoPqrs;
import com.lookextreme.model.Usuario;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

@Named
@ViewScoped
public class PqrsController implements Serializable{ 
        
    @EJB
    PqrsFacadeLocal EjbPqrs;
    private Pqrs pqrs;
    private TipoPqrs tipoPqrs;
    private UIComponent buttonCreate;
    private Usuario usuario;

    public PqrsFacadeLocal getEjbPqrs() {
        return EjbPqrs;
    }

    public void setEjbPqrs(PqrsFacadeLocal EjbPqrs) {
        this.EjbPqrs = EjbPqrs;
    }

    public UIComponent getButtonCreate() {
        return buttonCreate;
    }

    public void setButtonCreate(UIComponent buttonCreate) {
        this.buttonCreate = buttonCreate;
    }
       
    public TipoPqrs getTipoPqrs() {
        return tipoPqrs;
    }

    public void setTipoPqrs(TipoPqrs tipoPqrs) {
        this.tipoPqrs = tipoPqrs;
    }        

    public Pqrs getPqrs() {
        return pqrs;
    }

    public void setPqrs(Pqrs pqrs) {
        this.pqrs = pqrs;
    }
    
    
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        System.out.println("id usuario " + usuario.getIdUsuario());
        pqrs = new Pqrs();
        tipoPqrs = new TipoPqrs();
    }
    
    public void validarPqrs(){
        
        if(tipoPqrs.getTipoPQRS().equals("No existen Tipos de PQRS") ){
            showMessageError("Debe seleccionar un tipo válido.");            
        }else if(pqrs.getAsunto().isEmpty() || pqrs.getAsunto().length() < 10){
            showMessageError("Debe ingresar un asunto mayor a 10 caracteres.");            
        }else if(pqrs.getDetalles().isEmpty() || pqrs.getDetalles().length() < 30){
            showMessageError("Debe ingresar un detalle mayor a 30 caracteres.");            
        }else{            
            crearPqrs();            
        }
        //return  null;
    }
    
    private void showMessageError(String messageText){
        try {
            FacesMessage message;
            FacesContext context = FacesContext.getCurrentInstance();
            message = new FacesMessage(messageText);
            context.addMessage(buttonCreate.getClientId(context), message);
        } catch (Exception e) {
            System.out.println("showMessageError : " + e.getMessage());                    
        }        
    }
    
    public void crearPqrs(){
           System.out.println("Creando PQRS");
        try {
            Timestamp fechaCreacion = new Timestamp(System.currentTimeMillis());
            pqrs.setIdPQRS(1);
            pqrs.setTipopqrsidTipoPQRS(tipoPqrs);            
            pqrs.setEstado("Pendiente");
            pqrs.setFechaHoraCreacion(fechaCreacion);
            Cliente cliente = new Cliente();
            cliente.setUsuario(usuario);
            pqrs.setClienteusuarioidUsuario(cliente);
            EjbPqrs.create(pqrs);            
        } catch (Exception e) {
            System.out.println("Crear Pqrs Error: " + e.getMessage());
        }
        
        
        /*System.out.println("CrearPqrs");
        System.out.println("tipo: "+tipoPqrs.getTipoPQRS());
        System.out.println("asunto: "+pqrs.getAsunto());
        System.out.println("detalles: "+pqrs.getDetalles());*/
    }
}