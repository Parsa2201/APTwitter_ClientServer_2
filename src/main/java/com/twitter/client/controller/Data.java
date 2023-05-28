package com.twitter.client.controller;

import com.twitter.client.view.ProgramState;

public class Data
{
    private static Data data;

    private ProgramState programState;

    private Data()
    {
        programState = ProgramState.LOGGED_OUT;
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
}
