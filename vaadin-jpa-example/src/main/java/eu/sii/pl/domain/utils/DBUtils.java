package eu.sii.pl.domain.utils;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;

import eu.sii.pl.domain.model.Service;
import eu.sii.pl.domain.model.User;

public class DBUtils {
    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("test").createEntityManager();
    }

    public static JPAContainer<User> getUsersContainer() {
        JPAContainer<User> users = new JPAContainer<User>(User.class);
        users.setEntityProvider(new CachingMutableLocalEntityProvider<User>(User.class, getEntityManager()));
        return users;
    }
    public static JPAContainer<Service> getServiceContainer() {
        JPAContainer<Service> services = new JPAContainer<Service>(Service.class);
        services.setEntityProvider(new CachingMutableLocalEntityProvider<Service>(Service.class, getEntityManager()));
        services.setAutoCommit(true);
        return services;
    }

    public static void populate() {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        for (int i = 0; i < 20; i++) {
            User u = new User();
            u.setName("service_" + i);
            u.setLastName("l_name_" + i);
            u.setBirthday(new Date());
            entityManager.persist(u);
            
            Service s = new Service();
            s.setName("service_"+i);
            s.setStartDate(new Date());
            s.setSalesPerson(u);
            s.setServicePerson(u);
            entityManager.persist(s);
        }
        entityManager.getTransaction().commit();
    }
}
