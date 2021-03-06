package com.lookextreme.controller;

import com.lookextreme.Dao.ClienteFacadeLocal;
import com.lookextreme.Dao.UsuarioFacadeLocal;
import com.lookextreme.model.Cliente;
import com.lookextreme.model.Roles;
import com.lookextreme.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@Named
@ViewScoped
public class ClienteController implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    private Usuario usuario;
    private Usuario usuario2;
    private Roles roles;

    @EJB
    private ClienteFacadeLocal clienteEJB;
    private Cliente cliente;
    private List<Cliente> listaDatosCliente;

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }
    
    

    public List<Cliente> getListaDatosCliente() {
        return listaDatosCliente;
    }

    public void setListaDatosCliente(List<Cliente> listaDatosCliente) {
        this.listaDatosCliente = listaDatosCliente;
    }

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
    public void init() {
        usuario2 = new Usuario();
        usuario = new Usuario();
        cliente = new Cliente();
        roles = new Roles();
        getDatosCliente();
    }

    public String registrarUsuario() {
        System.out.println("cliente-registrado");
        String redireccion = null;

        try {
            Roles roles1 = new Roles();
            roles1.setIdRoles(3);
            roles1.setTiporoles("Cliente");
            usuario2.setRolesidRoles(roles1);
            usuarioEJB.create(usuario2);
            registrarCliente(usuario2);
            redireccion = "login";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return redireccion;
    }

    public void registrarCliente(Usuario usuario) {
        // String redireccion = null;
        try {

            cliente.setUsuarioidUsuario(usuario.getIdUsuario());
            cliente.setUsuario(usuario);
            clienteEJB.create(cliente);
            //redireccion = "indexCliente";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //return redireccion;
    }

    public void getDatosCliente() {
        
        try {
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            cliente = clienteEJB.getDatosCliente(usuario.getIdUsuario());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cargarDatosCliente(Cliente clientee) {
        try {
            this.cliente = clientee;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actualizarDatosUsuario() {
        System.out.println("actualizando datos");
        try {
            usuarioEJB.edit(usuario);
            actualizarDatosCliente(usuario);
            PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('wdialog2').show();");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actualizarDatosCliente(Usuario usuario) {
        try{
            cliente.setUsuarioidUsuario(usuario.getIdUsuario());
            cliente.setUsuario(usuario);
            clienteEJB.edit(cliente);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public String redireccionarDatos(){
        String datos= null;
        datos = "cliente-datos";
        return datos;
    }
}
