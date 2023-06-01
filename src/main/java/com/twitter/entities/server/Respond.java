package com.twitter.entities.server;

import com.twitter.client.view.TwitterLog;
import com.twitter.entities.exception.TwitException;
import com.twitter.entities.exception.io.server.DatabaseFailedException;
import com.twitter.entities.exception.io.server.ServerInvalidObjectException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Respond implements Serializable
{
    private final RespondCode respondCode;
    private final Object object;
    private final TwitException e;

    public Respond(RespondCode respondCode)
    {
        this.respondCode = respondCode;
        object = null;
        e = null;
    }

    public Respond(RespondCode respondCode, String message)
    {
        this.respondCode = respondCode;
        object = null;
        e = null;
    }

    public Respond(RespondCode respondCode, Object object)
    {
        this.respondCode = respondCode;
        this.object = object;
        e = null;
    }

    public Respond(RespondCode respondCode, Object object, TwitException e)
    {
        this.respondCode = respondCode;
        this.object = object;
        this.e = e;
    }

    public Respond(TwitException e)
    {
        this.respondCode = RespondCode.FAIL;
        object = null;
        this.e = e;
    }

    public RespondCode getRespondCode()
    {
        return respondCode;
    }

    public Object getObject()
    {
        return object;
    }

    public void send(ObjectOutputStream objectOutputStream) throws ServerInvalidObjectException
    {
        try
        {
            objectOutputStream.writeObject(this);
        } catch (IOException e)
        {
            e.printStackTrace();
            throw new ServerInvalidObjectException("Invalid object sent to client!");
        }
    }

    public void check() throws DatabaseFailedException
    {
        if(respondCode == RespondCode.FAIL)
            throw new DatabaseFailedException();
    }
}
