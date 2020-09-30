/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.SalidaVenta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexis
 */
@Local
public interface SalidaVentaFacadeLocal {

    void create(SalidaVenta salidaVenta);

    void edit(SalidaVenta salidaVenta);

    void remove(SalidaVenta salidaVenta);

    SalidaVenta find(Object id);

    List<SalidaVenta> findAll();

    List<SalidaVenta> findRange(int[] range);

    int count();
    
}
