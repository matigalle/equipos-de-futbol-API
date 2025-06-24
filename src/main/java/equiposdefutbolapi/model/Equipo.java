package equiposdefutbolapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String liga;
    private String pais;

}
