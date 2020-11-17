/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lookextreme.controller;

import com.lookextreme.Dao.CitaFacadeLocal;
import com.lookextreme.model.Cita;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;

@Named
@ViewScoped
public class AgendamientoReporteController implements Serializable {

    @EJB
    private CitaFacadeLocal EJBcita;
    private PieChartModel pieModel;

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    @PostConstruct
    public void init() {        
        initChart();
    }

    public void initChart() {
        try {
            pieModel = new PieChartModel();
            List<Cita> citas = EJBcita.obtenerEstadosAgendamiento();
            System.out.println("citas : " + citas.size());

            for (Cita cita : citas) {
                pieModel.set(cita.getEstado(), cita.getIdCita());
                System.out.println("estado: " +cita.getEstado());
                System.out.println("cantidad: " +cita.getIdCita());
            }

            pieModel.setTitle("Agendamientos");
            pieModel.setLegendPosition("e");
            pieModel.setFill(false);
            pieModel.setShowDataLabels(true);
            pieModel.setDiameter(150);
        } catch (Exception e) {
            System.out.println("Error chart: " + e.getMessage());
        }
    }
}
