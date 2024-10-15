package tp3.entregable3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp3.entregable3.DTOs.inscripcion.InputInscripcionDTO;
import tp3.entregable3.DTOs.inscripcion.OutputInscripcionDTO;
import tp3.entregable3.entities.Alumno;
import tp3.entregable3.entities.Carrera;
import tp3.entregable3.entities.Inscripcion;
import tp3.entregable3.entities.InscripcionId;
import tp3.entregable3.exceptions.NotFoundException;
import tp3.entregable3.repositories.AlumnoRepository;
import tp3.entregable3.repositories.CarreraRepository;
import tp3.entregable3.repositories.InscripcionRepository;

import java.util.List;

@Service
public class InscripcionService {
    private final InscripcionRepository inscripcionRepository;
    private final AlumnoRepository alumnoRepository;
    private final CarreraRepository carreraRepository;

    @Autowired
    public InscripcionService(InscripcionRepository inscripcionRepository, AlumnoRepository alumnoRepository, CarreraRepository carreraRepository) {
        this.alumnoRepository = alumnoRepository;
        this.carreraRepository = carreraRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    public List<OutputInscripcionDTO> obtenerInscripciones() {
        return inscripcionRepository
                .findAll()
                .stream()
                .map(OutputInscripcionDTO::new)
                .toList();
    }

    public OutputInscripcionDTO altaAlumnoCarrera(InputInscripcionDTO dto) {
        Alumno alumno = alumnoRepository.findById(dto.getNroLibreta()).orElseThrow(() -> new NotFoundException("Student not found"));
        Carrera carrera = carreraRepository.findById(dto.getIdCarrera()).orElseThrow(() -> new NotFoundException("Carrera not found"));
        Inscripcion inscripcion = new Inscripcion(alumno, carrera);
        inscripcionRepository.save(inscripcion);
        return new OutputInscripcionDTO(inscripcion);
    }

    public OutputInscripcionDTO updateInscripcion(int nroLibreta, int idCarrera, InputInscripcionDTO dto) {
        Inscripcion inscripcionToModified = inscripcionRepository.findById(new InscripcionId(nroLibreta, idCarrera)).orElseThrow(() -> new NotFoundException("Inscripcion not found"));
        inscripcionToModified.setNroLibreta(nroLibreta);
        inscripcionToModified.setIdCarrera(idCarrera);
        inscripcionToModified.setCarrera(carreraRepository.findById(dto.getIdCarrera()).orElseThrow(() -> new NotFoundException("Carrera not found")));
        inscripcionToModified.setAlumno(alumnoRepository.findById(dto.getNroLibreta()).orElseThrow(() -> new NotFoundException("Alumno not found")));
        inscripcionToModified.setSeGraduo(dto.isSeGraduo());
        inscripcionToModified.setFechaInscripcion(dto.getFechaInscripcion());
        inscripcionRepository.save(inscripcionToModified);
        return new OutputInscripcionDTO(inscripcionToModified);
    }

    public OutputInscripcionDTO findInscripcion(int nroLibreta, int idCarrera) {
        InscripcionId id = new InscripcionId(nroLibreta, idCarrera);
        Inscripcion inscripcion = inscripcionRepository.findById(id).orElseThrow(() -> new NotFoundException("Inscripcion not found"));
        return new OutputInscripcionDTO(inscripcion);
    }

    public OutputInscripcionDTO delete(int nroLibreta, int idCarrera) {
        InscripcionId id = new InscripcionId(idCarrera, nroLibreta);
        Inscripcion inscripcion = inscripcionRepository.findById(id).orElseThrow(() -> new NotFoundException("Inscripcion not found"));
        inscripcionRepository.delete(inscripcion);
        return new OutputInscripcionDTO(inscripcion);
    }
}
