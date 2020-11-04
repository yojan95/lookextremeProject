/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.Cita;
import com.lookextreme.model.HorarioDisponibilidad;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public List<HorarioDisponibilidad> verificarDisponibilidad(int idUsuario, Date fechacita) {        
        List<Object>  result = new ArrayList();
        List<HorarioDisponibilidad> horarios = new ArrayList();
        try {
            StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("cita.verificarDisponibilidad");
            query.setParameter("id_usuario", idUsuario);
            query.setParameter("fechaCita", fechacita);   
            result = query.getResultList();
            Iterator itr = result.iterator();            
            while(itr.hasNext()){                
                Object[] obj = (Object[]) itr.next();
                HorarioDisponibilidad  horario = new HorarioDisponibilidad();
                horario.setEstado(String.valueOf(obj[1]));
                horario.setHora(Integer.valueOf(String.valueOf(obj[0])));
                horarios.add(horario);
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return horarios;
    }
    
    @Override
    public List<Cita> obtenerEstadosAgendamiento(){
        List<Object>  result = new ArrayList();
        List<Cita> citas = new ArrayList();
        try {
            result = em.createNamedQuery("Cita.findAllGroupedByState")
                    .getResultList();
            Iterator itr = result.iterator();            
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                Cita cita = new Cita();                
                cita.setEstado(String.valueOf(obj[1]));
                cita.setIdCita(Integer.valueOf(String.valueOf(obj[0])));
                citas.add(cita);
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return citas;
    }
}