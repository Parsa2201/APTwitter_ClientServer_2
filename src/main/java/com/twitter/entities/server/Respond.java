package com.twitter.entities.server;

import com.twitter.entities.exception.TwitException;
import com.twitter.entities.exception.UnknownException;
import com.twitter.entities.exception.io.server.DataNotFoundException;
import com.twitter.entities.exception.io.server.DatabaseFailedException;
import com.twitter.entities.exception.io.server.ServerInvalidCommandException;
import com.twitter.entities.exception.io.server.ServerInvalidObjectException;
import com.twitter.entities.exception.text.TextTooLongException;
import com.twitter.entities.exception.user.password.InvalidPasswordException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Respond implements Serializable
{
    private final RespondCode respondCode;
    private final Object object;
    private final TwitException exception;

    public Respond(RespondCode respondCode)
    {
        this.respondCode = respondCode;
        object = null;
        exception = null;
    }

    public Respond(RespondCode respondCode, String message)
    {
        this.respondCode = respondCode;
        object = null;
        exception = null;
    }

    public Respond(RespondCode respondCode, Object object)
    {
        this.respondCode = respondCode;
        this.object = object;
        exception = null;
    }

    public Respond(RespondCode respondCode, Object object, TwitException e)
    {
        this.respondCode = respondCode;
        this.object = object;
        this.exception = e;
    }

    public Respond(TwitException e)
    {
        this.respondCode = RespondCode.FAIL;
        object = null;
        this.exception = e;
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

    public void check() throws UnknownException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException
    {
        if(respondCode == RespondCode.FAIL)
        {
            if(exception == null)
                throw new UnknownException();

            // throw the exact type of the exception
            if(exception instanceof DatabaseFailedException)
                throw (DatabaseFailedException) exception;
            else if(exception instanceof ServerInvalidObjectException)
                throw (ServerInvalidObjectException) exception;
            else if (exception instanceof DataNotFoundException)
                throw (DataNotFoundException) exception;
            else if(exception instanceof InvalidPasswordException)
                throw (InvalidPasswordException) exception;
            else if(exception instanceof TextTooLongException)
                throw (TextTooLongException) exception;
            else if(exception instanceof ServerInvalidCommandException)
                throw (ServerInvalidCommandException) exception;
            else if (exception instanceof UnknownException)
                throw (UnknownException) exception;
            else
                throw new UnknownException();
        }
    }
}
