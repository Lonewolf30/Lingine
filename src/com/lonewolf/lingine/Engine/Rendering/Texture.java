package com.lonewolf.lingine.Engine.Rendering;


import com.lonewolf.lingine.Engine.CoreEngine.Util;
import com.lonewolf.lingine.Engine.Rendering.resourceManagement.TextureResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL13.*;

public class Texture
{
    private TextureResource m_resource;

    public Texture(String fileName)
    {
        m_resource = LoadTexture(fileName);
    }

    public Texture(BufferedImage Image)
    {
        m_resource = LoadTexture(Image);
    }

    public void Bind()
    {
        Bind(0);
    }

    public void Bind(int samplerSlot)
    {
        assert (samplerSlot >= 0 && samplerSlot <= 31);
        glActiveTexture(GL_TEXTURE0 + samplerSlot);
        glBindTexture(GL_TEXTURE_2D, m_resource.GetId());
    }

    public int GetID()
    {
        return m_resource.GetId();
    }

    private static TextureResource LoadTexture(BufferedImage image)
    {
        try
        {
            return getTextureResource(image);
        } catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    private static TextureResource getTextureResource(BufferedImage image)
    {
        int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());

        ByteBuffer buffer = Util.CreateByteBuffer(image.getHeight() * image.getWidth() * 4);
        boolean hasAlpha = image.getColorModel().hasAlpha();

        for (int y = 0; y < image.getHeight(); y++)
        {
            for (int x = 0; x < image.getWidth(); x++)
            {
                int pixel = pixels[y * image.getWidth() + x];

                buffer.put((byte) ((pixel >> 16) & 0xFF));
                buffer.put((byte) ((pixel >> 8) & 0xFF));
                buffer.put((byte) ((pixel) & 0xFF));
                if (hasAlpha)
                    buffer.put((byte) ((pixel >> 24) & 0xFF));
                else
                    buffer.put((byte) (0xFF));
            }
        }

        buffer.flip();

        TextureResource resource = new TextureResource();
        glBindTexture(GL_TEXTURE_2D, resource.GetId());

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        return resource;
    }

    private static TextureResource LoadTexture(String fileName)
    {
        try
        {
            BufferedImage image = ImageIO.read(new File("./res/texture/" + fileName));
            return getTextureResource(image);
        } catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }
}
