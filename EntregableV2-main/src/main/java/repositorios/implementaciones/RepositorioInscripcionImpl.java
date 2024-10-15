package repositorios.implementaciones;

import entities.Alumno;
import entities.Carrera;
import entities.Inscripcion;
import entities.InscripcionId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import repositorios.BaseRepository;
import repositorios.interfaces.RepositorioInscripcion;

import java.time.LocalDate;

public class RepositorioInscripcionImpl extends BaseRepository implements RepositorioInscripcion {
    private static RepositorioInscripcionImpl repositorioInscripcionImpl;

    private RepositorioInscripcionImpl(EntityManager em) {
        super.em = em;
    }

    //Singleton
    public static RepositorioInscripcionImpl getInstancia(EntityManager em) {
        if (repositorioInscripcionImpl == null) {
            repositorioInscripcionImpl = new RepositorioInscripcionImpl(em);
        }
        return repositorioInscripcionImpl;
    }

    @Override
    public Inscripcion obtenerInscripcion(InscripcionId id) {
        Inscripcion i = em.find(Inscripcion.class, id);
        return i;
    }

    private void agregarInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null) {
            try {
                em.getTransaction().begin();  // Iniciar la transacción

                em.persist(inscripcion);

                em.getTransaction().commit();  // Confirmar la transacción

            } catch (Exception e) {
                if (em.getTransaction() != null && em.getTransaction().isActive()) {
                    em.getTransaction().rollback();  // Si hay un error, hacer rollback
                }
                throw e;
            }
        }
    }

    @Override
    public void modificarInscripcion(Inscripcion inscripcion) {
        EntityTransaction transaction = em.getTransaction(); // Obtener la transacción
        try {
            transaction.begin(); // Iniciar la transacción

            if (inscripcion == null) {
                throw new IllegalArgumentException("El alumno debe existir en la base de datos");
            }

            em.merge(inscripcion); // Actualizar el alumno

            transaction.commit(); // Confirmar la transacción

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Hacer rollback en caso de error
            }
            throw e; // Lanzar nuevamente la excepción
        }
    }

    @Override
    public void eliminarInscripcion(InscripcionId id) {
        EntityTransaction transaction = em.getTransaction(); // Obtenemos la transacción
        try {
            transaction.begin(); // Iniciamos la transacción
            Inscripcion i = obtenerInscripcion(id);

            if (i != null) {
                em.remove(i); // Si existe, lo eliminamos
            } else {
                throw new IllegalArgumentException("La inscripcion no existe");
            }

            transaction.commit(); // Confirmamos la transacción

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Hacemos rollback en caso de error
            }
            throw e; // Volvemos a lanzar la excepción
        }
    }

    @Override
    public void matricularAlumnoCarrera(Alumno alumno, Carrera carrera) {
        Inscripcion inscripcion = new Inscripcion(alumno, carrera, LocalDate.now());
        agregarInscripcion(inscripcion);

    }
}
