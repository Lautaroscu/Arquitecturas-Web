package DTOs.carrera;

import entities.Carrera;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CantInscriptosCarreraDTO extends CarreraDTO {
    private Long cantInscriptos;

    public CantInscriptosCarreraDTO(Carrera c, Long cantInscriptos) {
        super(c);
        this.cantInscriptos = cantInscriptos;
    }
}
