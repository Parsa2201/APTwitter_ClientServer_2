package com.twitter.entities.exception.user.password;

public class PasswordFormatException extends PasswordException
{
    public PasswordFormatException()
    {
        super();
    }

    public PasswordFormatException(String message)
    {
        super(message);
    }
}
