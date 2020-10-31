/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.Cita;
import com.lookextreme.model.Cliente;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

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

    @Override
    public List<Cita> obtenerCitaPorEstilistaEstadoIncumpliento(int idEstilista) {
         List<Cita> listaCita = new ArrayList();
        try{
            listaCita = em.createNamedQuery("Estilista.findbyCita")
                    .setParameter("idUsuario", idEstilista)
                    .getResultList();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return listaCita;
    }

    @Override
    public List<Cita> verificarDisponibilidad(int idUsuario, Date fechacita) {
        List<Cita> listaCitaVerificar = new ArrayList();
        Cita citaProcedure = new Cita();
        List<Cita>  procedure; 
        
        try {
            StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("cita.findByVerificarDisponibilidad");
            query.setParameter("id_usuario", idUsuario);
            query.setParameter("fechaCita", fechacita);   
             List result = query.getResultList();
             for(Object record: result) {
                 citaProcedure = new Cita();
                 //citaProcedure.setEstado(record.);
                 //citaProcedure.setHora(record[0]);
             }
             
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listaCitaVerificar;
    }
}
