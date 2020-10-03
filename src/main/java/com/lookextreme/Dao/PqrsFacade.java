/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.Pqrs;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexis
 */
@Stateless
public class PqrsFacade extends AbstractFacade<Pqrs> implements PqrsFacadeLocal {

    @PersistenceContext(unitName = "com.lookExtreme_Projectlookextreme_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PqrsFacade() {
        super(Pqrs.class);
    }
    
    @Override
    public List<Pqrs> obtenerPqrsPorCliente(int idCliente){
        List<Pqrs> listaPqrs = new ArrayList();
        try{
            listaPqrs = em.createNamedQuery("Pqrs.findByClient")
                .setParameter("idUsuario" , idCliente)
                .getResultList();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return listaPqrs;
    }
    
}
