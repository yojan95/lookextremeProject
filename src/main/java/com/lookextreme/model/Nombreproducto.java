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
@Table(name = "nombreproducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nombreproducto.findAll", query = "SELECT n FROM Nombreproducto n"),
    @NamedQuery(name = "Nombreproducto.findByIdNombreProducto", query = "SELECT n FROM Nombreproducto n WHERE n.idNombreProducto = :idNombreProducto"),
    @NamedQuery(name = "Nombreproducto.findByNombreProductocol", query = "SELECT n FROM Nombreproducto n WHERE n.nombreProductocol = :nombreProductocol")})
public class Nombreproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNombreProducto")
    private Integer idNombreProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NombreProductocol")
    private String nombreProductocol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nombreProductoidNombreProducto")
    private List<Productos> productosList;

    public Nombreproducto() {
    }

    public Nombreproducto(Integer idNombreProducto) {
        this.idNombreProducto = idNombreProducto;
    }

    public Nombreproducto(Integer idNombreProducto, String nombreProductocol) {
        this.idNombreProducto = idNombreProducto;
        this.nombreProductocol = nombreProductocol;
    }

    public Integer getIdNombreProducto() {
        return idNombreProducto;
    }

    public void setIdNombreProducto(Integer idNombreProducto) {
        this.idNombreProducto = idNombreProducto;
    }

    public String getNombreProductocol() {
        return nombreProductocol;
    }

    public void setNombreProductocol(String nombreProductocol) {
        this.nombreProductocol = nombreProductocol;
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
        hash += (idNombreProducto != null ? idNombreProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nombreproducto)) {
            return false;
        }
        Nombreproducto other = (Nombreproducto) object;
        if ((this.idNombreProducto == null && other.idNombreProducto != null) || (this.idNombreProducto != null && !this.idNombreProducto.equals(other.idNombreProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lookextreme.model.Nombreproducto[ idNombreProducto=" + idNombreProducto + " ]";
    }
    
}
