/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.Categorias;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexis
 */
@Stateless
public class CategoriasFacade extends AbstractFacade<Categorias> implements CategoriasFacadeLocal {

    @PersistenceContext(unitName = "com.lookExtreme_Projectlookextreme_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriasFacade() {
        super(Categorias.class);
    }
    
}
