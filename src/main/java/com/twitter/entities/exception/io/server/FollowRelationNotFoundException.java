package com.twitter.entities.exception.io.server;

public class FollowRelationNotFoundException extends DataNotFoundException
{
    public FollowRelationNotFoundException(){super();}

    public FollowRelationNotFoundException (String message){super(message);}
}
