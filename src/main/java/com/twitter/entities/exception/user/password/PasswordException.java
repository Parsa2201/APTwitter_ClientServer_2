package com.twitter.entities.exception.user.password;

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
