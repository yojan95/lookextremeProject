/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alexis
 */
@Entity
@Table(name = "estilista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estilista.findAll", query = "SELECT e FROM Estilista e"),
    @NamedQuery(name = "Estilista.findByEspecialidades", query = "SELECT e FROM Estilista e WHERE e.especialidades = :especialidades"),
    @NamedQuery(name = "Estilista.findByUsuarioidUsuario", query = "SELECT e FROM Estilista e WHERE e.usuarioidUsuario = :usuarioidUsuario")})
public class Estilista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "especialidades")
    private String especialidades;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_idUsuario")
    private Integer usuarioidUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estilistausuarioidUsuario")
    private List<Horario> horarioList;
    @JoinColumn(name = "usuario_idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estilistausuarioidUsuario")
    private List<SalidaPorConsumo> salidaPorConsumoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estilistausuarioidUsuario")
    private List<Cita> citaList;

    public Estilista() {
    }

    public Estilista(Integer usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
    }

    public Estilista(Integer usuarioidUsuario, String especialidades) {
        this.usuarioidUsuario = usuarioidUsuario;
        this.especialidades = especialidades;
    }

    public String getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(String especialidades) {
        this.especialidades = especialidades;
    }

    public Integer getUsuarioidUsuario() {
        return usuarioidUsuario;
    }

    public void setUsuarioidUsuario(Integer usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
    }

    @XmlTransient
    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public List<SalidaPorConsumo> getSalidaPorConsumoList() {
        return salidaPorConsumoList;
    }

    public void setSalidaPorConsumoList(List<SalidaPorConsumo> salidaPorConsumoList) {
        this.salidaPorConsumoList = salidaPorConsumoList;
    }

    @XmlTransient
    public List<Cita> getCitaList() {
        return citaList;
    }

    public void setCitaList(List<Cita> citaList) {
        this.citaList = citaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioidUsuario != null ? usuarioidUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estilista)) {
            return false;
        }
        Estilista other = (Estilista) object;
        if ((this.usuarioidUsuario == null && other.usuarioidUsuario != null) || (this.usuarioidUsuario != null && !this.usuarioidUsuario.equals(other.usuarioidUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lookextreme.model.Estilista[ usuarioidUsuario=" + usuarioidUsuario + " ]";
    }
    
}
