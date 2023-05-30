package com.twitter.client.view;

import com.twitter.client.controller.ControllerCommands;
import com.twitter.client.controller.Data;
import com.twitter.entities.exception.EmailOrPhoneRequiredException;
import com.twitter.entities.exception.TwitException;
import com.twitter.entities.exception.io.server.DatabaseFailedException;
import com.twitter.entities.exception.io.server.ServerConnectionFailedException;
import com.twitter.entities.exception.io.server.ServerInvalidObjectException;
import com.twitter.entities.exception.io.server.ServerRespondFailedException;
import com.twitter.entities.exception.user.CountryException;
import com.twitter.entities.exception.user.email.EmailFormatException;
import com.twitter.entities.exception.user.password.PasswordConfirmException;
import com.twitter.entities.exception.user.password.PasswordFormatException;
import com.twitter.entities.exception.user.password.PasswordHashException;
import com.twitter.entities.user.Country;
import com.twitter.entities.user.User;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Command
{
    private final ControllerCommands controllerCommands;

    public Command()
    {
        controllerCommands = new ControllerCommands();
    }

    public void signUp()
    {
        String userName, name, family, email, phoneNumber, password, passwordConfirm, country;
        int year, month, day;

        userName = TwitterLog.nextLine("Username: ");
        name = TwitterLog.nextLine("Name: ");
        family = TwitterLog.nextLine("Family Name: ");
        email = TwitterLog.nextLine("Email: ");
        phoneNumber = TwitterLog.nextLine("Phone Number: ");
        password = TwitterLog.nextLine("Password: ");
        passwordConfirm = TwitterLog.nextLine("Confirm you password: ");
        TwitterLog.println("Country List:");
        TwitterLog.println(Country.getInstance().toString());
        country = TwitterLog.nextLine("Country: ");

        String date = TwitterLog.nextLine("Birth Date: (yyyy-mm-dd) ");
        LocalDate localDate;
        try
        {
            localDate = LocalDate.parse(date);
        }
        catch (DateTimeParseException e)
        {
            TwitterLog.println("Wrong Date format!");
            return;
        }

        year = localDate.getYear();
        month = localDate.getMonthValue();
        day = localDate.getDayOfMonth();

        try
        {
            controllerCommands.signUp(userName, name, family, email, phoneNumber, password, passwordConfirm, country, year, month, day);
        } catch (PasswordConfirmException e)
        {
            // TODO: handle the exceptions
        } catch (ServerConnectionFailedException e)
        {
            throw new RuntimeException(e);
        } catch (CountryException e)
        {
            throw new RuntimeException(e);
        } catch (EmailFormatException e)
        {
            throw new RuntimeException(e);
        } catch (PasswordFormatException e)
        {
            throw new RuntimeException(e);
        } catch (EmailOrPhoneRequiredException e)
        {
            throw new RuntimeException(e);
        } catch (ServerRespondFailedException e)
        {
            throw new RuntimeException(e);
        } catch (PasswordHashException e)
        {
            throw new RuntimeException(e);
        } catch (DatabaseFailedException e)
        {
            throw new RuntimeException(e);
        } catch (ServerInvalidObjectException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void signIn()
    {

    }
}
