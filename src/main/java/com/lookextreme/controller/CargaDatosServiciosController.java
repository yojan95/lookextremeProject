package com.lookextreme.controller;

import com.lookextreme.Dao.ServiciosFacadeLocal;
import com.lookextreme.model.Servicios;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.file.UploadedFile;

@Named
@ViewScoped
public class CargaDatosServiciosController implements Serializable {

    @EJB
    private ServiciosFacadeLocal serviciosEJB;
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
    public void init() {
        servicios = new Servicios();
    }

    public void cargarServicios() {
        System.out.println("cargando servicios");
        if (file.getSize() > 0) {
            try {
                InputStream input = file.getInputStream();
                XSSFWorkbook libro = new XSSFWorkbook(input);
                Sheet sheet = libro.getSheetAt(0);
                Iterator<Row> iterator = sheet.iterator();
                int i = 0;
                while (iterator.hasNext()) {
                    Servicios nuevosServicios = new Servicios();
                    Row currentRow = iterator.next();
                    if (i > 0) {
                        if (currentRow.getCell(0) == null && currentRow.getCell(1) != null
                                && currentRow.getCell(2) != null && currentRow.getCell(3) != null) {
                            nuevosServicios.setDescripcion(currentRow.getCell(1).getStringCellValue());
                            nuevosServicios.setPrecio((int)currentRow.getCell(2).getNumericCellValue());
                            nuevosServicios.setNombre(currentRow.getCell(3).getStringCellValue());
                            serviciosEJB.create(nuevosServicios);
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se cargaron los datos exitosamente"));
                        }else{
                            break;
                        }
                    }
                    i++;
                }
                libro.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Por favor seleccione un archivo!"));
        }
    }
}
