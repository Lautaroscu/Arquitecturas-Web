package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "alumno")
@Getter
@Setter
@ToString
@NamedQuery(name = Alumno.ALUMNOSSORTNROLIB, query = "SELECT a FROM Alumno a ORDER BY a.nro_libreta")

@NamedQuery(name = Alumno.ALUMNOSPORGENERO, query = "SELECT a FROM Alumno a WHERE a.genero = :genero")

@NamedQuery(name = Alumno.ALUMNOCARRERAYCIUDAD, query = "SELECT a FROM Alumno a WHERE a.ciudad_residencia = :ciudad AND a IN (SELECT i.alumno FROM Inscripcion i WHERE i.carrera.nombre = :carrera)")

@NamedQuery(name = Alumno.LISTARALUMNOSSORTAPELLIDO, query = "SELECT a FROM Alumno a ORDER BY a.apellido")

@NamedQuery(name = Alumno.LISTARALUMNOS, query = "SELECT a FROM Alumno a ORDER BY a.apellido")
public class Alumno {
    public static final String LISTARALUMNOS = "Alumno.ListarAlumnos";
    public static final String ALUMNOSSORTNROLIB = "Alumno.Alumnossortnrolib";
    public static final String ALUMNOSPORGENERO = "Alumno.Alumnosporgenero";
    public static final String ALUMNOCARRERAYCIUDAD = "Alumno.Alumnocarryayciudad";
    public static final String LISTARALUMNOSSORTAPELLIDO = "Alumno.Listaralumnossortapellido";

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
