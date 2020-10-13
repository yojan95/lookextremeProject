/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.Cita;
import com.lookextreme.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alexis
 */
@Stateless
public class CitaFacade extends AbstractFacade<Cita> implements CitaFacadeLocal {

    @PersistenceContext(unitName = "com.lookExtreme_Projectlookextreme_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitaFacade() {
        super(Cita.class);
    }
     @Override
    public List<Cita> obtenerCitaPorCliente(int idCliente){
        List<Cita> listaCita = new ArrayList();
        try{
            listaCita = em.createNamedQuery("Cliente.findbyCita")
                    .setParameter("idUsuario", idCliente)
                    .getResultList();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return listaCita;
    }
/*
    @Override
    public List<Cita> buscarEstadoCliente(int idCliente, String estado) throws Exception{
        List<Cita> ListaBusqueda;
        try{
            String jpql = "FROM Cita c WHERE c.clienteusuarioidUsuario.usuario.idUsuario = ?1 and c.estado = ?2";
            Query query = em.createNamedQuery(jpql);
            query.setParameter(1, idCliente);
            query.setParameter(2, estado);
            
            ListaBusqueda = query.getResultList();
        }catch(Exception e){
            throw e;
        }
        return ListaBusqueda;
    }
    */
}
