package com.twitter.client.model;

import com.twitter.entities.exception.UnknownException;
import com.twitter.entities.exception.io.server.*;
import com.twitter.entities.exception.text.TextTooLongException;
import com.twitter.entities.exception.user.password.InvalidPasswordException;
import com.twitter.entities.image.Avatar;
import com.twitter.entities.image.Header;
import com.twitter.entities.server.Respond;
import com.twitter.entities.user.*;

import java.time.LocalDate;

public class ModelCommands
{
    public ModelCommands()
    {

    }

    public void signUp(User user) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("sign-up");
            serverConnectionHandler.sendObject(user);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();

            // test the respond code
            System.out.println(respond.getRespondCode());
        }
    }

    public User signIn(String userName, Password passwordHash) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("sign-in");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(passwordHash);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();

            return (User) respond.getObject();
        }
    }

    public void setAvatar(String userName, Avatar avatar) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("set-image");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(avatar);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public void setHeader(String userName, Header header) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("set-header");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(header);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

public void changePassword(String userName, Password newPasswordHash) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-password");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(newPasswordHash);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public void changeName(String userName, String name) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-name");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(name);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public void changeFamily(String userName, String family) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-family");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(family);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public void changeEmail(String userName, String email) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-email");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(email);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public void changePhoneNumber(String userName, String phoneNumber) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-phone-number");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(phoneNumber);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public void changeBirthDate(String userName, LocalDate birthDate) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-birth-date");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(birthDate);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public void changeCountry(String userName, String country) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-country");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(country);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public void changeBio(String userName, Bio bio) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-bio");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(bio);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public void changeLocation(String userName, String location) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-location");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(location);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public void changeWebsite(String userName, String website) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-website");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(website);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

//    public void changeUserInformation(String userName, Bio bio, String address, String website) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
//    {
//        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
//        {
//            serverConnectionHandler.sendCommend("change-user-information");
//            serverConnectionHandler.sendObject(userName);
//            serverConnectionHandler.sendObject(bio);
//            serverConnectionHandler.sendObject(address);
//            serverConnectionHandler.sendObject(website);
//            Respond respond = serverConnectionHandler.getRespond();
//            respond.check();
//        }
//    }

    public Followers showFollowers(String userName) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("show-followers");
            serverConnectionHandler.sendObject(userName);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
            return (Followers) respond.getObject();
        }
    }

    public Followings showFollowings(String userName) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("show-followings");
            serverConnectionHandler.sendObject(userName);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
            return (Followings) respond.getObject();
        }
    }

    public void follow(FollowRelation followRelation) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("follow");
            serverConnectionHandler.sendObject(followRelation);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public void unfollow(FollowRelation followRelation) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("unfollow");
            serverConnectionHandler.sendObject(followRelation);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public MiniUser showUser(String miniUserUsername) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("show-user");
            serverConnectionHandler.sendObject(miniUserUsername);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
            return (MiniUser) respond.getObject();
        }
    }
}
