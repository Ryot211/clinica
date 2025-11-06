package org.app.apidemo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="doctor")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Doctor {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional=false)
    @Column(name="id_doctor")
    private Long id;
    private String identificacion;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String edad;
    private String correo;
    private String telefono;
    private String estado;


}
