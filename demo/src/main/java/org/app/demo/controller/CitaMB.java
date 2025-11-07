package org.app.demo.controller;


import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.app.demo.common.DatosReporte;
import org.app.demo.common.JsfUtils;
import org.app.demo.common.ReporteFormato;
import org.app.demo.common.StatusType;
import org.app.demo.model.Cita;
import org.app.demo.model.ServletSession;
import org.app.demo.services.interfaces.CitaService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class CitaMB  implements Serializable {
    @Inject
    private CitaService citaService;
    @Inject
    private ServletSession ss;
    private Cita cita;
    private List<Cita> citas;

    @PostConstruct
    public void init(){loadCitas(StatusType.ACTIVO.name());}
        public void loadCitas(String tipo){
        citas = new ArrayList<Cita>();
        List<Cita>citaTmp = citaService.consultarXEstado(tipo);
        if(citaTmp!= null && citaTmp.size()>0){
            citas.addAll( citaTmp);
        }
    }
    public void imprimir(String estado){
        DatosReporte datosReporte = new DatosReporte();
        ss.borrarDatos();
        ss.instanciarParametros();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("NOMBRE_REPORTE", "CURSO DE JAVA AVANZADO");
        parametros.put("estado", estado);
        datosReporte.setFormato((ReporteFormato.PDF.getCodigo()));
        datosReporte.setNombreArchivo("Citas"+ReporteFormato.PDF.getCodigo());
        datosReporte.setParametros(parametros);
        datosReporte.setDataSource(Boolean.FALSE);
        datosReporte.setGestorDocumental(Boolean.FALSE);
        datosReporte.setNombreReporte("citas");
        ss.setDatosReporte(datosReporte);
        ss.setNombreDocumento("Citas");
        redireccionarReporte();
    }
    public void redireccionarReporte(){
        JsfUtils.redirectNewTab("/ReporteWS");
    }
    public void nuevo(Cita cita, String origen, Boolean visualizar){
        ss.borrarDatos();
        ss.agregarParametro("origen", origen);
        ss.agregarParametro("visualizar", visualizar);
        if(cita!=null){
            ss.agregarParametro("cita", cita);
        }
        JsfUtils.redirectNewTab("/procesos/_registrarCita.xhtml");
    }
    public Cita getCita(){return cita;}
    public void setCita(Cita cita){this.cita = cita;}
    public List<Cita> getCitas(){return citas;}
    public void setCitas(List<Cita> citas){this.citas = citas;}
}
