/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.ServiciosCitas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexis
 */
@Local
public interface ServiciosCitasFacadeLocal {

    void create(ServiciosCitas serviciosCitas);

    void edit(ServiciosCitas serviciosCitas);

    void remove(ServiciosCitas serviciosCitas);

    ServiciosCitas find(Object id);

    List<ServiciosCitas> findAll();

    List<ServiciosCitas> findRange(int[] range);
    
    List<ServiciosCitas> obtenerCitaPorEstilistas(int idEstilista);

    int count();
    
}
