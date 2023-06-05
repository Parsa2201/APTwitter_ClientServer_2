package com.twitter.entities.exception.io.server;

public class DuplicateLikeRequestException extends DatabaseException
{
    public DuplicateLikeRequestException(){}
    public DuplicateLikeRequestException(String message){super(message);}
}
