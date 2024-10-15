package tp3.entregable3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "alumno")
@Getter
@Setter
@ToString

public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nro_libreta;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private int edad;

    @Column
    private String genero;

    @Column
    private String ciudad_residencia;

    public Alumno() {
    }

    public Alumno(String nombre, String apellido, int edad, String genero, String ciudad_residencia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad_residencia = ciudad_residencia;
    }
}