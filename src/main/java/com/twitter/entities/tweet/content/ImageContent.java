package com.twitter.entities.tweet.content;

import com.twitter.entities.exception.io.FileNotExistException;
import com.twitter.entities.exception.io.FileNotImageException;
import com.twitter.entities.exception.io.FileSizeException;
import com.twitter.entities.exception.io.ImageSizeException;
import com.twitter.entities.image.ImageContentData;

import java.awt.image.BufferedImage;

public class ImageContent extends Content
{
    private final ImageContentData image;

    public ImageContent(String path) throws ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException
    {
        this.image = new ImageContentData(path);
    }

    public BufferedImage getImage()
    {
        return image.getImage();
    }
}
