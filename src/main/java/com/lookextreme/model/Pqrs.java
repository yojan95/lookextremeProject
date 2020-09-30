/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hoore
 */
@Entity
@Table(name = "pqrs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pqrs.findAll", query = "SELECT p FROM Pqrs p"),
    @NamedQuery(name = "Pqrs.findByIdPQRS", query = "SELECT p FROM Pqrs p WHERE p.idPQRS = :idPQRS"),
    @NamedQuery(name = "Pqrs.findByFechaHoraCreacion", query = "SELECT p FROM Pqrs p WHERE p.fechaHoraCreacion = :fechaHoraCreacion"),
    @NamedQuery(name = "Pqrs.findByDetalles", query = "SELECT p FROM Pqrs p WHERE p.detalles = :detalles"),
    @NamedQuery(name = "Pqrs.findByEstado", query = "SELECT p FROM Pqrs p WHERE p.estado = :estado"),
    @NamedQuery(name = "Pqrs.findByRespuesta", query = "SELECT p FROM Pqrs p WHERE p.respuesta = :respuesta"),
    @NamedQuery(name = "Pqrs.findByAsunto", query = "SELECT p FROM Pqrs p WHERE p.asunto = :asunto")})
public class Pqrs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPQRS")
    private Integer idPQRS;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaHoraCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;
    @Lob
    @Column(name = "Anexos")
    private byte[] anexos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "Detalles")
    private String detalles;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Estado")
    private String estado;
    @Size(max = 300)
    @Column(name = "Respuesta")
    private String respuesta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Asunto")
    private String asunto;
    @JoinColumn(name = "administrador_usuario_idUsuario", referencedColumnName = "usuario_idUsuario")
    @ManyToOne
    private Administrador administradorusuarioidUsuario;
    @JoinColumn(name = "cliente_usuario_idUsuario", referencedColumnName = "usuario_idUsuario")
    @ManyToOne(optional = false)
    private Cliente clienteusuarioidUsuario;
    @JoinColumn(name = "tipo_pqrs_idTipo_PQRS", referencedColumnName = "idTipo_PQRS")
    @ManyToOne(optional = false)
    private TipoPqrs tipopqrsidTipoPQRS;

    public Pqrs() {
    }

    public Pqrs(Integer idPQRS) {
        this.idPQRS = idPQRS;
    }

    public Pqrs(Integer idPQRS, Date fechaHoraCreacion, String detalles, String estado, String asunto) {
        this.idPQRS = idPQRS;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.detalles = detalles;
        this.estado = estado;
        this.asunto = asunto;
    }

    public Integer getIdPQRS() {
        return idPQRS;
    }

    public void setIdPQRS(Integer idPQRS) {
        this.idPQRS = idPQRS;
    }

    public Date getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Date fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public byte[] getAnexos() {
        return anexos;
    }

    public void setAnexos(byte[] anexos) {
        this.anexos = anexos;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Administrador getAdministradorusuarioidUsuario() {
        return administradorusuarioidUsuario;
    }

    public void setAdministradorusuarioidUsuario(Administrador administradorusuarioidUsuario) {
        this.administradorusuarioidUsuario = administradorusuarioidUsuario;
    }

    public Cliente getClienteusuarioidUsuario() {
        return clienteusuarioidUsuario;
    }

    public void setClienteusuarioidUsuario(Cliente clienteusuarioidUsuario) {
        this.clienteusuarioidUsuario = clienteusuarioidUsuario;
    }

    public TipoPqrs getTipopqrsidTipoPQRS() {
        return tipopqrsidTipoPQRS;
    }

    public void setTipopqrsidTipoPQRS(TipoPqrs tipopqrsidTipoPQRS) {
        this.tipopqrsidTipoPQRS = tipopqrsidTipoPQRS;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPQRS != null ? idPQRS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pqrs)) {
            return false;
        }
        Pqrs other = (Pqrs) object;
        if ((this.idPQRS == null && other.idPQRS != null) || (this.idPQRS != null && !this.idPQRS.equals(other.idPQRS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lookextreme.model.Pqrs[ idPQRS=" + idPQRS + " ]";
    }
    
}
