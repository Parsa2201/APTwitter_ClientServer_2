package com.twitter.entities.exception.io.server;

public class DuplicateBlockRequestException extends DatabaseException
{
    public DuplicateBlockRequestException (){super();}
    public DuplicateBlockRequestException (String message){super(message);}
}
