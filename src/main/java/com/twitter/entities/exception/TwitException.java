package com.twitter.entities.exception;

import java.io.Serializable;

public class TwitException extends Exception implements Serializable
{
    public TwitException()
    {
        super();
    }

    public TwitException(String message)
    {
        super(message);
    }
}
