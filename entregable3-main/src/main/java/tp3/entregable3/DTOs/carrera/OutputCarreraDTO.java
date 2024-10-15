package tp3.entregable3.DTOs.carrera;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tp3.entregable3.entities.Carrera;

@Getter
@Setter
public class OutputCarreraDTO {
    private int id;
    private String nombre;

    public OutputCarreraDTO(Carrera c) {
        this.id = c.getId_carrera();
        this.nombre = c.getNombre();
    }
}
