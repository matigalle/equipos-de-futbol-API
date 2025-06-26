package equiposdefutbolapi.service;

import equiposdefutbolapi.model.Equipo;
import equiposdefutbolapi.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoRepository repository;
    private static final String NOT_FOUND_EQUIPO = "Equipo no encontrado";

    public List<Equipo> getEquipos() {
        return repository.findAll();
    }

    public Equipo getEquipo(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, NOT_FOUND_EQUIPO));
    }

    public List<Equipo> searchEquipos(String nombre) {
        List<Equipo> equipos = repository.findByNombreContainingIgnoreCase(nombre);

        if (equipos.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No se encontró ningún equipo");
        }

        return equipos;
    }

    public Equipo createEquipo(Equipo equipo) {
        equipo.setId(null);
        return repository.save(equipo);
    }

    public Equipo updateEquipo(Integer id, Equipo equipo) {
        validateIfExists(id);
        equipo.setId(id);
        return repository.save(equipo);
    }

    public void deleteEquipo(Integer id) {
        validateIfExists(id);
        repository.deleteById(id);
    }

    private void validateIfExists(Integer id) {
        if (!repository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, NOT_FOUND_EQUIPO);
        }
    }

}
