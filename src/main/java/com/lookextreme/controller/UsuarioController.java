/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.controller;

import com.lookextreme.Dao.UsuarioFacadeLocal;
import com.lookextreme.model.Smtp;
import com.lookextreme.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.mail.MessagingException;
import org.primefaces.PrimeFaces;


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
    
    public void listar(){
        System.out.println("usuarios_Listados");
        try{
         listarUsuarios = this.usuarioEJB.findAll();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    public String irRegistrarse(){
        String registrarse = null;
        registrarse = "registrarse";
        return registrarse;
    }
    public String irLogin(){
        String login = null;
        login = "login";
        return login;
    }
    
    public void recuperarContraseña(){
        System.out.println("recuperando password");
        Usuario user = new Usuario();
                
        try{
           user = usuarioEJB.recuperarContraseña(usuario);
            if (user!= null) {
                 Smtp smtp = new Smtp();
                 smtp.setTo(user.getEmail());
                 smtp.setSubject("Recuperar contraeña");
                 smtp.setFrom("lookextreme7@gmail.com");
                 smtp.setDescr("Su contraseña es: "+user.getContraseña());
                 smtp.enviarCorreo();
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Se envió un correo con su contraseña"));
            }else{
                System.out.println("el correo no es valido");
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('wdialog2').show();");
            }
        }catch(MessagingException  | NullPointerException e){
            System.out.println("error al recuperar contraseña: "+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "El correo no es valido o no esta registrado en el sistema"));
        }
    }
}
