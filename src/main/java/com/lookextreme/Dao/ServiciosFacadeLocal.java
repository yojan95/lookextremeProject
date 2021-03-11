/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.Servicios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexis
 */
@Local
public interface ServiciosFacadeLocal {

    void create(Servicios servicios);

    void edit(Servicios servicios);

    void remove(Servicios servicios);

    Servicios find(Object id);
    
    void updateService(Servicios servicios);

    List<Servicios> findAll();

    List<Servicios> findRange(int[] range);

    int count();
    
}
