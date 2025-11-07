package org.app.demo.services.ejb;


import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.app.demo.config.VariablesInicio;
import org.app.demo.model.Doctor;
import org.app.demo.model.RespuestaWs;
import org.app.demo.services.interfaces.AppService;
import org.app.demo.services.interfaces.DoctorService;

import java.util.List;

@Stateless
public class DoctorEjb implements DoctorService {
    @Inject
    private AppService appService;
    @Override
    public RespuestaWs guardar(Doctor doctor) {
        return (RespuestaWs) appService.methodPOST(doctor, VariablesInicio.wsDemo.concat("doctores/crear"),RespuestaWs.class);
    }
    @Override
    public List<Doctor>consultarXEstado(String estado) {
        return appService.methodListGET(VariablesInicio.wsDemo.concat("doctores/consultar/").concat(estado),Doctor[].class);
    }
    @Override
    public Doctor buscarPorId(Long id) {
        return (Doctor) appService.methodGET(
                VariablesInicio.wsDemo.concat("doctores/buscar/").concat(String.valueOf(id)),
                Doctor.class
        );
    }
}
