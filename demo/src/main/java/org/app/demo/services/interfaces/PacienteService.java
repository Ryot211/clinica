package org.app.demo.services.interfaces;

import jakarta.ejb.Local;
import org.app.demo.model.Paciente;
import org.app.demo.model.RespuestaWs;

import java.util.List;

@Local
public interface PacienteService {
    public RespuestaWs guardar(Paciente paciente);
    public List<Paciente> consultarXEstado(String estado);
}
