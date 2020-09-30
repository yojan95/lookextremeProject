/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.Nombreproducto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexis
 */
@Local
public interface NombreproductoFacadeLocal {

    void create(Nombreproducto nombreproducto);

    void edit(Nombreproducto nombreproducto);

    void remove(Nombreproducto nombreproducto);

    Nombreproducto find(Object id);

    List<Nombreproducto> findAll();

    List<Nombreproducto> findRange(int[] range);

    int count();
    
}
