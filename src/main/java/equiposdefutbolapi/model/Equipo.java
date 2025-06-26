package equiposdefutbolapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String liga;

    @Column(nullable = false)
    private String pais;

}
