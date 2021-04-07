/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.Roles;
import com.lookextreme.model.Usuario;
import java.util.ArrayList;
import java.util.Iterator;
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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "com.lookExtreme_Projectlookextreme_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    @Override
    public Usuario iniciarSesion(Usuario us){
        Usuario usuario = null;
        try{              
            //consulta = "FROM Usuario u WHERE u.nombre = ?1 and u.contrase\u00f1a = ?2";
            Query query = em.createNamedQuery("Usuario.findByEmailContrasena");
            query.setParameter("email", us.getEmail());
            query.setParameter("contrase\u00f1a", us.getContraseña());
            List<Usuario> lista = query.getResultList();//obtener la lista de la consulta
            if (!lista.isEmpty())
                usuario = lista.get(0);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return usuario;
    }
    
    @Override
    public List<Usuario> consultarRoll(Roles rol){
        List<Usuario> lista = new ArrayList();
        try{
            lista = em.createNamedQuery("Usuario.findByRollEstilista")
                .setParameter("idRoles" , rol.getIdRoles())
                .getResultList();                        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    @Override
    public Usuario recuperarContraseña(Usuario usuarioEmail){
       Usuario usuario = new Usuario();
        List<Usuario> lista = new ArrayList<>();
        List<Object> result = new ArrayList();
        try{
            result = em.createNamedQuery("Usuario.findByRecuperarContraseña")
                    .setParameter("email", usuarioEmail.getEmail())
                    .getResultList();
            Iterator itr = result.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                 
                 usuario.setContraseña(String.valueOf(obj[1]));
                 usuario.setEmail(String.valueOf(obj[0]));
               
            }
            
        }catch(Exception e){
            System.out.println("error al recuperrar contraseña Dao: "+e.getMessage());
        }
        return usuario;
    }
}
