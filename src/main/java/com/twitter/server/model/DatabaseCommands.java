package com.twitter.server.model;

import com.twitter.entities.exception.io.server.DataNotFoundException;
import com.twitter.entities.exception.user.password.InvalidPasswordException;
import com.twitter.entities.exception.text.TextTooLongException;
import com.twitter.entities.image.Avatar;
import com.twitter.entities.image.Header;
import com.twitter.entities.user.*;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.List;

public class DatabaseCommands
{
    private final DatabaseManager databaseManager;
    public DatabaseCommands()
    {
        databaseManager = new DatabaseManager();
    }

    public void signUp(User user)
    {
        try(Session session = databaseManager.sessionFactory.openSession())
        {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            // TODO: print suitable error
        } catch (PersistenceException e){
            System.out.println("this user already exist");
        }
    }
    public User signIn (String userName, Password passwordHash) throws InvalidPasswordException, DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        if(user.getPassHash().equals(passwordHash))
        {
            return user;
        }
        else
        {
            throw new InvalidPasswordException();
        }
    }

    public void setAvatar(String userName, Avatar avatar) throws DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setAvatar(avatar);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void setHeader(String userName, Header header) throws DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setHeader(header);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void changeUserInformation(String userName, Bio bio, String location, String website) throws TextTooLongException, DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        List<User> users = session.createQuery("select u from User u", User.class).list();
        User user = databaseManager.findUser(userName, session);
        user.setBio(bio);
        user.setLocation(location);
        user.setWebsite(website);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void follow (FollowRelation followRelation) throws DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        databaseManager.findUser(followRelation.getFollowedUsername(), session);
        databaseManager.findUser(followRelation.getUsername(), session);
        session.beginTransaction();
        session.persist(followRelation);
        session.getTransaction().commit();
        session.close();
    }
    public void unfollow (FollowRelation followRelation) throws DataNotFoundException
    {

        Session session = databaseManager.sessionFactory.openSession();
        if(databaseManager.isFollowRelationExist(followRelation, session))
        {
            session.beginTransaction();
            session.delete(followRelation);
            session.getTransaction().commit();
            session.close();
        }
        else
        {
            session.close();
            throw new DataNotFoundException();
        }

    }
    public MiniUser showUser (String userName) throws DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName,session);
        return user.toMiniUser();
    }
}
