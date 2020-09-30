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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alexis
 */
@Entity
@Table(name = "categorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c"),
    @NamedQuery(name = "Categorias.findByIdcategorias", query = "SELECT c FROM Categorias c WHERE c.idcategorias = :idcategorias"),
    @NamedQuery(name = "Categorias.findByCaNombre", query = "SELECT c FROM Categorias c WHERE c.caNombre = :caNombre")})
public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcategorias")
    private Integer idcategorias;
    @Size(max = 45)
    @Column(name = "CaNombre")
    private String caNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriasIdcategorias")
    private List<Productos> productosList;

    public Categorias() {
    }

    public Categorias(Integer idcategorias) {
        this.idcategorias = idcategorias;
    }

    public Integer getIdcategorias() {
        return idcategorias;
    }

    public void setIdcategorias(Integer idcategorias) {
        this.idcategorias = idcategorias;
    }

    public String getCaNombre() {
        return caNombre;
    }

    public void setCaNombre(String caNombre) {
        this.caNombre = caNombre;
    }

    @XmlTransient
    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcategorias != null ? idcategorias.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorias)) {
            return false;
        }
        Categorias other = (Categorias) object;
        if ((this.idcategorias == null && other.idcategorias != null) || (this.idcategorias != null && !this.idcategorias.equals(other.idcategorias))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lookextreme.model.Categorias[ idcategorias=" + idcategorias + " ]";
    }
    
}
