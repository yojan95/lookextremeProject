
package com.lookextreme.controller;

import com.lookextreme.Dao.ServiciosFacadeLocal;
import com.lookextreme.model.Servicios;
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
public class ServiciosController implements Serializable{
    
    @EJB
    private ServiciosFacadeLocal EJBservicios;
    private Servicios servicios;
    private List<Servicios> listaServicios;

    public List<Servicios> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<Servicios> listaServicios) {
        this.listaServicios = listaServicios;
    }
    
    

    public Servicios getServicios() {
        return servicios;
    }

    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }
    
    @PostConstruct
    public void init(){
        servicios = new Servicios();
        listaServicios = this.EJBservicios.findAll();
    }
    public void crearServicio(){
        try{
            EJBservicios.create(servicios);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "se creo el servicio con exito!"));
        }catch(Exception e){
            System.out.println(e.getMessage());
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No se creo el servicio!"));
        }
    }
 
    public void eliminarServicio(Servicios re){
        System.out.println("servicio eliminado");
        try{
            EJBservicios.remove(re);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "se elimino el servicio con exito!"));
        }catch(Exception e){
            System.out.println("error al eliminar servicio"+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No se pudo eliminar el servicio!"));
        }
    }
    public void cargarServicios(Servicios ac){
        try{
            this.servicios = ac;
        }catch(Exception e){
            System.out.println(""+e.getMessage());
        }
    }
    public void actulizarServicios(){
        System.out.println("servicios actualizados");
        try{
            this.EJBservicios.edit(servicios);
        }catch(Exception e){
            System.out.println("error al actualizar servicios"+e.getMessage());
        }
    }
    public String irAeliminar(){
        String redireccionar = null;
        redireccionar = "admin-eliminarServicios";
        
        return redireccionar;
    }
    public String irActualizar(){
        String redireccionar = null;
        redireccionar = "admin-GestionarServicios";
        
        return redireccionar;
    }

}
