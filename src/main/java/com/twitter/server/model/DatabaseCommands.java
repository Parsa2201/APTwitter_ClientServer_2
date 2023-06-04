package com.twitter.server.model;

import com.twitter.entities.exception.io.server.DataNotFoundException;
import com.twitter.entities.exception.text.TextTooLongException;
import com.twitter.entities.exception.user.CountryException;
import com.twitter.entities.exception.user.email.EmailFormatException;
import com.twitter.entities.exception.user.password.InvalidPasswordException;
import com.twitter.entities.image.Avatar;
import com.twitter.entities.image.Header;
import com.twitter.entities.tweet.*;
import com.twitter.entities.tweet.content.hashtag.Hashtag;
import com.twitter.entities.user.*;
import com.twitter.entities.user.follow.FollowRelation;
import com.twitter.entities.user.follow.Followers;
import com.twitter.entities.user.follow.Followings;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.time.LocalDate;

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
        session.persist(avatar);
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
        session.persist(header);
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

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
        Query<User> userQuery = session.createQuery("select f.user from FollowRelation f where f.followedUser.userName = :user");
        userQuery.setParameter("user", userName);
        Followers followers = new Followers();
        for (User u : userQuery.list())
        {
            followers.add(u.toMiniUser());
        }
        return followers;
    }

    public Followings showFollowings(String userName) throws DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        Query<User> userQuery = session.createQuery("select f.followedUser from FollowRelation f where f.followedUser.userName = :user");
        userQuery.setParameter("user", userName);
        Followings followings = new Followings();
        for (User u : userQuery.list())
        {
            followings.add(u.toMiniUser());
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

    public void follow (String userName, String followedUserName) throws DataNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
//        databaseManager.findUser(followRelation.getFollowedUser(), session);
//        databaseManager.findUser(followRelation.getUsername(), session);
        FollowRelation followRelation = new FollowRelation(databaseManager.findUser(userName, session), databaseManager.findUser(followedUserName, session));
        if(databaseManager.isFollowRelationExist(followRelation, session) == null)
        {
            session.beginTransaction();
            session.persist(followedUserName);
            session.getTransaction().commit();
            session.close();
        }
        else
        {
            // throw exception
        }
    }
    public void unfollow (String userName, String followedUserName) throws DataNotFoundException
    {

        Session session = databaseManager.sessionFactory.openSession();
        FollowRelation followRelation = new FollowRelation(databaseManager.findUser(userName, session), databaseManager.findUser(followedUserName, session));
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
        // temporary send tweet this throw exception
        Session session = databaseManager.sessionFactory.openSession();
        session.beginTransaction();
        for (Hashtag hashtag : tweet.getHashtags())
        {
            session.persist(hashtag);
        }
        session.persist(tweet.getHashtags());
        session.persist(tweet);
        session.getTransaction().commit();
        session.close();
    }

    public void sendRetweet(Retweet retweet)
    {
        // TODO
        Session session = databaseManager.sessionFactory.openSession();
        session.beginTransaction();
        session.persist(retweet);
        session.getTransaction().commit();
        session.close();
    }

    public void sendQuote(Quote quote)
    {
        // TODO
        Session session = databaseManager.sessionFactory.openSession();
        session.beginTransaction();
        session.persist(quote);
        session.getTransaction().commit();
        session.close();
    }

    public void likeTweet(Tweet tweet, String userName) throws DataNotFoundException
    {
        // TODO
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        LikeRelation likeRelation = new LikeRelation(user, tweet);
        if(databaseManager.isLikeRelationExist(likeRelation, session) == null)
        {
            session.beginTransaction();
            session.persist(likeRelation);
            session.getTransaction().commit();
            session.close();
        }
        else
        {
          // throw exception
        }
    }

    public void dislikeTweet(Tweet tweet, String userName) throws DataNotFoundException
    {
        // TODO
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        LikeRelation likeRelation = new LikeRelation(user, tweet);
        if(databaseManager.isLikeRelationExist(likeRelation, session) != null)
        {
            session.beginTransaction();
            session.delete(likeRelation);
            session.getTransaction().commit();
            session.close();
        }
        else
        {
          // throw exception
        }
    }

    public TimeLine showTimeLine(String userName)
    {
        // TODO
        return null;
    }

    public void block(String blocker, String blocked) throws DataNotFoundException
    {
        // TODO : check the followRelations
        Session session = databaseManager.sessionFactory.openSession();
        BlockRelation blockRelation = new BlockRelation(databaseManager.findUser(blocker, session), databaseManager.findUser(blocked, session));
        FollowRelation followerRelation = new FollowRelation(blockRelation.getBlocked(), blockRelation.getBlocker());
        FollowRelation followingRelation = new FollowRelation(blockRelation.getBlocker(), blockRelation.getBlocked());
        if((followerRelation = databaseManager.isFollowRelationExist(followerRelation, session)) != null)
        {
            session.beginTransaction();
            session.delete(followerRelation);
            session.getTransaction().commit();
        }
        if((followingRelation = databaseManager.isFollowRelationExist(followingRelation, session)) != null)
        {
            session.beginTransaction();
            session.delete(followingRelation);
            session.getTransaction().commit();
        }
        if(databaseManager.isBlockRelationExist(blockRelation, session) == null)
        {
            session.beginTransaction();
            session.persist(blockRelation);
            session.getTransaction().commit();
            session.close();
        }
        else
        {
          // throw exception
        }
    }

    public void unblock(String blocker, String blocked) throws DataNotFoundException
    {
        // TODO
        Session session = databaseManager.sessionFactory.openSession();
        BlockRelation blockRelation = new BlockRelation(databaseManager.findUser(blocker, session), databaseManager.findUser(blocked, session));
        if((blockRelation = databaseManager.isBlockRelationExist(blockRelation, session)) != null)
        {
            session.beginTransaction();
            session.delete(blockRelation);
            session.getTransaction().commit();
            session.close();
        }
        else
        {
         // throw exception
        }
    }

    public BlackList showBlackList(String userName)
    {
        Session session = databaseManager.sessionFactory.openSession();
        Query<User> userQuery = session.createQuery("select b.blocked from BlockRelation b where b.blocker.userName = :user", User.class);
        userQuery.setParameter("user", userName);
        BlackList blackList = new BlackList();
        for (User u : userQuery.list())
        {
            blackList.add(u.toMiniUser());
        }
        return blackList;
    }
}
