/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.controller;

import com.lookextreme.Dao.UsuarioFacadeLocal;
import com.lookextreme.model.Roles;
import com.lookextreme.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class UsuarioController implements Serializable{
    
    @EJB
    UsuarioFacadeLocal usuarioEJB;
    private Usuario usuario;
    private List<Usuario> listarUsuarios;

    public List<Usuario> getListarUsuarios() {
        return listarUsuarios;
    }

    public void setListarUsuarios(List<Usuario> listarUsuarios) {
        this.listarUsuarios = listarUsuarios;
    }
    
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

         
    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }
    /*
    public String registrar(){
        System.out.println("UsuarioRegistrado\n ..logueado");
        String redireccion = null;
        
        try{
             Roles roles = new Roles();
            roles.setIdRoles(1);
            roles.setTiporoles("Client");
            usuario.setRolesidRoles(roles);
            usuarioEJB.create(usuario);
            redireccion = "indexCliente";
                       
        }catch(Exception e){
            System.out.println(""+e.getMessage());
        }
        return redireccion;
    }
*/
    public String irAlaCita(){
        String agendar= null;
        
        agendar = "cliente-disponibilidad";
        
        return agendar;
    }
    public void listar(){
        System.out.println("usuarios_Listados");
        try{
         listarUsuarios = this.usuarioEJB.findAll();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
