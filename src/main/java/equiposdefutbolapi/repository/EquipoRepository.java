package equiposdefutbolapi.repository;

import equiposdefutbolapi.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

    List<Equipo> findByNombreContainingIgnoreCase(String nombre);

}
