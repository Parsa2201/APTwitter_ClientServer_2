package com.twitter.entities.exception.io;

public class FileSizeException extends FileErrorException
{
    public FileSizeException()
    {
        super();
    }

    public FileSizeException(String message)
    {
        super(message);
    }
}
