package com.twitter.client.controller;

import com.twitter.entities.exception.user.password.PasswordFormatException;
import com.twitter.entities.exception.user.password.PasswordHashException;
import com.twitter.entities.user.User;
import org.junit.jupiter.api.Test;

public class ControllerCommandsTest
{
    @Test
    void signUp1()
    {
        String userName = "1";
        String name = "hello";
        String family = "world";
        String email = "hello@world.com";
        String phoneNumber = "1234567890";
        String password = "NaNoOOl;#329";
        String passwordConfirm = "NaNoOOl;#329";
        String country = "Iran";
        int year = 2000;
        int month = 1;
        int day = 1;

        ControllerCommands controllerCommands = new ControllerCommands();
        try
        {
            controllerCommands.signUp(userName, name, family, email, phoneNumber, password, passwordConfirm, country, year, month, day);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            assert (false);
        }
    }

    @Test
    void signIn1()
    {
        String userName = "1";
        String password = "NaNoOOl;#329";

        ControllerCommands controllerCommands = new ControllerCommands();
        try
        {
            User user = controllerCommands.signIn(userName, password);
            System.out.println(user);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            assert (false);
        }
    }

    @Test
    void setAvatar()
    {
        // FIXME
        User user = new User();
        user.setUserName("1");
        String path = "src/test/assets/Parsa Salamatipour 400X400.jpg";

        ControllerCommands controllerCommands = new ControllerCommands();
        try
        {
            controllerCommands.setAvatar(user, path);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            assert (false);
        }
    }

    @Test
    void setHeader1()
    {
        // FIXME
        User user = new User();
        user.setUserName("1");
        String path = "src/test/assets/Parsa Salamatipour 400X400.jpg";

        ControllerCommands controllerCommands = new ControllerCommands();
        try
        {
            controllerCommands.setHeader(user, path);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            assert (false);
        }
    }

    @Test
    void changeUserInformation1()
    {
        User user = new User();
        user.setUserName("1");
        try
        {
            user.setPassword("NaNoOOl;#329");
        } catch (PasswordFormatException | PasswordHashException ignored) {}


        String bioText, location, website;
        bioText = "Hello World!" +
                "I'm hello world";
        location = "Iran";
        website = "https://www.google.com";

        ControllerCommands controllerCommands = new ControllerCommands();
        try
        {
            controllerCommands.changeUserInformation(user, bioText, location, website);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            assert (false);
        }
    }
}
