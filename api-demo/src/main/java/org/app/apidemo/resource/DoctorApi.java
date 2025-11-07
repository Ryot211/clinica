package org.app.apidemo.resource;
import org.app.apidemo.entity.Doctor;
import org.app.apidemo.entity.Paciente;
import org.app.apidemo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DoctorApi {
    @Autowired
    private DoctorService doctorService;
    // Buscar doctor por ID
    @GetMapping(value = "doctores/buscar/{id}")
    public ResponseEntity<Doctor> buscarPorId(@PathVariable Long id) {
        Doctor doctor = doctorService.buscarPorId(id);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="doctores/consultar/{estado}")
    public ResponseEntity<List<Doctor>> consultarDoctor(@PathVariable String estado){
        return new ResponseEntity<>(doctorService.consultarDoctorXEstado(estado), HttpStatus.OK);
    }
    // Crear Doctor
    @PostMapping(value = "doctores/crear")
    public ResponseEntity<Map<String, Object>> createDoctor(@RequestBody Doctor doctor) {
        Map<String, Object> respuesta = new HashMap<>();

        try {
            doctorService.crearDoctor(doctor);
            respuesta.put("estado", true);
            respuesta.put("mensaje", "Doctor registrado correctamente");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            respuesta.put("estado", false);
            respuesta.put("mensaje", "Error al registrar doctor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }
    // Actualizar doctor
    @PostMapping(value= "doctores/actualizar")
    public ResponseEntity<Doctor> actualizarDoctor(@RequestBody Doctor doctor){
        return new ResponseEntity<>(doctorService.actualizarDoctor(doctor), HttpStatus.OK);
    }
    // Eliminar Doctor
    @DeleteMapping(value = "doctores/eliminar/{id}")
    public ResponseEntity<String> eliminarDoctor(@PathVariable Long id) {
        boolean exito = doctorService.eliminarDoctor(id);
        if (exito) {
            return ResponseEntity.ok("Paciente eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente no encontrado");
        }
    }
}
