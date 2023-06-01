package com.twitter.entities.exception;

public class UnknownException extends TwitException
{
    public UnknownException()
    {
        super();
    }

    public UnknownException(String message)
    {
        super(message);
    }
}
