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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "tipo_pqrs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPqrs.findAll", query = "SELECT t FROM TipoPqrs t"),
    @NamedQuery(name = "TipoPqrs.findByIdTipoPQRS", query = "SELECT t FROM TipoPqrs t WHERE t.idTipoPQRS = :idTipoPQRS"),
    @NamedQuery(name = "TipoPqrs.findByTipoPQRS", query = "SELECT t FROM TipoPqrs t WHERE t.tipoPQRS = :tipoPQRS")})
public class TipoPqrs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipo_PQRS")
    private Short idTipoPQRS;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Tipo_PQRS")
    private String tipoPQRS;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipopqrsidTipoPQRS")
    private List<Pqrs> pqrsList;

    public TipoPqrs() {
    }

    public TipoPqrs(Short idTipoPQRS) {
        this.idTipoPQRS = idTipoPQRS;
    }

    public TipoPqrs(Short idTipoPQRS, String tipoPQRS) {
        this.idTipoPQRS = idTipoPQRS;
        this.tipoPQRS = tipoPQRS;
    }

    public Short getIdTipoPQRS() {
        return idTipoPQRS;
    }

    public void setIdTipoPQRS(Short idTipoPQRS) {
        this.idTipoPQRS = idTipoPQRS;
    }

    public String getTipoPQRS() {
        return tipoPQRS;
    }

    public void setTipoPQRS(String tipoPQRS) {
        this.tipoPQRS = tipoPQRS;
    }

    @XmlTransient
    public List<Pqrs> getPqrsList() {
        return pqrsList;
    }

    public void setPqrsList(List<Pqrs> pqrsList) {
        this.pqrsList = pqrsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPQRS != null ? idTipoPQRS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPqrs)) {
            return false;
        }
        TipoPqrs other = (TipoPqrs) object;
        if ((this.idTipoPQRS == null && other.idTipoPQRS != null) || (this.idTipoPQRS != null && !this.idTipoPQRS.equals(other.idTipoPQRS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lookextreme.model.TipoPqrs[ idTipoPQRS=" + idTipoPQRS + " ]";
    }
    
}
