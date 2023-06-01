package com.twitter.entities.exception.io;

import com.twitter.entities.exception.TwitException;

public class TwitIOException extends TwitException
{
    public TwitIOException()
    {
        super();
    }

    public TwitIOException(String message)
    {
        super(message);
    }
}
