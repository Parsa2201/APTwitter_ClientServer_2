package com.twitter.server.model;

import com.twitter.entities.exception.io.server.ServerInvalidObjectException;
import com.twitter.entities.exception.io.server.DataNotFoundException;
import com.twitter.entities.exception.user.password.InvalidPasswordException;
import com.twitter.entities.exception.text.TextTooLongException;
import com.twitter.entities.server.Respond;
import com.twitter.entities.server.RespondCode;

import java.io.ObjectInputStream;

public class ModelCommandHandler
{
    private String command;
    private final ModelCommands modelCommands;

    public ModelCommandHandler()
    {
        modelCommands = new ModelCommands();
    }

    public void setCommand(String command)
    {
        this.command = command;
    }

    public Respond runCommand(ObjectInputStream objectInputStream)
    {
        try
        {
            return switch (command)
            {
                case "sign-up" -> modelCommands.signUp(objectInputStream);
                case "sign-in" -> modelCommands.signIn(objectInputStream);
                case "set-avatar" -> modelCommands.setAvatar(objectInputStream);
                case "set-header" -> modelCommands.setHeader(objectInputStream);
                case "change-user-information" -> modelCommands.changeUserInformation(objectInputStream);

                // TODO: make an exception for a wrong commend in the server side in the default case
                default -> new Respond(RespondCode.UNKNOWN);
            };
        }
        catch (ServerInvalidObjectException | DataNotFoundException | InvalidPasswordException | TextTooLongException e)
        {
            return new Respond(e);
        }

    }
}
