/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hoore
 */
@Entity
@Table(name = "servicios_citas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiciosCitas.findAll", query = "SELECT s FROM ServiciosCitas s"),
    @NamedQuery(name = "ServiciosCitas.findByIdServiciocita", query = "SELECT s FROM ServiciosCitas s WHERE s.idServiciocita = :idServiciocita"),
     @NamedQuery(name = "ServiciosCitas.findByIdCliente", query = "SELECT s FROM ServiciosCitas s WHERE s.citaidCita.clienteusuarioidUsuario.usuario.idUsuario = :idUsuario"),
    @NamedQuery(name = "ServiciosCitas.findEstilista", query = "SELECT s FROM ServiciosCitas s WHERE s.citaidCita.fecha = :idUsuario")})
public class ServiciosCitas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idServicio_cita")
    private Integer idServiciocita;
    @JoinColumn(name = "cita_idCita", referencedColumnName = "idCita")
    @ManyToOne(optional = false)
    private Cita citaidCita;
    @JoinColumn(name = "servicios_idServicios", referencedColumnName = "idServicios")
    @ManyToOne(optional = false)
    private Servicios serviciosidServicios;

    public ServiciosCitas() {
    }

    public ServiciosCitas(Integer idServiciocita) {
        this.idServiciocita = idServiciocita;
    }

    public Integer getIdServiciocita() {
        return idServiciocita;
    }

    public void setIdServiciocita(Integer idServiciocita) {
        this.idServiciocita = idServiciocita;
    }

    public Cita getCitaidCita() {
        return citaidCita;
    }

    public void setCitaidCita(Cita citaidCita) {
        this.citaidCita = citaidCita;
    }

    public Servicios getServiciosidServicios() {
        return serviciosidServicios;
    }

    public void setServiciosidServicios(Servicios serviciosidServicios) {
        this.serviciosidServicios = serviciosidServicios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServiciocita != null ? idServiciocita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiciosCitas)) {
            return false;
        }
        ServiciosCitas other = (ServiciosCitas) object;
        if ((this.idServiciocita == null && other.idServiciocita != null) || (this.idServiciocita != null && !this.idServiciocita.equals(other.idServiciocita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lookextreme.model.ServiciosCitas[ idServiciocita=" + idServiciocita + " ]";
    }
    
}
