package org.app.apidemo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cita")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id_cita")
    private Long id;
    private String fecha;
    private String motivo;
    private Long id_paciente;
    private Long id_doctor;
    private String estado;
}
