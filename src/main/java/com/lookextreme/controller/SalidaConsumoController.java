
package com.lookextreme.controller;
        

import com.lookextreme.Dao.EstilistaFacadeLocal;
import com.lookextreme.Dao.ProductosFacadeLocal;
import com.lookextreme.Dao.SalidaPorConsumoFacadeLocal;
import com.lookextreme.Dao.UsuarioFacadeLocal;
import com.lookextreme.model.Administrador;
import com.lookextreme.model.Estilista;
import com.lookextreme.model.Productos;
import com.lookextreme.model.Roles;
import com.lookextreme.model.SalidaPorConsumo;
import com.lookextreme.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class SalidaConsumoController implements Serializable {
    
    @EJB
    private SalidaPorConsumo consumoModel;
    private SalidaPorConsumoFacadeLocal consumoDao;
    private Administrador administrador;
    private Productos pro;
    private ProductosFacadeLocal prof;
    
    private Estilista estilista;
    private EstilistaFacadeLocal estilistaDao;
    
    @EJB
    private ProductosFacadeLocal productoEJB;
    private List<Productos> proList;
    
    @EJB
    private EstilistaFacadeLocal estilistaEJB;
    private List<SelectItem> estList;
    
    @EJB
    private Usuario usuario;
    private UsuarioFacadeLocal EJBusuario;
   

    public SalidaPorConsumo getConsumoModel() {
        return consumoModel;
    }

    public void setConsumoModel(SalidaPorConsumo consumoModel) {
        this.consumoModel = consumoModel;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Productos getPro() {
        return pro;
    }

    public void setPro(Productos pro) {
        this.pro = pro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Productos> getProList() {
        return proList;
    }

    public void setProList(List<Productos> proList) {
        this.proList = proList;
    }

    public List<SelectItem> getEstList() {
        return estList;
    }

    public void setEstList(List<SelectItem> estList) {
        this.estList = estList;
    }

    

    public Estilista getEstilista() {
        return estilista;
    }

    public void setEstilista(Estilista estilista) {
        this.estilista = estilista;
    }
     @PostConstruct
    
    public void init() {
        pro = new Productos();
        consumoModel = new SalidaPorConsumo();
        administrador = new Administrador();
        usuario = new Usuario();
        proList = productoEJB.findAll();
        estilista = new Estilista();
        listarEstilistas();
    }
            public void consumoRegistrado() {
        System.out.println("Producto Registrado");

        try {

            consumoModel.setEstilistausuarioidUsuario(estilista);  ;
            consumoModel.setProductosidCodigo(pro);
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
          
            administrador.setUsuarioidUsuario(usuario.getIdUsuario());            
            //estilista.setUsuarioidUsuario(usuario.getIdUsuario());
            consumoDao.create(consumoModel);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
     public void listarEstilistas() {
         
        estList = new ArrayList();
        List<Usuario> listaEstilistas = new ArrayList();
        try {
             Roles rol = new Roles();
             rol.setIdRoles(2);
             listaEstilistas = EJBusuario.consultarRoll(rol);
             for ( Usuario estilistaitem : listaEstilistas  ){
                 estList.add( new SelectItem(estilistaitem.getIdUsuario(),estilistaitem.getNombre() ) );
                                                               }
            }catch(Exception e){
                System.out.println("listar-estilistas");
            System.out.println(e.getMessage());
        }
     }
    
    

    
    
    
    
    
    
    
    
}
