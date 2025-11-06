package org.app.demo.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.app.demo.common.JsfUtils;
import org.app.demo.model.Doctor;
import org.app.demo.model.RespuestaWs;
import org.app.demo.model.ServletSession;
import org.app.demo.services.interfaces.DoctorService;

import java.io.Serializable;
import java.util.Map;

@Named
@ViewScoped
public class RegistroDoctorMB implements Serializable {
    @Inject
    private ServletSession ss;
    @Inject
    private DoctorService doctorService;
    private Map<String, Object> param;
    private Doctor doctor;
    private String origen;
    private Boolean visualizar;

    @PostConstruct
    public void init(){
        visualizar= Boolean.FALSE;
        doctor=new Doctor();
        if(ss.getParametros()!= null){
            param= ss.getParametros();
            if(param.containsKey("doctor")){
                doctor=(Doctor)param.get("doctor");
            }
            if(param.containsKey("origen")){
                origen=(String)param.get("origen");
            }
            if(param.containsKey("visualizar")){
                visualizar=(Boolean)param.get("visualizar");
            }
        }
    }
    public void guardarDoctor() {
        RespuestaWs respuesta = doctorService.guardar(doctor);
        if (respuesta != null && respuesta.getEstado() != null && respuesta.getEstado()) {
            JsfUtils.addMessage(FacesMessage.SEVERITY_INFO, "Exito", respuesta.getMensaje());
        } else {
            JsfUtils.addMessage(FacesMessage.SEVERITY_WARN, "Error", "Error al registrar persona");
        }
    }

    public Boolean getVisualizar() {
        return visualizar;
    }

    public void setVisualizar(Boolean visualizar) {
        this.visualizar = visualizar;
    }
    public void regresar(){
        JsfUtils.redirect(origen);
    }
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
