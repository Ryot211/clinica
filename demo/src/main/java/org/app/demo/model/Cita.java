package org.app.demo.model;

import org.app.demo.common.StatusType;

public class Cita {
    public Long id;
    public String indetificacion;
    public String fecha;
    public String motivo;
    private Long id_paciente;
    private Long id_doctor;
    private String estado;

    public Cita(){this.estado = StatusType.ACTIVO.name();}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndetificacion() {
        return indetificacion;
    }

    public void setIndetificacion(String indetificacion) {
        this.indetificacion = indetificacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Long getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(Long id_paciente) {
        this.id_paciente = id_paciente;
    }

    public Long getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(Long id_doctor) {
        this.id_doctor = id_doctor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
