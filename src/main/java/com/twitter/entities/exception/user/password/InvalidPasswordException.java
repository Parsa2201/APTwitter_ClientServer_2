package com.twitter.entities.exception.user.password;

public class InvalidPasswordException extends PasswordException
{
    public InvalidPasswordException(){super();}

    public InvalidPasswordException(String message){super(message);}
}
