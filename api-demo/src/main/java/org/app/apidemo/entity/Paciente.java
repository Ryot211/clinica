package org.app.apidemo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id_paciente")
    private Long id;
    private String identificacion;
    private String nombre;
    private String apellido;
    private String edad;
    private String correo;
    private String telefono;
    private String estado;
}
