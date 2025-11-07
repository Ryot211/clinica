package org.app.apidemo.resource;
import org.app.apidemo.entity.Paciente;
import org.app.apidemo.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PacienteApi {
    @Autowired
    private PacienteService pacienteService;

    //Consultar paciente por estado (ACTIVO/DESACTIVADO)
    @GetMapping(value= "pacientes/consultar/{estado}")
    public ResponseEntity<List<Paciente>> consultarPacientes(@PathVariable String estado){
        return new ResponseEntity<>(pacienteService.consultarPacienteXEstado(estado), HttpStatus.OK);
    }
    //Crear Pacientes

    @PostMapping(value = "pacientes/crear")
    public ResponseEntity<Map<String, Object>> createPaciente(@RequestBody Paciente paciente) {
        Map<String, Object> respuesta = new HashMap<>();

        try {
            pacienteService.crearPaciente(paciente);
            respuesta.put("estado", true);
            respuesta.put("mensaje", "Paciente registrado correctamente");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            respuesta.put("estado", false);
            respuesta.put("mensaje", "Error al registrar paciente: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

// Buscar paciente por ID
    @GetMapping(value = "pacientes/buscar/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        if (paciente != null) {
            return new ResponseEntity<>(paciente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value = "pacientes/actualizar")
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente){
        return  new ResponseEntity<>(pacienteService.actualizarPaciente(paciente), HttpStatus.OK);
    }
    @DeleteMapping(value = "pacientes/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) {
        boolean exito = pacienteService.eliminarPaciente(id);
        if (exito) {
            return ResponseEntity.ok("Paciente eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente no encontrado");
        }
    }
}
