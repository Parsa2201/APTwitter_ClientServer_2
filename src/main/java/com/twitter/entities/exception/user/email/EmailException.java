package com.twitter.entities.exception.user.email;

import com.twitter.entities.exception.TwitException;

public class EmailException extends TwitException
{
    public EmailException()
    {
        super();
    }

    public EmailException(String message)
    {
        super(message);
    }
}
