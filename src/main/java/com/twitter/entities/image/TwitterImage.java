package com.twitter.entities.image;

import com.twitter.entities.exception.io.FileNotExistException;
import com.twitter.entities.exception.io.FileNotImageException;
import com.twitter.entities.exception.io.FileSizeException;
import com.twitter.entities.exception.io.ImageSizeException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

public abstract class TwitterImage implements Serializable
{
    private BufferedImage image;

    public TwitterImage()
    {
        this.image = null;
    }

    public TwitterImage(BufferedImage image)
    {
        this.image = image;
    }

    // return 0 if you want to use WIDTH and HEIGHT instead of MAX_WIDTH and MAX_HEIGHT
    public abstract int getWidth();

    public abstract int getHeight();


    public abstract int getMaxWidth();

    public abstract int getMaxHeight();

    public abstract int getMaxByteSize();

    public TwitterImage(String path) throws FileSizeException, FileNotExistException, FileNotImageException, ImageSizeException
    {
        File file = new File(path);
        if (!file.exists())
            throw new FileNotExistException("File does not exist");

        if (file.length() > getMaxByteSize())
            throw new FileSizeException("File size is too large (max size : " + getMaxByteSize() / 1024 / 1024 + "MB)");

        try
        {
            image = ImageIO.read(file);
        } catch (IOException e)
        {
            throw new FileNotImageException("File is not an image");
        }

        if (image == null)
            throw new FileNotImageException("File is not an image");

        if (getMaxWidth() == 0 && getMaxHeight() == 0)
        {
            if (image.getWidth() != getWidth() || image.getHeight() != getHeight())
                throw new ImageSizeException("Image size is not " + getWidth() + "X" + getHeight());
        } else
        {
            if (image.getWidth() > getMaxWidth() || image.getHeight() > getMaxHeight())
                throw new ImageSizeException("Image size should be less than or equal " + getMaxWidth() + "X" + getMaxHeight());
        }
    }

    public BufferedImage getImage()
    {
        return image;
    }

    @Serial
    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException
    {
        out.defaultWriteObject();
        ImageIO.write(image, "jpg", out);
    }
    @Serial
    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        image = ImageIO.read(in);
    }
}
