package org.app.demo.controller;


import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.app.demo.common.JsfUtils;
import org.app.demo.model.Paciente;
import org.app.demo.model.RespuestaWs;
import org.app.demo.model.ServletSession;
import org.app.demo.services.interfaces.PacienteService;

import java.io.Serializable;
import java.util.Map;

@Named
@ViewScoped
public class RegistroPacienteMB implements Serializable {
    @Inject
    private ServletSession ss;
    @Inject
    PacienteService pacienteService;
    private Map<String, Object> param;
    private Paciente paciente;
    private String origen;
    private Boolean visualizar;

    @PostConstruct
    public void init() {
        visualizar = Boolean.FALSE;
        paciente = new Paciente();
        if(ss.getParametros() != null){
            param = ss.getParametros();
            if(param.containsKey("paciente")){
                paciente = (Paciente) param.get("paciente");
            }
            if(param.containsKey("origen")){
                origen = (String) param.get("origen");
            }
            if(param.containsKey("visualizar")){
                visualizar = (Boolean) param.get("visualizar");
            }
        }
    }



    public void guardarPaciente() {
        try {
            RespuestaWs respuesta = pacienteService.guardar(paciente);

            if (respuesta != null && Boolean.TRUE.equals(respuesta.getEstado())) {
                JsfUtils.addMessage(FacesMessage.SEVERITY_INFO, "Éxito", respuesta.getMensaje());
            } else {
                String detalle = (respuesta != null && respuesta.getMensaje() != null)
                        ? respuesta.getMensaje()
                        : "Error desconocido al registrar paciente.";
                JsfUtils.addMessage(FacesMessage.SEVERITY_ERROR, "Error", detalle);
            }

        } catch (Exception e) {
            // Muestra mensaje en pantalla
            JsfUtils.addMessage(FacesMessage.SEVERITY_FATAL, "Excepción", e.getMessage());
            // Imprime el error completo en consola
            e.printStackTrace();
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

    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

}
