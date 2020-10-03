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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hoore
 */
@Entity
@Table(name = "horario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByIdHorario", query = "SELECT h FROM Horario h WHERE h.idHorario = :idHorario"),
    @NamedQuery(name = "Horario.findByDias", query = "SELECT h FROM Horario h WHERE h.dias = :dias"),
    @NamedQuery(name = "Horario.findByHoraDeInicio", query = "SELECT h FROM Horario h WHERE h.horaDeInicio = :horaDeInicio"),
    @NamedQuery(name = "Horario.findByHoraFin", query = "SELECT h FROM Horario h WHERE h.horaFin = :horaFin")})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHorario")
    private Integer idHorario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Dias")
    private short dias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "HoraDeInicio")
    private String horaDeInicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "HoraFin")
    private String horaFin;
    @JoinColumn(name = "estilista_usuario_idUsuario", referencedColumnName = "usuario_idUsuario")
    @ManyToOne(optional = false)
    private Estilista estilistausuarioidUsuario;

    public Horario() {
    }

    public Horario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Horario(Integer idHorario, short dias, String horaDeInicio, String horaFin) {
        this.idHorario = idHorario;
        this.dias = dias;
        this.horaDeInicio = horaDeInicio;
        this.horaFin = horaFin;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public short getDias() {
        return dias;
    }

    public void setDias(short dias) {
        this.dias = dias;
    }

    public String getHoraDeInicio() {
        return horaDeInicio;
    }

    public void setHoraDeInicio(String horaDeInicio) {
        this.horaDeInicio = horaDeInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
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
        hash += (idHorario != null ? idHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.idHorario == null && other.idHorario != null) || (this.idHorario != null && !this.idHorario.equals(other.idHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lookextreme.model.Horario[ idHorario=" + idHorario + " ]";
    }
    
}
