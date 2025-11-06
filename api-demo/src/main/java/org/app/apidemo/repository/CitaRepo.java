package org.app.apidemo.repository;

import org.app.apidemo.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CitaRepo extends JpaRepository<Cita,Long> {
    List<Cita> findAllByEstadoOrderByIdAsc(String estado);
}
