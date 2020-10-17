package com.lookextreme.controller;

import com.lookextreme.Dao.PqrsFacadeLocal;
import com.lookextreme.model.Cliente;
import com.lookextreme.model.Pqrs;
import com.lookextreme.model.TipoPqrs;
import com.lookextreme.model.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;
import org.primefaces.model.file.UploadedFile;


@Named
@ViewScoped
public class PqrsController implements Serializable {

    @EJB
    PqrsFacadeLocal EjbPqrs;
    private Pqrs pqrs;
    private TipoPqrs tipoPqrs;   
    private Usuario usuario;
    private List<Pqrs> pqrsList;
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    
    
    public List<Pqrs> getPqrsList() {
        return pqrsList;
    }

    public void setPqrsList(List<Pqrs> pqrsList) {
        this.pqrsList = pqrsList;
    }
/*
    public UIComponent getButtonCreate() {
        return buttonCreate;
    }

    public void setButtonCreate(UIComponent buttonCreate) {
        this.buttonCreate = buttonCreate;
    }
*/
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
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        System.out.println("usuario pqrs: "+ usuario.getIdUsuario());
        String origin = obtenerUri();
        if (origin.contains("cliente_crearPqrs.xhtml")) {
            pqrs = new Pqrs();
            tipoPqrs = new TipoPqrs();            
        } else if (origin.contains("cliente_consultarpqrs.xhtml")) {
            obtenerPqrsPorCliente(usuario.getIdUsuario());
        } else if (origin.contains("administrador_consultarpqrs.xhtml")) {
            pqrs = new Pqrs();
            obtenerPqrsAdministrador();
        }
    }

    private String obtenerUri() {
        String uri;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest servletRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        uri = servletRequest.getRequestURI();
        System.out.println("init pqrs controller from :" + uri);
        return uri;
    }

    public void validarPqrs() {

        if (tipoPqrs.getIdTipoPQRS() == -1) {
            showMessageError("Debe seleccionar un tipo válido.");
        } else if (pqrs.getAsunto().isEmpty() || pqrs.getAsunto().length() < 10) {
            showMessageError("Debe ingresar un asunto mayor a 10 caracteres.");
        } else if (pqrs.getDetalles().isEmpty() || pqrs.getDetalles().length() < 30) {
            showMessageError("Debe ingresar un detalle mayor a 30 caracteres.");
        } else {
            crearPqrs();
        }
    }

    private void showMessageError(String messageText) {
        try {
            FacesMessage message;
            FacesContext context = FacesContext.getCurrentInstance();
            message = new FacesMessage(messageText);
            //context.addMessage(buttonCreate.getClientId(context), message);
            context.addMessage(null, message);
        } catch (Exception e) {
            System.out.println("showMessageError : " + e.getMessage());
        }
    }

    public void crearPqrs() {
        System.out.println("Creando PQRS");
        try {
            Timestamp fechaCreacion = new Timestamp(System.currentTimeMillis());
            pqrs.setIdPQRS(1);
            pqrs.setTipopqrsidTipoPQRS(tipoPqrs);
            pqrs.setEstado("Pendiente");
            pqrs.setFechaHoraCreacion(fechaCreacion);
            Cliente cliente = new Cliente();
            cliente.setUsuarioidUsuario(usuario.getIdUsuario());
            pqrs.setClienteusuarioidUsuario(cliente);
            if(file.getSize()> 0)
                pqrs.setAnexos(file.getContent());
            EjbPqrs.create(pqrs);
        } catch (Exception e) {
            showMessageError("No pudo ser creado correctamente el PQRS.");
            System.out.println("Crear Pqrs Error: " + e.getMessage());
        }
    }

    private void obtenerPqrsPorCliente(int idCliente) {
        if (idCliente > 0) {
            try {
                pqrsList = new ArrayList();
                pqrsList = EjbPqrs.obtenerPqrsPorCliente(idCliente);
            } catch (Exception e) {
                System.out.println("obtener pqrs cliente error: " + e.getLocalizedMessage());
            }
        }
    }

    private void obtenerPqrsAdministrador() 
    {
        try 
        {
            pqrsList = new ArrayList();
            pqrsList = EjbPqrs.obtenerPqrsAdministrador();
        } catch (Exception e) 
        {
            System.out.println("obtener pqrs admin error: " + e.getLocalizedMessage());
        }
    }

    public void openModal(Pqrs _pqrs) 
    {
        PrimeFaces current = PrimeFaces.current();
        if (_pqrs.getRespuesta() == null) 
        {
            _pqrs.setRespuesta("");
        }
        pqrs = _pqrs;
        current.executeScript("PF('dialog_pqrs').show();");
    }

    public void guardarRespuesta() {
        try 
        {
            System.out.println("nueva respuesta: "+ pqrs.getRespuesta());
            if (!pqrs.getRespuesta().isEmpty())
            {
                pqrs.setEstado("Respondido");
                EjbPqrs.edit(pqrs);
            }
        } catch (Exception e) 
        {
            System.out.println("guardar respuesta error: "+ e.getMessage());
        }
    }
    
    public void descargarAnexo(byte[] anexoBytes) throws IOException{               
        try {
            File anexoFile = File.createTempFile("Anexo", null);
            try (FileOutputStream fos = new FileOutputStream(anexoFile)) {
                fos.write(anexoBytes);
                fos.close();
            }
        } catch (IOException e) {
            System.out.println("descargar anexo error: " + e.getMessage());
        }                
    }
}
