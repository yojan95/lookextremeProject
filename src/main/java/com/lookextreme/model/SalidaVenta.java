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
@Table(name = "salida_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalidaVenta.findAll", query = "SELECT s FROM SalidaVenta s"),
    @NamedQuery(name = "SalidaVenta.findByIdSalidaventa", query = "SELECT s FROM SalidaVenta s WHERE s.idSalidaventa = :idSalidaventa"),
    @NamedQuery(name = "SalidaVenta.findByPrecioUnitario", query = "SELECT s FROM SalidaVenta s WHERE s.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "SalidaVenta.findByNombre", query = "SELECT s FROM SalidaVenta s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SalidaVenta.findByCantidad", query = "SELECT s FROM SalidaVenta s WHERE s.cantidad = :cantidad"),
    @NamedQuery(name = "SalidaVenta.findByTotal", query = "SELECT s FROM SalidaVenta s WHERE s.total = :total"),
    @NamedQuery(name = "SalidaVenta.findByColor", query = "SELECT s FROM SalidaVenta s WHERE s.color = :color"),
    @NamedQuery(name = "SalidaVenta.findByTama\u00f1o", query = "SELECT s FROM SalidaVenta s WHERE s.tama\u00f1o = :tama\u00f1o")})
public class SalidaVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSalida_venta")
    private Integer idSalidaventa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PrecioUnitario")
    private int precioUnitario;
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
    @Column(name = "Total")
    private int total;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "color")
    private String color;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tama\u00f1o")
    private short tamaño;
    @JoinColumn(name = "productos_idCodigo", referencedColumnName = "idCodigo")
    @ManyToOne(optional = false)
    private Productos productosidCodigo;

    public SalidaVenta() {
    }

    public SalidaVenta(Integer idSalidaventa) {
        this.idSalidaventa = idSalidaventa;
    }

    public SalidaVenta(Integer idSalidaventa, int precioUnitario, String nombre, short cantidad, int total, String color, short tamaño) {
        this.idSalidaventa = idSalidaventa;
        this.precioUnitario = precioUnitario;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.total = total;
        this.color = color;
        this.tamaño = tamaño;
    }

    public Integer getIdSalidaventa() {
        return idSalidaventa;
    }

    public void setIdSalidaventa(Integer idSalidaventa) {
        this.idSalidaventa = idSalidaventa;
    }

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public short getTamaño() {
        return tamaño;
    }

    public void setTamaño(short tamaño) {
        this.tamaño = tamaño;
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
        hash += (idSalidaventa != null ? idSalidaventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalidaVenta)) {
            return false;
        }
        SalidaVenta other = (SalidaVenta) object;
        if ((this.idSalidaventa == null && other.idSalidaventa != null) || (this.idSalidaventa != null && !this.idSalidaventa.equals(other.idSalidaventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lookextreme.model.SalidaVenta[ idSalidaventa=" + idSalidaventa + " ]";
    }
    
}
