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
@Table(name = "salida_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalidaVenta.findAll", query = "SELECT s FROM SalidaVenta s"),
    @NamedQuery(name = "SalidaVenta.findByIdSalidaventa", query = "SELECT s FROM SalidaVenta s WHERE s.idSalidaventa = :idSalidaventa"),
    @NamedQuery(name = "SalidaVenta.findByNombre", query = "SELECT s FROM SalidaVenta s WHERE s.estado = :estado"),
    @NamedQuery(name = "SalidaVenta.findByCantidad", query = "SELECT s FROM SalidaVenta s WHERE s.cantidad = :cantidad"),
    @NamedQuery(name = "SalidaVenta.findByTotal", query = "SELECT s FROM SalidaVenta s WHERE s.total = :total"),
    @NamedQuery(name = "SalidaVenta.findByColor", query = "SELECT s FROM SalidaVenta s WHERE s.fechaSalidaVenta = :fechaSalidaVenta")})
public class SalidaVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSalida_venta")
    private Integer idSalidaventa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad")
    private Integer cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Total")
    private Integer total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaSalidaVenta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalidaVenta;
    @JoinColumn(name = "productos_idCodigo", referencedColumnName = "idCodigo")
    @ManyToOne(optional = false)
    private Productos productosidCodigo;

    public SalidaVenta() {
    }

    public SalidaVenta(Integer idSalidaventa) {
        this.idSalidaventa = idSalidaventa;
    }

    public SalidaVenta(Integer idSalidaventa,String estado, Integer cantidad, Integer total, Date fechaSalidaVenta) {
        this.idSalidaventa = idSalidaventa;
        this.estado = estado;
        this.cantidad = cantidad;
        this.total = total;
        this.fechaSalidaVenta = fechaSalidaVenta;
       
    }

    public Integer getIdSalidaventa() {
        return idSalidaventa;
    }

    public void setIdSalidaventa(Integer idSalidaventa) {
        this.idSalidaventa = idSalidaventa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getFechaSalidaVenta() {
        return fechaSalidaVenta;
    }

    public void setFechaSalidaVenta(Date fechaSalidaVenta) {
        this.fechaSalidaVenta = fechaSalidaVenta;
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
