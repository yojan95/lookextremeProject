package com.lookextreme.controller;

import com.lookextreme.Dao.CitaFacadeLocal;
import com.lookextreme.Dao.ClienteFacadeLocal;
import com.lookextreme.Dao.EstilistaFacadeLocal;
import com.lookextreme.Dao.ServiciosCitasFacadeLocal;
import com.lookextreme.Dao.ServiciosFacadeLocal;
import com.lookextreme.Dao.UsuarioFacadeLocal;
import com.lookextreme.model.Cita;
import com.lookextreme.model.Cliente;
import com.lookextreme.model.Estilista;
import com.lookextreme.model.HorarioDisponibilidad;
import com.lookextreme.model.Roles;
import com.lookextreme.model.Servicios;
import com.lookextreme.model.ServiciosCitas;
import com.lookextreme.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named
@SessionScoped
public class CitaController implements Serializable {

    @EJB
    private CitaFacadeLocal EJBcita;
    private Cita cita;
    private List<Cita> citalistVerificarDispo;
    private List<Cita> busquedaCitaList;
    @EJB
    private EstilistaFacadeLocal EJBestilista;
    private Estilista estilista;
    private List<SelectItem> estilistaListItem;

    @EJB
    private UsuarioFacadeLocal EJBusuario;
    private Usuario usuario;

    @EJB
    private ServiciosFacadeLocal serviciosEJB;
    private Servicios servicios;
    private List<Servicios> serviciosList;
    private List<Servicios> carrito;
    @EJB
    private ServiciosCitasFacadeLocal serviciosCitasEJB;
    private ServiciosCitas serviciosCitas;

    @EJB
    private ClienteFacadeLocal clienteEJB;
    private Cliente cliente;
    private String estado;
    private List<SelectItem> horarioListItem;

    public List<SelectItem> getHorarioListItem() {
        return horarioListItem;
    }

    public void setHorarioListItem(List<SelectItem> horarioListItem) {
        this.horarioListItem = horarioListItem;
    }

    public List<Cita> getCitalistVerificarDispo() {
        return citalistVerificarDispo;
    }

    public void setCitalistVerificarDispo(List<Cita> citalistVerificarDispo) {
        this.citalistVerificarDispo = citalistVerificarDispo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Cita> getBusquedaCitaList() {

        return busquedaCitaList;
    }

    public void setBusquedaCitaList(List<Cita> busquedaCitaList) {
        this.busquedaCitaList = busquedaCitaList;
    }

    public Servicios getServicios() {
        return servicios;
    }

    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }

    public List<Servicios> getServiciosList() {
        serviciosList = serviciosEJB.findAll();
        return serviciosList;
    }

    public void setServiciosList(List<Servicios> serviciosList) {
        this.serviciosList = serviciosList;
    }

    public List<Servicios> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<Servicios> carrito) {
        this.carrito = carrito;
    }

    public List<SelectItem> getEstilistaListItem() {
        return estilistaListItem;
    }

    public void setEstilistaListItem(List<SelectItem> estilistaListItem) {
        this.estilistaListItem = estilistaListItem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        servicios = new Servicios();
        serviciosCitas = new ServiciosCitas();
        cliente = new Cliente();
        estilista = new Estilista();
        carrito = new ArrayList();
        usuario = new Usuario();
        listarEstilistas();
    }

    public void registrarCita() throws Exception {
        System.out.println("Cita_registrada");
        try {
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            cliente.setUsuarioidUsuario(usuario.getIdUsuario());
            cita.setClienteusuarioidUsuario(cliente);
            cita.setEstilistausuarioidUsuario(estilista);
            cita.setEstado("agendada");
            serviciosCitas.setServiciosidServicios(servicios);
            serviciosCitas.setCitaidCita(cita);
            serviciosCitas.setIdServiciocita(1);
            EJBcita.create(cita);
            insertarServicioCita(cita);
            System.out.println(cita.getIdCita());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro su cita"));

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    /*
    =================
    lista los estilistas mediante un selectItem y muestralos por el nombre obteniendo su rol 
    =================
     */

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

    /*
    =================
    agrega un servicio mediante un arraylist al objeto List<Servicios> carrito.
    =================
     */

    public List<Servicios> agregarServicio(List<Servicios> carrito, Servicios se) {
        carrito.add(se);
        return carrito;
    }

    public void actionAgregarServicio(Servicios se) {
        try {
            carrito = agregarServicio(carrito, se);
            System.out.println("" + se);
            System.out.println(carrito.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String irAlaCita() {
        String agendar = null;
        agendar = "cliente-disponibilidad";
        return agendar;
    }

    /*
    =================
    obtener el servicio del carrito y agregarlo a ala tabla
    serviciosCitas.
    =================
     */
    public void insertarServicioCita(Cita cita) {
        try {
            for (Servicios servicio : carrito) {
                serviciosCitas = new ServiciosCitas();
                serviciosCitas.setIdServiciocita(1);
                serviciosCitas.setServiciosidServicios(servicio);
                serviciosCitas.setCitaidCita(cita);
                serviciosCitasEJB.create(serviciosCitas);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    =================
    Haz esto si, quieres eliminar el servicio agregado al carrito.
    =================
     */
    public void eliminarServicioCarrito(int idServicio) {

        try {
            if (!carrito.isEmpty()) {
                int i = 0;
                for (Servicios servicio : carrito) {
                    if (servicio.getIdServicios() == idServicio) {
                        carrito.remove(i);
                        break;
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actionEliminarServicioCarrito(Servicios servicio) {

        try {
            eliminarServicioCarrito(servicio.getIdServicios());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actionCancelarCita(Cita cita) {
        try {
            cita.setEstado("cancelada");
            EJBcita.edit(cita);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Cita cancelada"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actionIncumpliCitaEstilista(Cita cita) {
        try {
            cita.setEstado("Incumplida");
            EJBcita.edit(cita);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isPostBack() {
        boolean rpta;
        rpta = FacesContext.getCurrentInstance().isPostback();

        return rpta;
    }

    public void verificarDisponibilidad() {

        try {
            if (cita.getFecha().getTime() > 0 && estilista.getUsuarioidUsuario() > 0) {
                List<HorarioDisponibilidad> horarios = EJBcita.verificarDisponibilidad(estilista.getUsuarioidUsuario(), cita.getFecha());
                horarioListItem = new ArrayList();
                for (HorarioDisponibilidad horario : horarios) {
                    SelectItem item = new SelectItem(horario.getHora(), Integer.toString(horario.getHora()));
                    if ("ocupado".equals(horario.getEstado().toLowerCase())) 
                        item.setDisabled(true);    
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    public void buscarEstadoCliente(){
        try{
            
         busquedaCitaList = EJBcita.buscarEstadoCliente(usuario.getIdUsuario(), estado);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
     */
}
