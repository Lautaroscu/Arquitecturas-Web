package services;

import DTOs.carrera.CantInscriptosCarreraDTO;
import DTOs.carrera.CarreraDTO;
import DTOs.carrera.CarreraMapper;
import DTOs.carrera.CarreraReporteDTO;
import entities.Carrera;
import repositorios.interfaces.RepositorioCarrera;

import java.util.List;

public class ServicioCarrera {
    private CarreraMapper carreraMapper;
    private RepositorioCarrera repositorioCarrera;

    public ServicioCarrera(RepositorioCarrera repositorioCarrera) {
        this.carreraMapper = new CarreraMapper();
        this.repositorioCarrera = repositorioCarrera;
    }

    public CarreraDTO obtenerCarrera(int id) {
        Carrera carrera = repositorioCarrera.recuperarCarrera(id);
        return carreraMapper.apply(carrera);
    }

    public void adicionarCarrera(Carrera carrera) {
        repositorioCarrera.adicionarCarrera(carrera);
    }

    public void removerCarrera(int id) {
        repositorioCarrera.removerCarrera(id);
    }

    public void modificarCarrera(CarreraDTO carrera) {
        Carrera c = transformarDtoAEntidad(carrera);
        repositorioCarrera.modificarCarrera(c);
    }

    public List<CarreraReporteDTO> generarReporteInscriptosEgresados() {
        return repositorioCarrera.obtenerReporteInscriptosEgresados();
    }

    private Carrera transformarDtoAEntidad(CarreraDTO carreraDTO) {
        return new Carrera(carreraDTO.getId_carrera(), carreraDTO.getNombre());
    }

    public List<CantInscriptosCarreraDTO> obtenerCarrerasPorCantInscriptos() {
        return repositorioCarrera.recuperarCarrerasSortByCantInscp();
    }
}
