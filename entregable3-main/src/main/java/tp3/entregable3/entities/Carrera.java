package tp3.entregable3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "carrera")

public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_carrera;

    @Column
    private String nombre;

    public Carrera() {
    }

    public Carrera(String nombre) {
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}