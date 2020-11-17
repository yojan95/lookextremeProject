package com.lookextreme.controller;

import com.lookextreme.Dao.CitaFacadeLocal;
import com.lookextreme.Dao.ServiciosCitasFacadeLocal;
import com.lookextreme.model.Cita;
import com.lookextreme.model.ServiciosCitas;
import com.lookextreme.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CitaConsultarController implements Serializable {

    @EJB
    private CitaFacadeLocal EJBcita;
    private Usuario usuario;
    private Cita cita;
    private List<Cita> citalist;
    private List<Cita> listCitaEstadoImcumpliento;
    @EJB
    private ServiciosCitasFacadeLocal EJBserviciosCitas;
    private ServiciosCitas serviciosCita;
    private List<ServiciosCitas> ListServiciosCita;

    public List<Cita> getListCitaEstadoImcumpliento() {
        return listCitaEstadoImcumpliento;
    }

    public void setListCitaEstadoImcumpliento(List<Cita> listCitaEstadoImcumpliento) {
        this.listCitaEstadoImcumpliento = listCitaEstadoImcumpliento;
    }

    public ServiciosCitas getServiciosCita() {
        return serviciosCita;
    }

    public void setServiciosCita(ServiciosCitas serviciosCita) {
        this.serviciosCita = serviciosCita;
    }

    public List<ServiciosCitas> getListServiciosCita() {
        return ListServiciosCita;
    }

    public void setListServiciosCita(List<ServiciosCitas> ListServiciosCita) {
        this.ListServiciosCita = ListServiciosCita;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public List<Cita> getCitalist() {
        return citalist;
    }

    public void setCitalist(List<Cita> citalist) {
        this.citalist = citalist;
    }

    @PostConstruct
    public void init() {
        cita = new Cita();
        usuario = new Usuario();
        FacesContext context = FacesContext.getCurrentInstance();
        usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");

        obtenerCitaPorCliente(usuario.getIdUsuario());
        obtenerCitaPorEstilistas(usuario.getIdUsuario());
        obtenerCitaPorEstilistaEstadoIncumpliento(usuario.getIdUsuario());
    }

    /*
    =================
    obtener el id del cliente logeado para realizar la consulta de la cita,
    pero primero crea los metodos en la fachada para pasar el parametro
    =================
     */

    public void obtenerCitaPorCliente(int idCliente) {
        if (idCliente > 0) {
            try {
                citalist = new ArrayList();
                citalist = EJBcita.obtenerCitaPorCliente(idCliente);
            } catch (Exception e) {
                System.out.println("obtener error: " + e.getLocalizedMessage());
            }
        }
    }

    private void obtenerCitaPorEstilistas(int idEstilista) {
        if (idEstilista > 0) {
            try {
                ListServiciosCita = new ArrayList();
                ListServiciosCita = EJBserviciosCitas.obtenerCitaPorEstilistas(idEstilista);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void obtenerCitaPorEstilistaEstadoIncumpliento(int idEstilistaa) {
        if (idEstilistaa > 0) {
            try {
                listCitaEstadoImcumpliento = new ArrayList();
                listCitaEstadoImcumpliento = EJBcita.obtenerCitaPorEstilistaEstadoIncumpliento(idEstilistaa);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
