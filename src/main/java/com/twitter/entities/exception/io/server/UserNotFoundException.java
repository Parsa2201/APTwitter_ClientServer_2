package com.twitter.entities.exception.io.server;

public class UserNotFoundException extends DataNotFoundException
{
    public UserNotFoundException(){super();}

    public UserNotFoundException(String message){super(message);}
}
