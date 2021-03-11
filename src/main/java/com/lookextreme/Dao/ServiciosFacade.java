/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.Servicios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alexis
 */
@Stateless
public class ServiciosFacade extends AbstractFacade<Servicios> implements ServiciosFacadeLocal {

    @PersistenceContext(unitName = "com.lookExtreme_Projectlookextreme_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiciosFacade() {
        super(Servicios.class);
    }
    
    @Override
    public void updateService(Servicios servicios){
        try{
            Query q = em.createNativeQuery("UPDATE servicios SET Descripcion = ?, Precio = ?, Nombre = ? WHERE idServicios = ?");
            q.setParameter(1, servicios.getDescripcion());
            q.setParameter(1, servicios.getPrecio());
            q.setParameter(1, servicios.getNombre());
            q.setParameter(4, servicios.getIdServicios());
            q.executeUpdate();
        }catch(Exception e){
            System.out.println("Error en la consulta"+e.getMessage());
        }
    }
    
}
