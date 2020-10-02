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
@Table(name = "salida_por_consumo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalidaPorConsumo.findAll", query = "SELECT s FROM SalidaPorConsumo s"),
    @NamedQuery(name = "SalidaPorConsumo.findByIdSalidaporconsumo", query = "SELECT s FROM SalidaPorConsumo s WHERE s.idSalidaporconsumo = :idSalidaporconsumo"),
    @NamedQuery(name = "SalidaPorConsumo.findByNombre", query = "SELECT s FROM SalidaPorConsumo s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SalidaPorConsumo.findByCantidad", query = "SELECT s FROM SalidaPorConsumo s WHERE s.cantidad = :cantidad"),
    @NamedQuery(name = "SalidaPorConsumo.findByTama\u00f1o", query = "SELECT s FROM SalidaPorConsumo s WHERE s.tama\u00f1o = :tama\u00f1o"),
    @NamedQuery(name = "SalidaPorConsumo.findByColor", query = "SELECT s FROM SalidaPorConsumo s WHERE s.color = :color")})
public class SalidaPorConsumo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSalida_por_consumo")
    private Integer idSalidaporconsumo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad")
    private short cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tama\u00f1o")
    private short tamaño;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "color")
    private String color;
    @JoinColumn(name = "estilista_usuario_idUsuario", referencedColumnName = "usuario_idUsuario")
    @ManyToOne(optional = false)
    private Estilista estilistausuarioidUsuario;
    @JoinColumn(name = "productos_idCodigo", referencedColumnName = "idCodigo")
    @ManyToOne(optional = false)
    private Productos productosidCodigo;

    public SalidaPorConsumo() {
    }

    public SalidaPorConsumo(Integer idSalidaporconsumo) {
        this.idSalidaporconsumo = idSalidaporconsumo;
    }

    public SalidaPorConsumo(Integer idSalidaporconsumo, String nombre, short cantidad, short tamaño, String color) {
        this.idSalidaporconsumo = idSalidaporconsumo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tamaño = tamaño;
        this.color = color;
    }

    public Integer getIdSalidaporconsumo() {
        return idSalidaporconsumo;
    }

    public void setIdSalidaporconsumo(Integer idSalidaporconsumo) {
        this.idSalidaporconsumo = idSalidaporconsumo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }

    public short getTamaño() {
        return tamaño;
    }

    public void setTamaño(short tamaño) {
        this.tamaño = tamaño;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Estilista getEstilistausuarioidUsuario() {
        return estilistausuarioidUsuario;
    }

    public void setEstilistausuarioidUsuario(Estilista estilistausuarioidUsuario) {
        this.estilistausuarioidUsuario = estilistausuarioidUsuario;
    }

    public Productos getProductosidCodigo() {
        return productosidCodigo;
    }

    public void setProductosidCodigo(Productos productosidCodigo) {
        this.productosidCodigo = productosidCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSalidaporconsumo != null ? idSalidaporconsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalidaPorConsumo)) {
            return false;
        }
        SalidaPorConsumo other = (SalidaPorConsumo) object;
        if ((this.idSalidaporconsumo == null && other.idSalidaporconsumo != null) || (this.idSalidaporconsumo != null && !this.idSalidaporconsumo.equals(other.idSalidaporconsumo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lookextreme.model.SalidaPorConsumo[ idSalidaporconsumo=" + idSalidaporconsumo + " ]";
    }
    
}
