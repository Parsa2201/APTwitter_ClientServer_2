package com.twitter.entities.exception.io.server;

import com.twitter.entities.exception.io.TwitIOException;

public class ServerException extends TwitIOException
{
    public ServerException()
    {
        super();
    }

    public ServerException(String message)
    {
        super(message);
    }
}
