package com.twitter.client.controller;

import com.twitter.client.model.ModelCommands;
import com.twitter.client.view.ProgramState;
import com.twitter.entities.exception.TwitException;
import com.twitter.entities.exception.UnknownException;
import com.twitter.entities.exception.io.server.*;
import com.twitter.entities.exception.text.TextTooLongException;
import com.twitter.entities.exception.user.password.InvalidPasswordException;
import com.twitter.entities.tweet.TimeLine;
import com.twitter.entities.user.User;

import java.io.Serializable;

public class Data implements Serializable
{
    private static Data data;

    private ProgramState programState;

    private User user;
    private TimeLine timeLine;
    private transient final SettingIO settingIO;

    private Data()
    {
        programState = ProgramState.LOGGED_OUT;
        user = null;
        timeLine = new TimeLine();
        settingIO = new SettingIO(this);
        Data loadedData = settingIO.load();
        if(loadedData != null)
        {
            programState = loadedData.getProgramState();
            user = loadedData.getUser();
            if(programState == ProgramState.MAIN_MENU)
            {
                verifyUser();
            }
            timeLine = loadedData.getTimeLine();
        }
        settingIO.startAutoSave();
    }

    private void verifyUser()
    {
        ModelCommands modelCommands = new ModelCommands();
        try
        {
            user = modelCommands.signIn(user.getUserName(), user.getPassHash());
        } catch (TwitException e)
        {
            programState = ProgramState.LOGGED_OUT;
            user = null;
        }
        save();
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
        save();
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
        save();
    }

    public TimeLine getTimeLine()
    {
        return timeLine;
    }

    public void setTimeLine(TimeLine timeLine)
    {
        this.timeLine = timeLine;
        save();
    }

    private boolean save()
    {
        return settingIO.save();
    }
}
