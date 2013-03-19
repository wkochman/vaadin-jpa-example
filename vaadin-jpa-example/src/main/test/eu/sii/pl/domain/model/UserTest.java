package eu.sii.pl.domain.model;

import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.Test;

import eu.sii.pl.domain.utils.DBUtils;

public class UserTest {
    
    @Test
    public void testSaveUser(){
        EntityManager entityManager = DBUtils.getEntityManager();
        entityManager.getTransaction().begin();
        User u = new User();
        u.setId(1l);
        u.setName("Test");
        u.setLastName("Test");
        u.setBirthday(new Date());
        entityManager.persist(u);
        entityManager.getTransaction().commit();
        System.out.println(entityManager.find(User.class, 1l));
    }

}
