package org.app.apidemo.resource;

import org.app.apidemo.entity.Cita;
import org.app.apidemo.entity.Doctor;
import org.app.apidemo.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CitaApi {
    @Autowired
    private CitaService citaService;
    @GetMapping(value = "citas/consultar/todas")
    public ResponseEntity<List<Cita>> obtenerTodas() {
        return new ResponseEntity<>(citaService.obtenerTodas(), HttpStatus.OK);
    }


    // Crear Cita
    @PostMapping(value = "citas/crear")
    public ResponseEntity<Map<String, Object>> createCita(@RequestBody Cita cita) {
        Map<String, Object> respuesta = new HashMap<>();

        try {
            citaService.creaCita(cita);
            respuesta.put("estado", true);
            respuesta.put("mensaje", "cita registrado correctamente");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            respuesta.put("estado", false);
            respuesta.put("mensaje", "Error al registrar cita: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }
    // Consultar cita x estado
    @GetMapping(value = "citas/consultar/{estado}")
    public ResponseEntity<List<Cita>> consultarCitas(@PathVariable String estado){
        return new ResponseEntity<>(citaService.consultarCitaXEstado(estado), HttpStatus.OK);
    }
    // Actualizar Citas
    @PostMapping(value = "citas/actualizar")
    public ResponseEntity<Cita> actualizarCita(@RequestBody Cita cita){
        return new ResponseEntity<>(citaService.actualizarCita(cita), HttpStatus.OK);
    }
    // Eliminar citas
    @DeleteMapping(value = "citas/eliminar/{id}")
    public ResponseEntity<String> eliminarCita(@PathVariable Long id){
        boolean exito = citaService.eliminarCita(id);
        if (exito) {
            return ResponseEntity.ok("Paciente eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente no encontrado");
        }
    }


}
