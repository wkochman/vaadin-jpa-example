package eu.sii.pl.domain.utils;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;

import eu.sii.pl.domain.model.User;

public class DBUtils {
    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("test").createEntityManager();
    }

    public static JPAContainer<User> getUsersContainer() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

        EntityManager em = emf.createEntityManager();

        CachingMutableLocalEntityProvider<User> entityProvider = new CachingMutableLocalEntityProvider<User>(User.class, em);

        JPAContainer<User> persons = new JPAContainer<User>(User.class);
        persons.setEntityProvider(entityProvider);
        return persons;
    }

    public static void populate() {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        for (int i = 0; i < 10000; i++) {
            User u = new User();
            u.setId(Long.valueOf(i));
            u.setName("f_name_" + i);
            u.setLastName("l_name_" + i);
            u.setBirthday(new Date());
            entityManager.persist(u);
        }
        entityManager.getTransaction().commit();
    }
}
