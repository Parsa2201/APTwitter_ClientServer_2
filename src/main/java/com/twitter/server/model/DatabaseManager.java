package com.twitter.server.model;

import com.twitter.entities.exception.io.server.DataNotFoundException;
import com.twitter.entities.user.FollowRelation;
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
    public SessionFactory sessionFactory;

    public DatabaseManager()
    {
        StandardServiceRegistry serviceRegistry;
        Metadata metadata;

        serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public User findUser(String userName , Session session) throws DataNotFoundException
    {
        Query<User> userQuery = session.createQuery("select u from User u where u.userName = :userName", User.class);
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
    public FollowRelation isFollowRelationExist(FollowRelation followRelation, Session session)
    {
        List<FollowRelation> followRelations = session.createQuery("select f from FollowRelation f", FollowRelation.class).list();
        for (FollowRelation followRe:followRelations)
        {
            if(followRe.equals(followRelation))
            {
                return followRe;
            }
        }
        return null;
    }
}
