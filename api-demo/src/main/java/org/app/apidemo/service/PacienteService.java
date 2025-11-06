package org.app.apidemo.service;
import org.app.apidemo.entity.Paciente;
import org.app.apidemo.repository.PacienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepo pacienteRepo;
    // Crear un nuevo paciente POST
    public Paciente crearPaciente(Paciente paciente){
        paciente.setEstado("ACTIVO");
        return pacienteRepo.save(paciente);
    }

    //GET
    public List<Paciente> consultarPacienteXEstado(String estado) {
        return pacienteRepo.findAllByEstadoOrderByIdAsc(estado);
    }
    // GET
    public Paciente actualizarPaciente(Paciente paciente) {
        return pacienteRepo.save(paciente);
    }

    // Eliminar Paciente, --actualizar estado a DESACTIVADO
    public boolean eliminarPaciente(Long id){
        Optional<Paciente> PacienteOpt = pacienteRepo.findById(id);
        if(PacienteOpt.isPresent()){
            Paciente paciente = PacienteOpt.get();
            paciente.setEstado("DESACTIVADO");
            pacienteRepo.save(paciente);
            return true;// Eliminacion lograda
        }
        return false; // falto el id del paciente
    }

}
