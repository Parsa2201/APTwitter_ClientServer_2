package com.twitter.server.model;

import com.twitter.entities.exception.io.server.DataNotFoundException;
import com.twitter.entities.exception.user.CountryException;
import com.twitter.entities.exception.user.email.EmailFormatException;
import com.twitter.entities.exception.user.password.InvalidPasswordException;
import com.twitter.entities.exception.text.TextTooLongException;
import com.twitter.entities.image.Avatar;
import com.twitter.entities.image.Header;
import com.twitter.entities.tweet.Quote;
import com.twitter.entities.tweet.Retweet;
import com.twitter.entities.tweet.TimeLine;
import com.twitter.entities.tweet.Tweet;
import com.twitter.entities.user.*;
import com.twitter.entities.user.follow.FollowRelation;
import com.twitter.entities.user.follow.Followers;
import com.twitter.entities.user.follow.Followings;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.time.LocalDate;
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

//    public void changeUserInformation(String userName, Bio bio, String location, String website) throws TextTooLongException, DataNotFoundException
//    {
//        Session session = databaseManager.sessionFactory.openSession();
//        List<User> users = session.createQuery("select u from User u", User.class).list();
//        User user = databaseManager.findUser(userName, session);
//        user.setBio(bio);
//        user.setLocation(location);
//        user.setWebsite(website);
//        session.beginTransaction();
//        session.update(user);
//        session.getTransaction().commit();
//        session.close();
//    }

    public void changeUserPassword(String userName, Password newpass) throws DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setPassword(newpass);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void changeName(String userName, String name) throws DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setName(name);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }
    public void changeFamily(String userName, String family) throws DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setFamily(family);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }
    public void changeEmail(String userName, String email) throws DataNotFoundException, EmailFormatException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setEmail(email);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void changePhone(String userName, String phone) throws DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setPhoneNumber(phone);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void changeBirthDate(String userName, LocalDate birthDate) throws DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setBirthDate(birthDate);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void changeCountry (String userName, String country) throws CountryException, DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setCountry(country);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }
    public void changeLocation (String userName, String location) throws DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setLocation(location);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }
    public void changeWebsite (String userName, String website) throws DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setWebsite(website);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public Followers showFollowers(String userName) throws DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        Query<String> followerIDsQ = session.createQuery("select f.username from FollowRelation f where f.followedUsername = :userName", String.class);
        followerIDsQ.setParameter("userName", userName);
        List<String> followerIDs = followerIDsQ.list();
        Followers followers = new Followers();
        for (String flID:followerIDs)
        {
            followers.add(databaseManager.findUser(flID, session).toMiniUser());
        }
        return followers;
    }

    public Followings showFollowings(String userName) throws DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        Query<String> followingsIDsQ = session.createQuery("select f.followedUsername from FollowRelation f where f.username = :userName", String.class);
        followingsIDsQ.setParameter("userName", userName);
        List<String> followingsIDs = followingsIDsQ.list();
        Followings followings = new Followings();
        for (String flID:followingsIDs)
        {
            followings.add(databaseManager.findUser(flID, session).toMiniUser());
        }
        return followings;
    }

    public void changeBio (String userName, Bio bio) throws DataNotFoundException, TextTooLongException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setBio(bio);
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
        if((followRelation = databaseManager.isFollowRelationExist(followRelation, session)) != null)
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

    public void sendTweet(Tweet tweet)
    {
        // TODO
    }

    public void sendRetweet(Retweet retweet)
    {
        // TODO
    }

    public void sendQuote(Quote quote)
    {
        // TODO
    }

    public void likeTweet(Tweet tweet, String userName)
    {
        // TODO
    }

    public void dislikeTweet(Tweet tweet, String userName)
    {
        // TODO
    }

    public TimeLine showTimeLine(String userName)
    {
        // TODO
        return null;
    }

    public void block(String blocker, String blocked)
    {
        // TODO
    }

    public void unblock(String blocker, String blocked)
    {
        // TODO
    }
}
