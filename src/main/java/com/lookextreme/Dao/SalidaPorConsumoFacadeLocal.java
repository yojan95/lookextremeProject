/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.SalidaPorConsumo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hoore
 */
@Local
public interface SalidaPorConsumoFacadeLocal {

    void create(SalidaPorConsumo salidaPorConsumo);

    void edit(SalidaPorConsumo salidaPorConsumo);

    void remove(SalidaPorConsumo salidaPorConsumo);

    SalidaPorConsumo find(Object id);

    List<SalidaPorConsumo> findAll();

    List<SalidaPorConsumo> findRange(int[] range);

    int count();
    
}
