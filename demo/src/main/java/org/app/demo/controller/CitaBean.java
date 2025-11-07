package org.app.demo.controller;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import org.app.demo.model.Cita;
import org.app.demo.model.Paciente;
import org.app.demo.model.Doctor;
import org.app.demo.services.interfaces.PacienteService;
import org.app.demo.services.interfaces.DoctorService;
import org.app.demo.services.interfaces.CitaService;

import java.io.Serializable;
import java.util.List;

@Named("citaBean")
@ViewScoped
public class CitaBean implements Serializable {

    private List<Cita> listaCitas;

    @EJB
    private CitaService citaService;

    @EJB
    private PacienteService pacienteService;

    @EJB
    private DoctorService doctorService;


    public void loadCitas(String estado) {
        listaCitas = citaService.consultarXEstado(estado);
        loadCitas("ACTIVO");
        // Completar nombres de paciente y doctor
        for (Cita c : listaCitas) {
            Paciente p = pacienteService.buscarPorId(c.getId_paciente());
            Doctor d = doctorService.buscarPorId(c.getId_doctor());
            c.setPaciente(p);
            c.setDoctor(d);
        }
    }

    public List<Cita> getListaCitas() {
        return listaCitas;
    }
    @PostConstruct
    public void init() {
        listaCitas = citaService.obtenerTodas();
    }



    public String obtenerNombrePaciente(Long idPaciente) {
        if (idPaciente == null) return "";
        Paciente paciente = pacienteService.buscarPorId(idPaciente);
        return (paciente != null) ? paciente.getNombre() : "";
    }

    public String obtenerNombreDoctor(Long idDoctor) {
        if (idDoctor == null) return "";
        Doctor doctor = doctorService.buscarPorId(idDoctor);
        return (doctor != null) ? doctor.getNombre() : "";
    }



}
