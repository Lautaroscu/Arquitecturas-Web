package tp3.entregable3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tp3.entregable3.DTOs.carrera.CarreraReporteDTO;
import tp3.entregable3.DTOs.carrera.OutputCarreraDTO;
import tp3.entregable3.entities.Carrera;
import tp3.entregable3.exceptions.BadRequestException;
import tp3.entregable3.exceptions.NotFoundException;
import tp3.entregable3.repositories.CarreraRepository;

import java.util.List;

@Service
public class CarreraService {
    private final CarreraRepository carreraRepository;

    @Autowired
    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    public List<?> findAll(boolean sortByInscriptos) {
        if (sortByInscriptos) {
            return carreraRepository.findCarrerasOrderByCantInscriptos();
        }

        return carreraRepository.findAll().stream().map(OutputCarreraDTO::new).toList();
    }
    public List<CarreraReporteDTO> getCarrerasReporteDTO() {
        return carreraRepository.reporteDeCarreras();
    }

    public OutputCarreraDTO saveCarrera(String nombre) {
        Carrera carrera1 = new Carrera(nombre);
        carrera1 = carreraRepository.save(carrera1);
        return new OutputCarreraDTO(carrera1);
    }

    public OutputCarreraDTO deleteCarrera(int id) {
        Carrera carrera = carreraRepository.findById(id).orElseThrow(() -> new NotFoundException("Carrera not found"));
        carreraRepository.delete(carrera);
        return new OutputCarreraDTO(carrera);
    }

    public OutputCarreraDTO updateCarrera(int idCarrera, String nombre) {
        Carrera carreraToModified = carreraRepository.findById(idCarrera).orElseThrow(() -> new NotFoundException("Carrera not found"));
        if (nombre == null || nombre.isEmpty()) {
            throw new BadRequestException("Nombre invalido");
        }
        carreraToModified.setNombre(nombre);
        carreraToModified = carreraRepository.save(carreraToModified);
        return new OutputCarreraDTO(carreraToModified);
    }

    public OutputCarreraDTO findCarrera(int idCarrera) {
        Carrera c = carreraRepository.findById(idCarrera).orElseThrow(() -> new NotFoundException("Carrera not found"));
        return new OutputCarreraDTO(c);
    }
}
