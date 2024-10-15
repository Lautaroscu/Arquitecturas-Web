package repositorios.implementaciones;

import DTOs.carrera.CantInscriptosCarreraDTO;
import DTOs.carrera.CarreraReporteDTO;
import entities.Carrera;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;
import repositorios.BaseRepository;
import repositorios.interfaces.RepositorioCarrera;

import java.util.List;

@Getter
@Setter
public class RepositorioCarreraImpl extends BaseRepository implements RepositorioCarrera {
    private static RepositorioCarreraImpl instance;

    private RepositorioCarreraImpl(EntityManager em) {
        super.em = em;
    }

    //Singleton
    public static RepositorioCarreraImpl getInstance(EntityManager em) {
        if (instance == null) {
            instance = new RepositorioCarreraImpl(em);
        }
        return instance;
    }

    @Override
    public Carrera recuperarCarrera(int id) {
        Carrera c = em.find(Carrera.class, id);
        return c;
    }

    @Override
    public void adicionarCarrera(Carrera carrera) {

        try {
            em.getTransaction().begin();

            em.persist(carrera);

            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction() != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public void removerCarrera(int id) {
        Carrera carrera = recuperarCarrera(id);
        if (carrera != null) {
            em.remove(carrera);
        }
    }

    @Override
    public void modificarCarrera(Carrera carrera) {
        if (em.contains(carrera)) {
            em.merge(carrera);
        }
    }

    @Override
    public List<CarreraReporteDTO> obtenerReporteInscriptosEgresados() {
        return em.createNamedQuery("Carrera.ReporteInscriptosEgresados", CarreraReporteDTO.class)
                .getResultList();
    }

    @Override
    public List<CantInscriptosCarreraDTO> recuperarCarrerasSortByCantInscp() {

        return em.createNamedQuery(Carrera.CARRERASSORTCANTINSC, CantInscriptosCarreraDTO.class).getResultList();
    }
}
