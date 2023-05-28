package com.twitter.entities.image;

import com.twitter.entities.exception.io.FileNotExistException;
import com.twitter.entities.exception.io.FileNotImageException;
import com.twitter.entities.exception.io.FileSizeException;
import com.twitter.entities.exception.io.ImageSizeException;

import java.awt.image.BufferedImage;

public class Header extends TwitterImage
{
    public Header()
    {
        super();
    }

    public Header(BufferedImage header)
    {
        super(header);
    }

    public Header(String path) throws FileSizeException, FileNotExistException, FileNotImageException, ImageSizeException
    {
        super(path);
    }

    @Override
    public int getWidth()
    {
        return 1500;
    }

    @Override
    public int getHeight()
    {
        return 1500;
    }

    @Override
    public int getMaxWidth()
    {
        return 0;
    }

    @Override
    public int getMaxHeight()
    {
        return 0;
    }

    @Override
    public int getMaxByteSize()
    {
        return 2 * 1024 * 1024;
    }
}
