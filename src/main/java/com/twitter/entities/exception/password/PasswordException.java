package com.twitter.entities.exception.password;

import com.twitter.entities.exception.TwitException;

public class PasswordException extends TwitException
{
    public PasswordException()
    {
        super();
    }

    public PasswordException(String message)
    {
        super(message);
    }
}
