package org.app.demo.services.ejb;


import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.app.demo.config.VariablesInicio;
import org.app.demo.model.Cita;
import org.app.demo.model.RespuestaWs;
import org.app.demo.services.interfaces.AppService;
import org.app.demo.services.interfaces.CitaService;

import java.util.List;

@Stateless
public class CitaEjb  implements CitaService {
    @Inject
    private AppService appService;
    @Override
    public RespuestaWs guardar(Cita cita){
        return (RespuestaWs) appService.methodPOST(cita, VariablesInicio.wsDemo.concat("citas/crear"),RespuestaWs.class);
    }
    @Override
    public List<Cita>consultarXEstado(String estado){
        return appService.methodListGET(VariablesInicio.wsDemo.concat("citas/consultar/").concat(estado),Cita[].class);
    }
    @Override
    public List<Cita> obtenerTodas() {
        return appService.methodListGET(
                VariablesInicio.wsDemo.concat("citas/consultar/todas"),
                Cita[].class
        );
    }
}
