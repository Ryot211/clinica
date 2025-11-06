package org.app.apidemo.repository;
import org.app.apidemo.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepo extends JpaRepository<Paciente,Long> {
    List<Paciente> findAllByEstadoOrderByIdAsc(String estado);
}
