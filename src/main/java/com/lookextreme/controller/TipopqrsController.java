package com.lookextreme.controller;

import com.lookextreme.Dao.TipoPqrsFacadeLocal;
import com.lookextreme.model.TipoPqrs;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.model.SelectItem;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class TipopqrsController  implements Serializable{
    @EJB
    private TipoPqrsFacadeLocal EJBTipopqrs;    
    private List<SelectItem> pqrsListItems;

    public List<SelectItem> getPqrsListItems() {
        return pqrsListItems;
    }
    
    public TipoPqrsFacadeLocal getEJBTipopqrs() {
        return EJBTipopqrs;
    }
    
    @PostConstruct
    public void init(){        
        getAllTipospqrs();
    }
    
    public List<SelectItem> getAllTipospqrs(){
        pqrsListItems = new ArrayList();
        List<TipoPqrs> tipoPqrsList = new ArrayList();
        
        try {
            tipoPqrsList =  EJBTipopqrs.findAll();
            for(TipoPqrs tipoPqrs : tipoPqrsList)
            {
                pqrsListItems.add(new SelectItem(tipoPqrs.getIdTipoPQRS(), tipoPqrs.getTipoPQRS()));
            }
        } catch (Exception e) {
            pqrsListItems.add(new SelectItem(-1, "No existen Tipos de PQRS"));
        }
        
        return pqrsListItems;
    }
}
