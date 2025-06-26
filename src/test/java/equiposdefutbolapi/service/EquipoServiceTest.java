package equiposdefutbolapi.service;

import equiposdefutbolapi.model.Equipo;
import equiposdefutbolapi.repository.EquipoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipoServiceTest {

    @Mock
    private EquipoRepository repository;

    @InjectMocks
    private EquipoService service;

    @Test
    void get_equipos() {
        List<Equipo> equipos = List.of(Equipo.builder().id(1).nombre("Real Madrid").build(),
                                       Equipo.builder().id(2).nombre("FC Barcelona").build());
        when(repository.findAll()).thenReturn(equipos);

        List<Equipo> result = service.getEquipos();

        assertEquals(equipos, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void get_equipo_found() {
        Equipo equipo = Equipo.builder().id(1).nombre("Real Madrid").build();
        when(repository.findById(equipo.getId())).thenReturn(Optional.of(equipo));

        Equipo result = service.getEquipo(equipo.getId());

        assertEquals(equipo, result);
        verify(repository, times(1)).findById(equipo.getId());
    }

    @Test
    void get_equipo_not_found() {
        Integer id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(HttpClientErrorException.class, () -> service.getEquipo(id));
        verify(repository, times(1)).findById(id);
    }

    @Test
    void create_equipo() {
        Equipo equipo = Equipo.builder()
                .nombre("Real Madrid")
                .liga("La Liga")
                .pais("España")
                .build();
        Equipo savedEquipo = Equipo.builder()
                .id(1)
                .nombre(equipo.getNombre())
                .liga(equipo.getLiga())
                .pais(equipo.getPais())
                .build();

        when(repository.save(equipo)).thenReturn(savedEquipo);

        Equipo result = service.createEquipo(equipo);

        assertEquals(savedEquipo, result);
        verify(repository, times(1)).save(equipo);
    }

    @Test
    void update_equipo() {
        Integer id = 1;
        Equipo equipoRequest = Equipo.builder().nombre("FC Barcelona").build();
        Equipo equipoUpdated = Equipo.builder().id(id).nombre(equipoRequest.getNombre()).build();
        when(repository.existsById(id)).thenReturn(true);
        when(repository.save(equipoRequest)).thenReturn(equipoUpdated);

        Equipo result = service.updateEquipo(id, equipoRequest);

        assertEquals(equipoUpdated, result);
        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).save(equipoRequest);
    }

    @Test
    void delete_equipo() {
        Integer id = 1;
        when(repository.existsById(id)).thenReturn(true);
        doNothing().when(repository).deleteById(id);

        service.deleteEquipo(id);

        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).deleteById(id);
    }

}
