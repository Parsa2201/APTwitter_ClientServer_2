package com.twitter.client.view;

import com.twitter.client.controller.Data;
import com.twitter.entities.exception.io.server.DatabaseFailedException;
import com.twitter.entities.exception.io.server.ServerConnectionFailedException;
import com.twitter.entities.exception.io.server.ServerInvalidObjectException;
import com.twitter.entities.exception.io.server.ServerRespondFailedException;
import com.twitter.entities.exception.user.password.PasswordHashException;

import java.util.ArrayList;

public class MenuOption
{
    private final ArrayList<Option> options;
    private Option command;

    public MenuOption()
    {
        options = new ArrayList<>();
    }

    public void showOptions()
    {
        switch (Data.getInstance().getProgramState())
        {
            case LOGGED_OUT -> setLoggedOutOptions();
            case MAIN_MENU -> setMainMenuOptions();
        }

        for(int i = 0; i < options.size(); i++)
        {
            TwitterLog.print((i + 1) + " : ");
            TwitterLog.println(options.get(i).toString());
        }
    }

    public void getCommand() throws IllegalArgumentException
    {
        String rawCommand = TwitterLog.nextLine();

        int optionIndex;
        try
        {
            optionIndex = Integer.parseInt(rawCommand);
        }
        catch (NumberFormatException e)
        {
            TwitterLog.println("The given option should be a number!");
            throw new IllegalArgumentException();
        }

        if(optionIndex > options.size())
        {
            TwitterLog.println("sorry, we don't have such an option!");
            throw new IllegalArgumentException();
        }

        command = options.get(optionIndex - 1);
    }

    public void doCommand()
    {
        // TODO: check for different commands and call the proper function from Command.java
        Command commandObject = new Command();

        try
        {
            switch (command)
            {
                case SIGN_UP -> commandObject.signUp();
                case SIGN_IN -> commandObject.signIn();
                case SHOW_USER_INFO -> commandObject.showUserInfo();
            }
        }
        catch (ServerConnectionFailedException e)
        {
            TwitterLog.printlnError("Server connection failed!");
        } catch (ServerRespondFailedException e)
        {
            TwitterLog.printlnError("Server respond failed!");
        } catch (DatabaseFailedException e)
        {
            TwitterLog.printlnError("Database failed!");
        } catch (ServerInvalidObjectException e)
        {
            TwitterLog.printlnError("Server got/sent invalid object!");
        } catch (PasswordHashException e)
        {
            TwitterLog.printlnError("Password hash failed!");
        }
    }

    private void setLoggedOutOptions()
    {
        options.clear();
        options.add(Option.SIGN_UP);
        options.add(Option.SIGN_IN);
    }

    private void setMainMenuOptions()
    {
        options.clear();
        options.add(Option.SHOW_USER_INFO);
    }
}
