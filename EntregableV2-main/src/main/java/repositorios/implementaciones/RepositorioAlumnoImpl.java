package repositorios.implementaciones;

import entities.Alumno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import repositorios.BaseRepository;
import repositorios.interfaces.RepositorioAlumno;

import java.util.List;

public class RepositorioAlumnoImpl extends BaseRepository implements RepositorioAlumno {
    private static RepositorioAlumnoImpl instancia;

    private RepositorioAlumnoImpl(EntityManager em) {
        super.em = em;
    }

    //Singleton
    public static RepositorioAlumnoImpl getInstancia(EntityManager em) {
        if (instancia == null) {
            instancia = new RepositorioAlumnoImpl(em);
        }
        return instancia;
    }

    public void altaAlumno(Alumno alumno) {
        if (alumno != null) {
            try {
                em.getTransaction().begin();  // Iniciar la transacción

                em.persist(alumno);

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
    public void bajaAlumno(int nroLibreta) {
        EntityTransaction transaction = em.getTransaction(); // Obtenemos la transacción
        try {
            transaction.begin(); // Iniciamos la transacción

            Alumno a = this.recuperarAlumnoPorNroLib(nroLibreta); // Recuperamos al alumno
            if (a != null) {
                em.remove(a); // Si existe, lo eliminamos
            } else {
                throw new IllegalArgumentException("El alumno no existe");
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
    public void modificarAlumno(Alumno alumno) {
        EntityTransaction transaction = em.getTransaction(); // Obtener la transacción
        try {
            transaction.begin(); // Iniciar la transacción

            if (alumno == null || alumno.getNro_libreta() == 0) {
                throw new IllegalArgumentException("El alumno debe existir en la base de datos");
            }

            em.merge(alumno); // Actualizar el alumno

            transaction.commit(); // Confirmar la transacción

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Hacer rollback en caso de error
            }
            throw e; // Lanzar nuevamente la excepción
        }
    }

    @Override
    public List<Alumno> listarAlumnos() {
        return em.createNamedQuery(Alumno.LISTARALUMNOS, Alumno.class).getResultList();
    }

    @Override
    public List<Alumno> recuperarAlumnosSortByApellido() {
        return em.createNamedQuery(Alumno.LISTARALUMNOSSORTAPELLIDO, Alumno.class).getResultList();
    }

    @Override
    public Alumno recuperarAlumnoPorNroLib(int nroLib) {
        return em.find(Alumno.class, nroLib);
    }

    @Override
    public List<Alumno> recuperarAlumnosPorGenero(String genero) {
        TypedQuery<Alumno> q = em.createNamedQuery(Alumno.ALUMNOSPORGENERO, Alumno.class);
        q.setParameter("genero", genero);
        return q.getResultList();
    }

    @Override
    public List<Alumno> recuperarAlumnosPorCarrerayCiudad(String carrera, String ciudad) {
        TypedQuery<Alumno> q = em.createNamedQuery(Alumno.ALUMNOCARRERAYCIUDAD, Alumno.class);
        q.setParameter("ciudad", ciudad);
        q.setParameter("carrera", carrera);
        return q.getResultList();
    }
}
