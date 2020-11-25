
package com.lookextreme.controller;

import com.lookextreme.Dao.ClienteFacadeLocal;
import com.lookextreme.Dao.UsuarioFacadeLocal;
import com.lookextreme.model.Cliente;
import com.lookextreme.model.Roles;
import com.lookextreme.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    private Roles roles;
    
    @EJB
    private ClienteFacadeLocal clienteEJB;
    private Cliente cliente;

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
    public String registrarUsuario(){
        System.out.println("cliente-registrado");
        String redireccion = null;
        
        try{
            Roles roles = new Roles();
            roles.setIdRoles(3);
            roles.setTiporoles("Cliente");
            usuario.setRolesidRoles(roles);
            usuarioEJB.create(usuario);
            registrarCliente(usuario);
            redireccion = "indexCliente";
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return redireccion;
    }
    public void registrarCliente(Usuario usuario){
       // String redireccion = null;
        try{
            
            cliente.setUsuarioidUsuario(usuario.getIdUsuario());
            cliente.setUsuario(usuario);
            clienteEJB.create(cliente);
            //redireccion = "indexCliente";
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //return redireccion;
    }
    
    
}
