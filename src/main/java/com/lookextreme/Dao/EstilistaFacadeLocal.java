/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.Estilista;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexis
 */
@Local
public interface EstilistaFacadeLocal {

    void create(Estilista estilista);

    void edit(Estilista estilista);

    void remove(Estilista estilista);

    Estilista find(Object id);

    List<Estilista> findAll();

    List<Estilista> findRange(int[] range);

    int count();
    
}
