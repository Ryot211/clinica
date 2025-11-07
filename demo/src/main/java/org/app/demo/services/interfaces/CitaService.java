package org.app.demo.services.interfaces;

import jakarta.ejb.Local;
import org.app.demo.model.Cita;
import org.app.demo.model.RespuestaWs;

import java.util.List;

@Local
public interface CitaService {
    public RespuestaWs guardar(Cita cita);

    public List<Cita> consultarXEstado(String estado);
    public List<Cita> obtenerTodas();
}
