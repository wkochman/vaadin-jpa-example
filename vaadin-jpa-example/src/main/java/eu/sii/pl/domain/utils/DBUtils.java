package eu.sii.pl.domain.utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DBUtils {
    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("test").createEntityManager();
    }
}
