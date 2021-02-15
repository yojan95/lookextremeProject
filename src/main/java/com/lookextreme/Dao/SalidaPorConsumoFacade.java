/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.SalidaPorConsumo;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Alexis
 */
@Stateless
public class SalidaPorConsumoFacade extends AbstractFacade<SalidaPorConsumo> implements SalidaPorConsumoFacadeLocal {

    @PersistenceContext(unitName = "com.lookExtreme_Projectlookextreme_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SalidaPorConsumoFacade() {
        super(SalidaPorConsumo.class);
    }

    @Override
    public List<SalidaPorConsumo> buscarSalidaFecha(Date date) {
        List<SalidaPorConsumo> lista = null;
        try{
            String jpql = "FROM SalidaPorConsumo p WHERE p.fechaSalidaConsumo between ?1 and ?2";
            Query query = em.createQuery(jpql);
            query.setParameter(1, date, TemporalType.DATE);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);     
            cal.add(Calendar.DATE, 1);//establecer una dia al calendario
            query.setParameter(2, cal, TemporalType.DATE);
            lista = query.getResultList();
        }catch(Exception e){
            System.out.println("error en la consulta"+e);
        }
        return lista;
    }
    
}
