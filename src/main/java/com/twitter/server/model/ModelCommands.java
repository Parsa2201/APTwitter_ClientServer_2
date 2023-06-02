package com.twitter.server.model;

import com.twitter.entities.exception.io.server.ServerInvalidObjectException;
import com.twitter.entities.exception.io.server.DataNotFoundException;
import com.twitter.entities.exception.user.password.InvalidPasswordException;
import com.twitter.entities.exception.text.TextTooLongException;
import com.twitter.entities.image.Avatar;
import com.twitter.entities.image.Header;
import com.twitter.entities.server.Respond;
import com.twitter.entities.server.RespondCode;
import com.twitter.entities.user.Bio;
import com.twitter.entities.user.FollowRelation;
import com.twitter.entities.user.Password;
import com.twitter.entities.user.User;

import java.io.ObjectInputStream;

public class ModelCommands
{
    private final DatabaseManager databaseManager;
    private final DatabaseCommands databaseCommands;

    public ModelCommands()
    {
        databaseManager = new DatabaseManager();
        databaseCommands = new DatabaseCommands();
//        databaseManager = null;
    }

    public Respond signUp(ObjectInputStream objectInputStream) throws ServerInvalidObjectException
    {
        User user = ObjectGetter.getObject(objectInputStream, User.class);

        // TODO: save to database, throw exception if failed
        databaseCommands.signUp(user);

        // TODO: add the newly created exceptions to both the client and the server project!!!

        // show the user as a test
        System.out.println(user);

        return new Respond(RespondCode.SUCCESS);
    }

    public Respond signIn(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, DataNotFoundException, InvalidPasswordException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        Password passwordHash = ObjectGetter.getObject(objectInputStream, Password.class);

        // TODO: get user from database, throw exception if not found or some other error
        User user = databaseCommands.signIn(userName, passwordHash);
        return new Respond(RespondCode.SUCCESS, user);
        // TODO: add the newly created exceptions to both the client and the server project!!!

        // temporary user (because we don't have a database yet)
//        User user = new User();
//        user.setUserName(userName);
//        user.setPassword(passwordHash);

//        return new Respond(RespondCode.SUCCESS, user);
    }

    public Respond setAvatar(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, DataNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        Avatar avatar = ObjectGetter.getObject(objectInputStream, Avatar.class);

        // TODO: save to database, throw exception if failed
        databaseCommands.setAvatar(userName, avatar);
        // TODO: add the newly created exceptions to both the client and the server project!!!

        // temporary showing the username (because we don't have a database yet)
        System.out.println(userName);

        return new Respond(RespondCode.SUCCESS);
    }

    public Respond setHeader(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, DataNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        Header header = ObjectGetter.getObject(objectInputStream, Header.class);

        // TODO: save to database, throw exception if failed
        databaseCommands.setHeader(userName, header);
        // TODO: add the newly created exceptions to both the client and the server project!!!

        // temporary showing the username (because we don't have a database yet)
        System.out.println(userName);

        return new Respond(RespondCode.SUCCESS);
    }

    public Respond changeUserInformation(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, DataNotFoundException, TextTooLongException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        Bio bio = ObjectGetter.getObject(objectInputStream, Bio.class);
        String location = ObjectGetter.getObject(objectInputStream, String.class);
        String website = ObjectGetter.getObject(objectInputStream, String.class);

        // TODO: change user information in database, throw exception if not found or some other error
        databaseCommands.changeUserInformation(userName, bio, location, website);
        // TODO: add the newly created exceptions to both the client and the server project!!!

        // temporary showing the user information (because we don't have a database yet)
        System.out.println(userName);
        System.out.println(bio);
        System.out.println(location);

        return new Respond(RespondCode.SUCCESS);
    }

    public Respond changePassword(ObjectInputStream objectInputStream)
    {
        // TODO
        return null;
    }

    public Respond changeName(ObjectInputStream objectInputStream)
    {
        // TODO
        return null;
    }

    public Respond changeFamily(ObjectInputStream objectInputStream)
    {
        // TODO
        return null;
    }

    public Respond changeEmail(ObjectInputStream objectInputStream)
    {
        // TODO
        return null;
    }

    public Respond changePhoneNumber(ObjectInputStream objectInputStream)
    {
        // TODO
        return null;
    }

    public Respond changeBirthDate(ObjectInputStream objectInputStream)
    {
        // TODO
        return null;
    }

    public Respond changeCountry(ObjectInputStream objectInputStream)
    {
        // TODO
        return null;
    }

    public Respond changeBio(ObjectInputStream objectInputStream)
    {
        // TODO
        return null;
    }

    public Respond changeLocation(ObjectInputStream objectInputStream)
    {
        // TODO
        return null;
    }

    public Respond changeWebsite(ObjectInputStream objectInputStream)
    {
        // TODO
        return null;
    }

    public Respond showFollowers(ObjectInputStream objectInputStream)
    {
        // TODO
        return null;
    }

    public Respond showFollowings(ObjectInputStream objectInputStream)
    {
        // TODO
        return null;
    }

    public Respond follow(ObjectInputStream objectInputStream) throws ServerInvalidObjectException
    {
        // TODO
        FollowRelation followRelation = ObjectGetter.getObject(objectInputStream, FollowRelation.class);

        return null;
    }

    public Respond unfollow(ObjectInputStream objectInputStream)
    {
        // TODO
        return null;
    }

    public Respond showUser(ObjectInputStream objectInputStream)
    {
        // TODO
        return null;
    }
}
