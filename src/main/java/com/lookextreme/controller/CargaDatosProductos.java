/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.controller;

import com.lookextreme.Dao.CategoriasFacadeLocal;
import com.lookextreme.Dao.MarcaFacadeLocal;
import com.lookextreme.Dao.NombreproductoFacadeLocal;
import com.lookextreme.Dao.ProductosFacadeLocal;
import com.lookextreme.model.Administrador;
import com.lookextreme.model.Categorias;
import com.lookextreme.model.Marca;
import com.lookextreme.model.Nombreproducto;
import com.lookextreme.model.Productos;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Alexis
 */
@Named
@ViewScoped
public class CargaDatosProductos implements Serializable {

    @EJB
    private ProductosFacadeLocal productosEJB;
    private Productos productos;
    private UploadedFile file;

    @EJB
    private MarcaFacadeLocal marcaEJB;

    @EJB
    private CategoriasFacadeLocal categoriaEJB;

    @EJB
    private NombreproductoFacadeLocal nombreProductoEJB;
    private Nombreproducto nombreProducto;


    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    @PostConstruct
    public void init() {
        productos = new Productos();
        nombreProducto = new Nombreproducto();
    }

    public void cargarProductos() throws ParseException {
        System.out.println("cargando Productos");

        Integer codigoMarca, codigoCategoria, codigoProducto;
        Categorias categoriass = new Categorias();
        Marca marca1 = new Marca();
        Productos product = new Productos();
        Administrador admin = new Administrador();
        Nombreproducto nombrep = new Nombreproducto();

        try {
            if (file.getSize() > 0) {
                InputStream input = file.getInputStream();
                XSSFWorkbook libro = new XSSFWorkbook(input);
                Sheet sheet = libro.getSheetAt(0);
                Iterator<Row> iterator = sheet.iterator();
                int i = 0;
                while (iterator.hasNext()) {

                    Row currentRow = iterator.next();
                    if (i > 0) {
                        if (currentRow.getCell(0) == null && currentRow.getCell(1) != null
                                && currentRow.getCell(2) != null && currentRow.getCell(3) != null && currentRow.getCell(4) != null
                                && currentRow.getCell(5) != null && currentRow.getCell(6) != null && currentRow.getCell(7) != null
                                && currentRow.getCell(8) != null) {

                            codigoProducto = (int) currentRow.getCell(1).getNumericCellValue();
                            codigoCategoria = (int) currentRow.getCell(2).getNumericCellValue();
                            codigoMarca = (int) currentRow.getCell(3).getNumericCellValue();
                            product.setTamaño((short) currentRow.getCell(4).getNumericCellValue());
                            Date datee = currentRow.getCell(5).getDateCellValue();
                            product.setFechaVencimiento(datee);
                            product.setCantidad((int) currentRow.getCell(6).getNumericCellValue());
                            product.setColor(currentRow.getCell(7).getStringCellValue());
                            product.setPrecio((int) currentRow.getCell(8).getNumericCellValue());
                            product.setEstado("registrado");

                            nombrep = nombreProductoEJB.find(codigoProducto);
                            categoriass = categoriaEJB.find(codigoCategoria);
                            marca1 = marcaEJB.find(codigoMarca);

                            admin.setUsuarioidUsuario(1);
                            product.setAdministradorusuarioidUsuario(admin);

                            if (categoriass.getIdcategorias() != null && marca1.getIdmarca() != null && nombrep.getIdNombreProducto() != null) {
                                product.setCategoriasIdcategorias(categoriass);
                                product.setMarcaIdmarca(marca1);
                                product.setNombreProductoidNombreProducto(nombrep);
                                productosEJB.create(product);
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se cargaron los datos exitosamente"));
                            } else {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Revise los codigos Marca, Categoria,Nombre Producto!"));
                                System.out.println("es vacio");
                            }

                        } else {
                            break;
                        }
                    }
                    i++;
                }
                libro.close();

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Por favor seleccione un archivo!"));
            }
        } catch (IOException | NullPointerException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "Algunos datos No pudieron ser subidos. Revise los codigos Marca, Categoria,Nombre Producto!"));
            System.out.println(e.getMessage());
        }

    }

}
