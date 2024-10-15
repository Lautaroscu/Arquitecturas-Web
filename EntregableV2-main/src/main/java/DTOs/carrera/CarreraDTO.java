package DTOs.carrera;


import entities.Carrera;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CarreraDTO implements Serializable {
    private int id_carrera;
    private String nombre;

    public CarreraDTO(Carrera c) {
        this.id_carrera = c.getId_carrera();
        this.nombre = c.getNombre();

    }
}
