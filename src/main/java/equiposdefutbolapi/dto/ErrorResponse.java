package equiposdefutbolapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private String mensaje;
    private int codigo;

}
