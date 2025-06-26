package equiposdefutbolapi.controller;

import equiposdefutbolapi.dto.EquipoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EquipoController {

    @GetMapping("/equipos")
    public ResponseEntity<List<EquipoResponse>> getEquipos() {
        List<EquipoResponse> equipos = List.of();

        return ResponseEntity.ok(equipos);
    }

}
