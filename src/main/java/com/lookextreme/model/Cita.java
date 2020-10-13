/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hoore
 */
@Entity
@Table(name = "cita")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c"),
    @NamedQuery(name = "Cita.findByIdCita", query = "SELECT c FROM Cita c WHERE c.idCita = :idCita"),
    @NamedQuery(name = "Cita.findByFecha", query = "SELECT c FROM Cita c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Cita.findByEstado", query = "SELECT c FROM Cita c WHERE c.estado = :estado"),
    @NamedQuery(name = "Cita.findByHora", query = "SELECT c FROM Cita c WHERE c.hora = :hora"),
    @NamedQuery(name = "Cliente.findbyCita", query ="SELECT c FROM Cita c WHERE c.clienteusuarioidUsuario.usuario.idUsuario = :idUsuario")})
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCita")
    private Integer idCita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "hora")
    private String hora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citaidCita")
    private List<ServiciosCitas> serviciosCitasList;
    @JoinColumn(name = "cliente_usuario_idUsuario", referencedColumnName = "usuario_idUsuario")
    @ManyToOne(optional = false)
    private Cliente clienteusuarioidUsuario;
    @JoinColumn(name = "estilista_usuario_idUsuario", referencedColumnName = "usuario_idUsuario")
    @ManyToOne(optional = false)
    private Estilista estilistausuarioidUsuario;

    public Cita() {
    }

    public Cita(Integer idCita) {
        this.idCita = idCita;
    }

    public Cita(Integer idCita, Date fecha, String estado, String hora) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.estado = estado;
        this.hora = hora;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @XmlTransient
    public List<ServiciosCitas> getServiciosCitasList() {
        return serviciosCitasList;
    }

    public void setServiciosCitasList(List<ServiciosCitas> serviciosCitasList) {
        this.serviciosCitasList = serviciosCitasList;
    }

    public Cliente getClienteusuarioidUsuario() {
        return clienteusuarioidUsuario;
    }

    public void setClienteusuarioidUsuario(Cliente clienteusuarioidUsuario) {
        this.clienteusuarioidUsuario = clienteusuarioidUsuario;
    }

    public Estilista getEstilistausuarioidUsuario() {
        return estilistausuarioidUsuario;
    }

    public void setEstilistausuarioidUsuario(Estilista estilistausuarioidUsuario) {
        this.estilistausuarioidUsuario = estilistausuarioidUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCita != null ? idCita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.idCita == null && other.idCita != null) || (this.idCita != null && !this.idCita.equals(other.idCita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lookextreme.model.Cita[ idCita=" + idCita + " ]";
    }
    
}
