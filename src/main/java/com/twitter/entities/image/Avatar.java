package com.twitter.entities.image;

import com.twitter.entities.exception.io.FileNotExistException;
import com.twitter.entities.exception.io.FileNotImageException;
import com.twitter.entities.exception.io.FileSizeException;
import com.twitter.entities.exception.io.ImageSizeException;

import java.awt.image.BufferedImage;

public class Avatar extends TwitterImage
{
    public Avatar()
    {
        super();
    }

    public Avatar(BufferedImage avatar)
    {
        super(avatar);
    }

    public Avatar(String path) throws FileSizeException, FileNotExistException, FileNotImageException, ImageSizeException
    {
        super(path);
    }

    @Override
    public int getWidth()
    {
        return 400;
    }

    @Override
    public int getHeight()
    {
        return 400;
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
        return 1024 * 1024;
    }
}
