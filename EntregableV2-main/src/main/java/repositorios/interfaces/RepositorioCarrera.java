package repositorios.interfaces;

import DTOs.carrera.CantInscriptosCarreraDTO;
import DTOs.carrera.CarreraReporteDTO;
import entities.Carrera;

import java.util.List;

public interface RepositorioCarrera {
    public Carrera recuperarCarrera(int id);

    public void adicionarCarrera(Carrera carrera);

    public void removerCarrera(int id);

    public void modificarCarrera(Carrera carrera);

    public List<CarreraReporteDTO> obtenerReporteInscriptosEgresados();

    List<CantInscriptosCarreraDTO> recuperarCarrerasSortByCantInscp();
}
