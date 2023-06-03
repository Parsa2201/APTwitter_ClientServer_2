package com.twitter.entities.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ConnectedUsers implements Serializable, Iterable<MiniUser>
{
    ArrayList<MiniUser> miniUsers;

    public ConnectedUsers()
    {
        miniUsers = new ArrayList<>();
    }

    public void add(MiniUser miniUser)
    {
        miniUsers.add(miniUser);
    }

    public void remove(MiniUser miniUser)
    {
        miniUsers.remove(miniUser);
    }

    public MiniUser get(int index)
    {
        return miniUsers.get(index);
    }

    public int size()
    {
        return miniUsers.size();
    }

    @Override
    public Iterator<MiniUser> iterator()
    {
        return miniUsers.iterator();
    }
}
