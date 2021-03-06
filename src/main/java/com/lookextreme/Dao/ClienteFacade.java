/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.Cliente;
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
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {

    @PersistenceContext(unitName = "com.lookExtreme_Projectlookextreme_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    public Cliente getDatosCliente(Integer idCliente) {
       
       Cliente datosCliente = new Cliente();
       try{
           datosCliente = (Cliente)em.createNamedQuery("Cliente.findByUsuarioidUsuario")
               .setParameter("usuarioidUsuario", idCliente)
               .getSingleResult();
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
       return datosCliente;
    } 
}
