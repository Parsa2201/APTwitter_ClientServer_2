package com.twitter.client.model;

import com.twitter.entities.exception.io.server.DatabaseFailedException;
import com.twitter.entities.exception.io.server.ServerConnectionFailedException;
import com.twitter.entities.exception.io.server.ServerInvalidObjectException;
import com.twitter.entities.exception.io.server.ServerRespondFailedException;
import com.twitter.entities.image.Avatar;
import com.twitter.entities.image.Header;
import com.twitter.entities.server.Respond;
import com.twitter.entities.user.*;

public class ModelCommands
{
    public ModelCommands()
    {

    }

    public void signUp(User user) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException
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

    public User signIn(String userName, Password passwordHash) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException
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

    public void setAvatar(String userName, Avatar avatar) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException
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

    public void setHeader(String userName, Header header) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException
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

    public void changeUserInformation(String userName, Bio bio, String address, String website) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-user-information");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(bio);
            serverConnectionHandler.sendObject(address);
            serverConnectionHandler.sendObject(website);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public Followers showFollowers(String userName) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException
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

    public Followings showFollowings(String userName) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException
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

    public void follow(FollowRelation followRelation) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("follow");
            serverConnectionHandler.sendObject(followRelation);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public void unfollow(FollowRelation followRelation) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("unfollow");
            serverConnectionHandler.sendObject(followRelation);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public MiniUser showUser(String miniUserUsername) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException
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
