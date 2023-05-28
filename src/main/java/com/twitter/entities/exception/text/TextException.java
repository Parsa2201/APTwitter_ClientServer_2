package com.twitter.entities.exception.text;

import com.twitter.entities.exception.TwitException;

public class TextException extends TwitException
{
    public TextException()
    {
        super();
    }

    public TextException(String message)
    {
        super(message);
    }
}
