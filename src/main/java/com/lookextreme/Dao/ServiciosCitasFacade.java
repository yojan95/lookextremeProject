/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.ServiciosCitas;
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
public class ServiciosCitasFacade extends AbstractFacade<ServiciosCitas> implements ServiciosCitasFacadeLocal {

    @PersistenceContext(unitName = "com.lookExtreme_Projectlookextreme_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiciosCitasFacade() {
        super(ServiciosCitas.class);
    }

    @Override
    public List<ServiciosCitas> obtenerCitaPorEstilistas(int idEstilista) {
        List<ServiciosCitas> listaCita = new ArrayList();
        try{
            listaCita = em.createNamedQuery("ServiciosCitas.findEstilista")
                    .setParameter("idUsuario", idEstilista)
                    .getResultList();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return listaCita;
    }
    
}
