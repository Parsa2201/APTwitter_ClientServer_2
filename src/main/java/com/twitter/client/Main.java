package com.twitter.client;

import com.twitter.entities.exception.email.EmailFormatException;
import com.twitter.entities.exception.password.PasswordFormatException;
import com.twitter.entities.exception.password.PasswordHashException;
import com.twitter.entities.user.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Main
{
    public static void main(String[] args)
    {
        try(Socket client = new Socket("localhost", 5679))
        {
            handleClient(client);
        }
        catch (IOException | EmailFormatException | PasswordFormatException | PasswordHashException e)
        {
            System.out.println("Error in connecting to server!");
        }
    }

    public static void handleClient(Socket socket) throws IOException, EmailFormatException, PasswordFormatException, PasswordHashException
    {
        OutputStream outputStream = socket.getOutputStream();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        User user = new User();
        user.setName("Parsa");
        user.setFamily("Salamatipour");
        user.setUserName("sa.parsa20");
        user.setEmail("sa.parsa20@gmail.com");
        user.setPassword("NaNoOOl;#329");

        objectOutputStream.writeObject(user);
    }
}