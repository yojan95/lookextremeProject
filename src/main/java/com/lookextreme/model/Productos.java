/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alexis
 */
@Entity
@Table(name = "productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByIdCodigo", query = "SELECT p FROM Productos p WHERE p.idCodigo = :idCodigo"),
    @NamedQuery(name = "Productos.findByTama\u00f1o", query = "SELECT p FROM Productos p WHERE p.tama\u00f1o = :tama\u00f1o"),
    @NamedQuery(name = "Productos.findByFechaVencimiento", query = "SELECT p FROM Productos p WHERE p.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "Productos.findByCantidad", query = "SELECT p FROM Productos p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Productos.findByColor", query = "SELECT p FROM Productos p WHERE p.color = :color"),
    @NamedQuery(name = "Productos.findByPrecio", query = "SELECT p FROM Productos p WHERE p.precio = :precio"),
    @NamedQuery(name = "Productos.findByEstado", query = "SELECT p FROM Productos p WHERE p.estado = :estado")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCodigo")
    private Integer idCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tama\u00f1o")
    private short tamaño;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaVencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad")
    private short cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Color")
    private String color;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Precio")
    private int precio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosidCodigo")
    private List<SalidaVenta> salidaVentaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosidCodigo")
    private List<SalidaPorConsumo> salidaPorConsumoList;
    @JoinColumn(name = "NombreProducto_idNombreProducto", referencedColumnName = "idNombreProducto")
    @ManyToOne(optional = false)
    private Nombreproducto nombreProductoidNombreProducto;
    @JoinColumn(name = "administrador_usuario_idUsuario", referencedColumnName = "usuario_idUsuario")
    @ManyToOne(optional = false)
    private Administrador administradorusuarioidUsuario;
    @JoinColumn(name = "categorias_idcategorias", referencedColumnName = "idcategorias")
    @ManyToOne(optional = false)
    private Categorias categoriasIdcategorias;
    @JoinColumn(name = "marca_idmarca", referencedColumnName = "idmarca")
    @ManyToOne(optional = false)
    private Marca marcaIdmarca;

    public Productos() {
    }

    public Productos(Integer idCodigo) {
        this.idCodigo = idCodigo;
    }

    public Productos(Integer idCodigo, short tamaño, Date fechaVencimiento, short cantidad, String color, int precio, String estado) {
        this.idCodigo = idCodigo;
        this.tamaño = tamaño;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.color = color;
        this.precio = precio;
        this.estado = estado;
    }

    public Integer getIdCodigo() {
        return idCodigo;
    }

    public void setIdCodigo(Integer idCodigo) {
        this.idCodigo = idCodigo;
    }

    public short getTamaño() {
        return tamaño;
    }

    public void setTamaño(short tamaño) {
        this.tamaño = tamaño;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<SalidaVenta> getSalidaVentaList() {
        return salidaVentaList;
    }

    public void setSalidaVentaList(List<SalidaVenta> salidaVentaList) {
        this.salidaVentaList = salidaVentaList;
    }

    @XmlTransient
    public List<SalidaPorConsumo> getSalidaPorConsumoList() {
        return salidaPorConsumoList;
    }

    public void setSalidaPorConsumoList(List<SalidaPorConsumo> salidaPorConsumoList) {
        this.salidaPorConsumoList = salidaPorConsumoList;
    }

    public Nombreproducto getNombreProductoidNombreProducto() {
        return nombreProductoidNombreProducto;
    }

    public void setNombreProductoidNombreProducto(Nombreproducto nombreProductoidNombreProducto) {
        this.nombreProductoidNombreProducto = nombreProductoidNombreProducto;
    }

    public Administrador getAdministradorusuarioidUsuario() {
        return administradorusuarioidUsuario;
    }

    public void setAdministradorusuarioidUsuario(Administrador administradorusuarioidUsuario) {
        this.administradorusuarioidUsuario = administradorusuarioidUsuario;
    }

    public Categorias getCategoriasIdcategorias() {
        return categoriasIdcategorias;
    }

    public void setCategoriasIdcategorias(Categorias categoriasIdcategorias) {
        this.categoriasIdcategorias = categoriasIdcategorias;
    }

    public Marca getMarcaIdmarca() {
        return marcaIdmarca;
    }

    public void setMarcaIdmarca(Marca marcaIdmarca) {
        this.marcaIdmarca = marcaIdmarca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCodigo != null ? idCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.idCodigo == null && other.idCodigo != null) || (this.idCodigo != null && !this.idCodigo.equals(other.idCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lookextreme.model.Productos[ idCodigo=" + idCodigo + " ]";
    }
    
}
