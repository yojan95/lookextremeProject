package com.lookextreme.controller;

import com.lookextreme.Dao.CitaFacadeLocal;
import com.lookextreme.Dao.ClienteFacadeLocal;
import com.lookextreme.Dao.EstilistaFacadeLocal;
import com.lookextreme.Dao.HorarioFacadeLocal;
import com.lookextreme.Dao.ServiciosCitasFacadeLocal;
import com.lookextreme.Dao.ServiciosFacadeLocal;
import com.lookextreme.Dao.UsuarioFacadeLocal;
import com.lookextreme.model.Cita;
import com.lookextreme.model.Cliente;
import com.lookextreme.model.Estilista;
import com.lookextreme.model.Horario;
import com.lookextreme.model.HorarioDisponibilidad;
import com.lookextreme.model.Roles;
import com.lookextreme.model.Servicios;
import com.lookextreme.model.ServiciosCitas;
import com.lookextreme.model.Usuario;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@Named
@SessionScoped
public class CitaController implements Serializable {

    @EJB
    private CitaFacadeLocal EJBcita;
    private Cita cita;
    private List<Cita> citalistVerificarDispo;
    private List<Cita> busquedaCitaList;
    private List<ServiciosCitas> listaCitaPorFechas;
    private Date inicioFecha, finFecha;
    private String FechaNoValida;
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
    private Integer totalServicio;
    @EJB
    private ServiciosCitasFacadeLocal serviciosCitasEJB;
    private ServiciosCitas serviciosCitas;

    @EJB
    private ClienteFacadeLocal clienteEJB;
    private Cliente cliente;
    private String estado;
    private List<SelectItem> horarioListItem;

    @EJB
    private HorarioFacadeLocal horarioEJB;
    private Horario horarioE;
    private List<Horario> listHorario;
    
    
    public List<Horario> getListHorario() {
        return listHorario;
    }

    public void setListHorario(List<Horario> listHorario) {
        this.listHorario = listHorario;
    }

    public String getFechaNoValida() {
        return FechaNoValida;
    }

    public void setFechaNoValida(String FechaNoValida) {
        this.FechaNoValida = FechaNoValida;
    }

    public List<ServiciosCitas> getListaCitaPorFechas() {
        return listaCitaPorFechas;
    }

    public void setListaCitaPorFechas(List<ServiciosCitas> listaCitaPorFechas) {
        this.listaCitaPorFechas = listaCitaPorFechas;
    }

    public Date getInicioFecha() {
        return inicioFecha;
    }

    public void setInicioFecha(Date inicioFecha) {
        this.inicioFecha = inicioFecha;
    }

    public Date getFinFecha() {
        return finFecha;
    }

    public void setFinFecha(Date finFecha) {
        this.finFecha = finFecha;
    }

    public Integer getTotalServicio() {
        return totalServicio;
    }

    public void setTotalServicio(Integer totalServicio) {
        this.totalServicio = totalServicio;
    }

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

    public Horario getHorarioE() {
        return horarioE;
    }

    public void setHorarioE(Horario horarioE) {
        this.horarioE = horarioE;
    }

    //constructor
    @PostConstruct
    public void init() {
        cita = new Cita();
        servicios = new Servicios();
        serviciosCitas = new ServiciosCitas();
        cliente = new Cliente();
        estilista = new Estilista();
        carrito = new ArrayList();
        usuario = new Usuario();
        horarioE = new Horario();
        listarEstilistas();

    }

    public String registrarCita() throws Exception {
        String redireccionar = null;
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(new Date());
        Date diaAtual = new Date();
        try {
            if (cita.getFecha().after(diaAtual)) {
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
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro su cita"));
                redireccionar = "cliente-citaRegistradaExito";
                cita = new Cita();
                carrito = new ArrayList<>();
                totalServicio = 0;
                horarioListItem = new ArrayList<>();
            } else {
                System.out.println("fecha no valida1");
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('wdialog1').show();");
            }

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return redireccionar;
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
        if (carrito.size() < 1) {
            carrito.add(se);
        } else {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('wdialog1').show();");
        }

        return carrito;
    }

    public void actionAgregarServicio(Servicios se) {
        try {
            carrito = agregarServicio(carrito, se);
            System.out.println("" + se);
            System.out.println(carrito.size());
            totalServicio = calcularTotalServicios(carrito);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Aviso", "Servicio agregado"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String validarServicio() {
        System.out.println("validando servicio");
        String agendar = null;
        Integer servicioAgregado = 1;

        if (!carrito.isEmpty()) {
            if (carrito.size() <= 1) {
                agendar = "cliente-disponibilidad";
            } else {
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('wdialog1').show();");
            }

        } else {
            System.out.println("seleccione un servicio");
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('wdialog').show();");
        }
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
            totalServicio = calcularTotalServicios(carrito);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actionCancelarCita(Cita cita) {
        try {
            cita.setEstado("Cancelada");
            EJBcita.edit(cita);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Cita cancelada"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actionIncumpliCitaEstilista(Cita cita) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, 21);
        try {
            System.out.println("hora actual" + cal);
            if (cita.getFecha().after(cal.getTime())) {
                cita.setEstado("Incumplida");
                EJBcita.edit(cita);
            } else {
                System.out.println("todavia no puede ejecutar esta accion");
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('wdialog').show();");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    =================
    Verificar la disponibilidad de la cita y valida el rango de la fecha que este estre un maximo
    15 dias para poder agendar
    =================
     */
    public void verificarDisponibilidad() {
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(new Date());
        fecha.add(Calendar.HOUR_OF_DAY, -5);
        fecha.add(Calendar.DATE, 15);

        try {
            if (cita.getFecha().before(fecha.getTime())) {

                if (estilista.getUsuarioidUsuario() > 0) {
                    List<HorarioDisponibilidad> horarios = EJBcita.verificarDisponibilidad(estilista.getUsuarioidUsuario(), cita.getFecha());
                    horarioListItem = new ArrayList();
                    for (HorarioDisponibilidad horario : horarios) {
                        if ("libre".equals(horario.getEstado().toLowerCase())) {
                            horarioListItem.add(new SelectItem(horario.getHora() + ":00:00", Integer.toString(horario.getHora()) + ":00:00"));

                        }
                    }
                }
            } else {
                System.out.println("fecha no valida");
                FechaNoValida = "La fecha limite para buscar una cita es de 15 dias";
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('wdialog2').show();");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Integer calcularTotalServicios(List<Servicios> carrito) {
        System.out.println("calculando");
        Integer suma = 0;

        try {
            for (Servicios ser : carrito) {
                suma += ser.getPrecio();
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }

        return suma;
    }

   
    public void citasPorFechasEstilista() {
        try {
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            listaCitaPorFechas = EJBcita.buscarCitaFecha(inicioFecha, finFecha, usuario.getIdUsuario());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String volverAlInicio() {
        String inicio = null;
        inicio = "indexCliente";
        return inicio;
    }

    public boolean renderizarBotonRegistrar() {
        //Date diaActual = new Date();
        return cita.getFecha() != null;
    }
    
}
