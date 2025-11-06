package org.app.demo.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.app.demo.common.DatosReporte;
import org.app.demo.common.JsfUtils;
import org.app.demo.common.ReporteFormato;
import org.app.demo.common.StatusType;
import org.app.demo.model.Doctor;
import org.app.demo.model.ServletSession;
import org.app.demo.services.interfaces.DoctorService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Named
@ViewScoped
public class DoctorMB implements Serializable {
    @Inject
    private DoctorService doctorService;
    @Inject
    private ServletSession ss;
    private Doctor doctor;
    private List<Doctor> doctores;

    @PostConstruct
    public void init(){loadDoctores(StatusType.ACTIVO.name());}
        public void loadDoctores(String tipo){
        doctores= new ArrayList<>();
        List<Doctor>doctorTmp = doctorService.consultarXEstado(tipo);
        if(doctorTmp!=null && doctorTmp.size()>0){
            doctores.addAll(doctorTmp);
        }
    }
    public void imprimir (String estado){
        DatosReporte datosReporte = new DatosReporte();
        ss.borrarDatos();
        ss.instanciarParametros();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("Doctores Activos", "Clinica Santander");
        parametros.put("estado", estado);
        datosReporte.setFormato((ReporteFormato.PDF.getCodigo()));
        datosReporte.setNombreArchivo("Doctores"+ReporteFormato.PDF.getExtension());
        datosReporte.setParametros(parametros);
        datosReporte.setDataSource(Boolean.FALSE);
        datosReporte.setGestorDocumental(Boolean.FALSE);
        datosReporte.setNombreReporte("Doctores Activos");
        ss.setDatosReporte(datosReporte);
        ss.setNombreDocumento("Doctores");
        redireccionarReporte();
    }

    public void redireccionarReporte(){
        JsfUtils.redirectNewTab("/ReportesWS");
    }
    public void nuevo(Doctor doctor, String origen, Boolean visualizar){
        ss.borrarDatos();
        ss.agregarParametro("origen", origen);
        ss.agregarParametro("visualizar",visualizar);
        if(doctor!= null){
            ss.agregarParametro("doctor",doctor);

        }
        JsfUtils.redirectNewTab("/procesos/_registrarDoctor.xhtml");
    }

    public Doctor getdoctor() {return doctor;}
    public void setDoctor(Doctor doctor) {this.doctor = doctor;}
    public List<Doctor> getDoctores() {return doctores;}
    public void setDoctores(List<Doctor> doctores) {this.doctores = doctores;}
}
