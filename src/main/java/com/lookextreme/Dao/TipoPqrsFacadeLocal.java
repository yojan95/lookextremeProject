/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.TipoPqrs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexis
 */
@Local
public interface TipoPqrsFacadeLocal {

    void create(TipoPqrs tipoPqrs);

    void edit(TipoPqrs tipoPqrs);

    void remove(TipoPqrs tipoPqrs);

    TipoPqrs find(Object id);

    List<TipoPqrs> findAll();

    List<TipoPqrs> findRange(int[] range);

    int count();
    
}
