package com.twitter.entities.exception.user;

import com.twitter.entities.exception.TwitException;

public class CountryException extends TwitException
{
    public CountryException()
    {
        super();
    }

    public CountryException(String message)
    {
        super(message);
    }
}
