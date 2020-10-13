package com.lookextreme.controller;

import com.lookextreme.Dao.CitaFacadeLocal;
import com.lookextreme.Dao.EstilistaFacadeLocal;
import com.lookextreme.Dao.UsuarioFacadeLocal;
import com.lookextreme.model.Cita;
import static com.lookextreme.model.Cita_.serviciosCitasList;
import com.lookextreme.model.Cliente;
import com.lookextreme.model.Estilista;
import com.lookextreme.model.Roles;
import com.lookextreme.model.Servicios;
import com.lookextreme.model.ServiciosCitas;
import com.lookextreme.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CitaController implements Serializable {

    @EJB
    private CitaFacadeLocal EJBcita;    
    private EstilistaFacadeLocal EJBestilista;
    @EJB
    private UsuarioFacadeLocal EJBusuario;
    private Cita cita;
    private Cliente cliente;
    private Estilista estilista;
    private Servicios servicio;
    private Usuario usuario;
    private ServiciosCitas serviciosCitas;
    private List<SelectItem> estilistaListItem;

    public List<SelectItem> getEstilistaListItem() {
        return estilistaListItem;
    }

    public void setEstilistaListItem(List<SelectItem> estilistaListItem) {
        this.estilistaListItem = estilistaListItem;
    }
    

    public Usuario getUsuario() {
        return usuario;
    }

    public Estilista getEstilista() {
        return estilista;
    }

    public void setEstilista(Estilista estilista) {
        this.estilista = estilista;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    @PostConstruct
    public void init() {
        cita = new Cita();
        cliente = new Cliente();
        estilista = new Estilista();
        servicio = new Servicios();
        serviciosCitas = new ServiciosCitas();
        usuario = new Usuario();
        listarEstilistas();
        //FacesContext context = FacesContext.getCurrentInstance();
        //usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
    }

    public void registrarCita() throws Exception {
        System.out.println("Cita_registrada");
        try {
            //cliente.setUsuarioidUsuario(3);
            //cita.setClienteusuarioidUsuario(cliente);
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            cliente.setUsuarioidUsuario(usuario.getIdUsuario());
            cita.setClienteusuarioidUsuario(cliente);
            //estilista.setUsuarioidUsuario(2);
            cita.setEstilistausuarioidUsuario(estilista);
            cita.setIdCita(2);
            cita.setEstado("agendada");
            servicio.setIdServicios(1);
            serviciosCitas.setServiciosidServicios(servicio);
            serviciosCitas.setCitaidCita(cita);
            serviciosCitas.setIdServiciocita(1);
            EJBcita.create(cita);

        } catch (Exception e) {

            System.out.println("" + e.getMessage());

        }
    }

    public void listarEstilistas() {        
        estilistaListItem = new ArrayList();
       
        List<Usuario> listaEstilistas = new ArrayList();
        try {
            
            Roles rol = new Roles();
            rol.setIdRoles(2);
            listaEstilistas = EJBusuario.consultarRoll(rol);
            for (Usuario estilistaitem : listaEstilistas) {
                estilistaListItem.add(new SelectItem(estilistaitem.getIdUsuario(), estilistaitem.getNombre()));
            }
        } catch (Exception e) {            
            System.out.println("listar-estilistas");
            System.out.println(e.getMessage());
        } 
    }
}
