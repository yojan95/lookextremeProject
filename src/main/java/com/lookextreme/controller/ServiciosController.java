
package com.lookextreme.controller;

import com.lookextreme.Dao.ServiciosFacadeLocal;
import com.lookextreme.model.Servicios;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.poi.util.IOUtils;
import org.primefaces.PrimeFaces;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.primefaces.model.file.UploadedFile;

@Named
@ViewScoped
public class ServiciosController implements Serializable{
    
    @EJB
    private ServiciosFacadeLocal EJBservicios;
    private Servicios servicios;
    private List<Servicios> listaServicios;
       private UploadedFile file;
    

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }


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
         servicios =  new Servicios();
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
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('wdialog1').show();");
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
    
    public void deleteService(Servicios se){
        try{
        
            EJBservicios.updateService(se);
         
        }catch(Exception e){
            System.out.println("error al eliminar el servicio"+e.getMessage());
        }
    }
    
    public void registrarServicio() {
        
        
        //final String folder_Files = "C:\\Users\\Alexis\\Desktop\\newpage\\com.lookExtreme_Projectlookextreme_war_1.0-SNAPSHOT\\src\\main\\webapp\\resources\\Imagenes\\";
        final String folder_Files = "C:\\Users\\Administrator\\Downloads\\Server Aplication\\glassfish-4.1\\glassfish4\\glassfish\\domains\\domain1\\applications\\lookextremeV5\\resources\\Imagenes\\";
        try {
            if (file.getSize() > 0) {
                servicios.setFoto(file.getFileName());
                servicios.setImagen(file.getContent());
                EJBservicios.create(servicios);
                //File outputfile = new File("C:\\Users\\Alexis\\Desktop\\newpage"+file.getFileName());
                writeImage(IOUtils.toByteArray(file.getInputStream()),folder_Files,file.getFileName());
                servicios = new Servicios();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se creo el servicio correctamente!"));
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('wdialog').show();");
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Seleccione una imagen"));
            }
        } catch (IOException | NullPointerException  e) {
            System.out.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "Por favor seleccione una imagen!"));
        }
    }
    
    
     public void writeImage(byte[] bytes, String folder, String fileName) {
        
        try {
            Path path = Paths.get(folder + fileName);
            Files.write(path, bytes);
            
        } catch (IOException ex) {
           ex.getMessage();
            
        }
        
        
    }


}
