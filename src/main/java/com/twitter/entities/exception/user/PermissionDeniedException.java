package com.twitter.entities.exception.user;

import com.twitter.entities.exception.TwitException;

public class PermissionDeniedException extends TwitException
{
    public PermissionDeniedException()
    {
        super();
    }

    public PermissionDeniedException(String message)
    {
        super(message);
    }
}
