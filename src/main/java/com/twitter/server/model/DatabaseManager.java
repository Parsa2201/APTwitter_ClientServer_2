package com.twitter.server.model;

import com.twitter.entities.exception.io.server.DataNotFoundException;
import com.twitter.entities.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class DatabaseManager
{
    StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
    SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
    public User findUser(String userName , Session session) throws DataNotFoundException
    {
        Query<User> userQuery = session.createQuery("select u from User u where u.name = :userName", User.class);
        userQuery.setParameter("userName", userName);
        User user = userQuery.list().get(0);
        if(user == null)
        {
            throw new DataNotFoundException();
        }
        else
        {
            return user;
        }

    }
}
