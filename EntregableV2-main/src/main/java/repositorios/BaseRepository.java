package repositorios;

import jakarta.persistence.EntityManager;

public abstract class BaseRepository {
    protected EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
