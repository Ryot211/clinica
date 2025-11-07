package org.app.demo.services.ejb;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.app.demo.config.VariablesInicio;
import org.app.demo.model.Paciente;
import org.app.demo.model.RespuestaWs;
import org.app.demo.services.interfaces.AppService;
import org.app.demo.services.interfaces.PacienteService;

import java.util.List;
@Stateless(name= "pacienteEjb")
public class PacienteEjb implements PacienteService {
    @Inject
    private AppService appService;

    @Override
    public RespuestaWs guardar(Paciente paciente){
        return (RespuestaWs) appService.methodPOST(paciente, VariablesInicio.wsDemo.concat("pacientes/crear"), RespuestaWs.class);

    }
    @Override
    public List<Paciente>consultarXEstado(String estado){
        return appService.methodListGET(VariablesInicio.wsDemo.concat("pacientes/consultar/").concat(estado), Paciente[].class);
    }
    @Override
    public Paciente buscarPorId(Long id) {
        return (Paciente) appService.methodGET(
                VariablesInicio.wsDemo.concat("pacientes/buscar/").concat(String.valueOf(id)),
                Paciente.class
        );
    }

}
