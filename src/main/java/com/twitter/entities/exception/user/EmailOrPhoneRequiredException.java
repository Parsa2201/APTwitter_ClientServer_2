package com.twitter.entities.exception.user;

import com.twitter.entities.exception.TwitException;

public class EmailOrPhoneRequiredException extends TwitException
{
    public EmailOrPhoneRequiredException()
    {
        super();
    }

    public EmailOrPhoneRequiredException(String message)
    {
        super(message);
    }
}
