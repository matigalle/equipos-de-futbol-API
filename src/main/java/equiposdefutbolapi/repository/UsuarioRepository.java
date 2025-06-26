package equiposdefutbolapi.repository;

import equiposdefutbolapi.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);

}
