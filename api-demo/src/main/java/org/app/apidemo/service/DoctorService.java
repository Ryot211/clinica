package org.app.apidemo.service;
import org.app.apidemo.entity.Doctor;
import org.app.apidemo.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepo doctorRepo;
    public Doctor buscarPorId(Long id) {
        return doctorRepo.findById(id).orElse(null);
    }

    // Crear un nuevo Doctor post
    public Doctor crearDoctor(Doctor doctor) {
        doctor.setEstado("ACTIVO");
        return doctorRepo.save(doctor);
    }
    // GET OBTENER DOCTORES POR ESTADO
    public List<Doctor> consultarDoctorXEstado(String estado){
        return doctorRepo.findAllByEstadoOrderByIdAsc(estado);
    }
    // POST ACTUALIZAR DOCTOR
    public Doctor actualizarDoctor(Doctor doctor){
        return doctorRepo.save(doctor);
    }
    // ELIMINAR UN DOCTOR Cambiar el estado a DESACTIVADO
    public boolean eliminarDoctor(Long id){
        Optional<Doctor> DoctorOpt= doctorRepo.findById(id);
        if(DoctorOpt.isPresent()){
            Doctor doctor = DoctorOpt.get();
            doctor.setEstado("DESACTIVADO");
            doctorRepo.save(doctor);
            return true;/// se "elimina" con exito
        }
        return false;// fallo falto el id o nose se proceso la soli
    }

}
