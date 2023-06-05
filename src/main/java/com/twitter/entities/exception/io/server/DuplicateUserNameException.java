package com.twitter.entities.exception.io.server;

public class DuplicateUserNameException extends DatabaseException
{
    public DuplicateUserNameException(){super();}

    public DuplicateUserNameException(String message){super(message);}
}
