

package com.lookextreme.controller;

import com.csvreader.CsvReader;
import com.lookextreme.Dao.ServiciosFacadeLocal;
import com.lookextreme.model.Servicios;
import java.io.InputStreamReader;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.file.UploadedFile;

@Named
@ViewScoped
public class CargaDatosServiciosController implements Serializable{
    
    @EJB
    private ServiciosFacadeLocal EJBservicios;
    private Servicios servicios;
    private UploadedFile file;

    public Servicios getServicios() {
        return servicios;
    }

    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    @PostConstruct
    public void insertar(){
        
    }
    public void actionCargarDatos(){
        try{
            if (file.getSize()>0) {
                CsvReader leerArchivos;
                leerArchivos = new CsvReader(new InputStreamReader(file.getInputStream()));
                leerArchivos.readHeaders();
                
                while(leerArchivos.readRecord()){
                    servicios = new Servicios();
                    servicios.setDescripcion(leerArchivos.get(0));
                    servicios.setPrecio(Integer.parseInt(leerArchivos.get(1)));
                    servicios.setNombre(leerArchivos.get(2));
                    EJBservicios.create(servicios);
                }
                leerArchivos.close();
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se cargaron los datos exitosamente"));
                 //FacesMessage message = new FacesMessage("Exito", file.getFileName()+"fue subido");
                  //FacesMessage message = new FacesMessage("Se subio con exito el archivo");
                 //FacesContext.getCurrentInstance().addMessage(null, message);
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Por favor seleccione un archivo!"));
                 //FacesMessage message = new FacesMessage("No se pudo subir el archivo");
                 //FacesContext.getCurrentInstance().addMessage(null, message);
            }
           
        }catch(Exception e){
            System.out.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No se registraron los datos"));
           // FacesMessage message = new FacesMessage("No se pudo subir el archivo");
            //FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
