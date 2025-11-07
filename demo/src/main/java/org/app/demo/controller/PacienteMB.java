package org.app.demo.controller;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.app.demo.common.*;
import org.app.demo.model.Paciente;
import org.app.demo.model.ServletSession;
import org.app.demo.services.interfaces.PacienteService;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Named
@ViewScoped
public class PacienteMB implements Serializable {
   @Inject
   private PacienteService pacienteService;
   @Inject
   private ServletSession ss;
   private Paciente paciente;
   private List<Paciente> pacientes;

   @PostConstruct
   public void init() {loadPacientes(StatusType.ACTIVO.name());}
    public void loadPacientes(String tipo){
       pacientes = new ArrayList<>();
       List<Paciente>pacienteTmp = pacienteService.consultarXEstado(tipo);
       if(pacienteTmp!= null && pacienteTmp.size()>0){
           pacientes.addAll(pacienteTmp);
       }
    }
    public void imprimir(String estado){
       DatosReporte datosReporte = new DatosReporte();
        ss.borrarDatos();
        ss.instanciarParametros();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("NOMBRE_REPORTE","Pacientes");
        parametros.put("estado",estado);
        datosReporte.setFormato(ReporteFormato.PDF.getCodigo());
        datosReporte.setNombreArchivo("Pacientes"+ReporteFormato.PDF.getExtension());
        datosReporte.setParametros(parametros);
        datosReporte.setDataSource(Boolean.FALSE);
        datosReporte.setGestorDocumental(Boolean.FALSE);
        datosReporte.setNombreReporte("clinicaa_pacientes");
        ss.setDatosReporte(datosReporte);
        ss.setNombreDocumento("Pacientes");
        redireccionarReporte();
    }
    public void redireccionarReporte(){
       JsfUtils.redirectNewTab("/ReporteWS");
    }

    public void nuevo (Paciente paciente,String origen, Boolean visualizar){
       ss.borrarDatos();
       ss.agregarParametro("origen",origen);
       ss.agregarParametro("visualizar",visualizar);
       if(paciente!=null){
           ss.agregarParametro("paciente",paciente);

       }
       JsfUtils.redirect("/procesos/_registrarPaciente.xhtml");
    }

    public Paciente getPaciente() {return paciente;}
    public void setPaciente (Paciente paciente) {this.paciente = paciente;}
    public List<Paciente> getPacientes() {return pacientes;}
    public void setPacientes(List<Paciente> pacientes) {this.pacientes = pacientes;}
}
