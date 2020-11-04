/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.Dao;

import com.lookextreme.model.Cita;
import com.lookextreme.model.Cliente;
import com.lookextreme.model.HorarioDisponibilidad;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexis
 */
@Local
public interface CitaFacadeLocal {

    void create(Cita cita);

    void edit(Cita cita);

    void remove(Cita cita);

    Cita find(Object id);

    List<Cita> findAll();

    List<Cita> findRange(int[] range);
    
    List<Cita> obtenerCitaPorCliente(int idCliente);
    
    List<Cita> obtenerCitaPorEstilistaEstadoIncumpliento(int idEstilista);
    
    List<HorarioDisponibilidad> verificarDisponibilidad(int idUsuario,Date fechacita);
    
    //List<Cita> buscarEstadoCliente(int idcliente,String estado)throws Exception;

    List<Cita> obtenerEstadosAgendamiento();
    
    int count();    
}
