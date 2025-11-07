package org.app.demo.services.interfaces;


import jakarta.ejb.Local;

import org.app.demo.model.Doctor;
import org.app.demo.model.RespuestaWs;

import java.util.List;

@Local
public interface DoctorService {
    public RespuestaWs guardar(Doctor doctor);
    // metodo eliminar prueba
    //public void eliminar(Doctor doctor);
    public List<Doctor> consultarXEstado(String estado);
    public Doctor buscarPorId(Long id);


}
