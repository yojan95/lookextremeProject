
package com.lookextreme.controller;

import com.lookextreme.Dao.UsuarioFacadeLocal;
import com.lookextreme.model.Cliente;
import com.lookextreme.model.Roles;
import com.lookextreme.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ClienteController implements Serializable{
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    private Usuario usuario;
    private Cliente cliente;
    private Roles roles;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
        cliente = new Cliente();
        roles = new Roles();
    }
    public String registrarCliente(){
        System.out.println("cliente-registrado");
        String redireccion = null;
        
        try{
            roles.setIdRoles(3);
            //roles.setTiporoles("Client");
            usuario.setRolesidRoles(roles);
            usuarioEJB.create(usuario);
            redireccion = "indexCliente";
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return redireccion;
    }
    
    
}
