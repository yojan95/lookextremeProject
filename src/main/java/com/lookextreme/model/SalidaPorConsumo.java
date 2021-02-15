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
@Table(name = "salida_por_consumo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalidaPorConsumo.findAll", query = "SELECT s FROM SalidaPorConsumo s"),
    @NamedQuery(name = "SalidaPorConsumo.findByIdSalidaporconsumo", query = "SELECT s FROM SalidaPorConsumo s WHERE s.idSalidaporconsumo = :idSalidaporconsumo"),
    @NamedQuery(name = "SalidaPorConsumo.findByNombre", query = "SELECT s FROM SalidaPorConsumo s WHERE s.estado = :estado"),
    @NamedQuery(name = "SalidaPorConsumo.findByCantidad", query = "SELECT s FROM SalidaPorConsumo s WHERE s.cantidad = :cantidad"),
    @NamedQuery(name = "SalidaPorConsumo.findByTama\u00f1o", query = "SELECT s FROM SalidaPorConsumo s WHERE s.fechaSalidaConsumo = :fechaSalidaConsumo")})
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
    @Column(name = "Estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad")
    private Integer cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaSalidaConsumo")
    @Temporal (TemporalType.TIMESTAMP)
    private Date fechaSalidaConsumo;
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

    public SalidaPorConsumo(Integer idSalidaporconsumo, String estado, Integer cantidad, Date fechaSalidaConsumo) {
        this.idSalidaporconsumo = idSalidaporconsumo;
        this.estado = estado;
        this.cantidad = cantidad;
        this.fechaSalidaConsumo = fechaSalidaConsumo;
    }

    public Integer getIdSalidaporconsumo() {
        return idSalidaporconsumo;
    }

    public void setIdSalidaporconsumo(Integer idSalidaporconsumo) {
        this.idSalidaporconsumo = idSalidaporconsumo;
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


    public Date getFechaSalidaConsumo() {
        return fechaSalidaConsumo;
    }

    public void setFechaSalidaConsumo(Date fechaSalidaConsumo) {
        this.fechaSalidaConsumo = fechaSalidaConsumo;
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
