/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.Horario;
import java.sql.ResultSet;
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
public class HorarioFacade extends AbstractFacade<Horario> implements HorarioFacadeLocal {

    @PersistenceContext(unitName = "com.lookExtreme_Projectlookextreme_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HorarioFacade() {
        super(Horario.class);
    }
    
    @Override
    public Horario validarRegistroHorario(int horario){
        List<Horario> listHorario = new ArrayList<>();
        Horario hour = new Horario();
        ResultSet result = null;
        try{
            Query q = em.createNativeQuery("select c.estilista_usuario_idUsuario from horario c where estilista_usuario_idUsuario = ?");
            q.setParameter(1, horario);
            //result =  q.executeUpdate();

            
        }catch(Exception e){
            System.out.println("error consulta"+e.getMessage());
        }
        return hour;
    }
}
