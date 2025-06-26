package equiposdefutbolapi.controller;

import equiposdefutbolapi.model.Equipo;
import equiposdefutbolapi.service.EquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService service;

    @GetMapping
    public ResponseEntity<List<Equipo>> getEquipos() {
        return ResponseEntity.ok(service.getEquipos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getEquipo(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getEquipo(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Equipo>> searchEquipos(@RequestParam String nombre) {
        return ResponseEntity.ok(service.searchEquipos(nombre));
    }

    @PostMapping
    public ResponseEntity<Equipo> createEquipo(@RequestBody Equipo equipo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createEquipo(equipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable Integer id, @RequestBody Equipo equipo) {
        return ResponseEntity.ok(service.updateEquipo(id, equipo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable Integer id) {
        service.deleteEquipo(id);
        return ResponseEntity.noContent().build();
    }

}
