package org.app.apidemo.service;

import org.app.apidemo.entity.Cita;
import org.app.apidemo.repository.CitaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {
    @Autowired
    private CitaRepo citaRepo;
    // Crear una cita
    public Cita creaCita(Cita cita){
        cita.setEstado("ACTIVO");
        return citaRepo.save(cita);
    }
    // Obtener Cita por Estado
    public List<Cita> consultarCitaXEstado(String estado)
    {
        return citaRepo.findAllByEstadoOrderByIdAsc(estado);
    }
    // Actualizar Cita
    public Cita actualizarCita(Cita cita){
        return citaRepo.save(cita);
    }
    // Eliminar Cita de manera cambio de estado
    public boolean eliminarCita(Long id){
        Optional<Cita> CitaOpt= citaRepo.findById(id);
        if(CitaOpt.isPresent()){
            Cita cita = CitaOpt.get();
            cita.setEstado("INACTIVO");
            citaRepo.save(cita);
            return true;// Se grabo correctamente
        }
        return false;/// fallo
    }
}
