package com.twitter.client.controller;

import com.twitter.client.view.ProgramState;
import com.twitter.entities.tweet.TimeLine;
import com.twitter.entities.user.User;

public class Data
{
    private static Data data;

    private ProgramState programState;

    private User user;
    private TimeLine timeLine;

    private Data()
    {
        programState = ProgramState.LOGGED_OUT;
        user = null;
        timeLine = new TimeLine();
    }

    public static Data getInstance()
    {
        if(data == null)
            data = new Data();
        return data;
    }

    public ProgramState getProgramState()
    {
        return programState;
    }

    public void setProgramState(ProgramState programState)
    {
        this.programState = programState;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public TimeLine getTimeLine()
    {
        return timeLine;
    }

    public void setTimeLine(TimeLine timeLine)
    {
        this.timeLine = timeLine;
    }
}
