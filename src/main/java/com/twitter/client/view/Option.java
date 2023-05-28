package com.twitter.client.view;

public enum Option
{
    LOGIN,
    SIGN_UP,
    SHOW_USER_INFO
    ;


    @Override
    public String toString()
    {
        return switch (this)
        {
            case LOGIN -> "Login";
            case SIGN_UP -> "Sign-UP";
            case SHOW_USER_INFO -> "Show my user information";
        };
    }
}
