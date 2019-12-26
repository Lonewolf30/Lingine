package com.lonewolf.lingine.Engine.Rendering;


import com.lonewolf.lingine.Engine.CoreEngine.Util;
import com.lonewolf.lingine.Engine.Rendering.resourceManagement.TextureResource;
import com.lonewolf.lingine.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL13.*;

public class Texture
{
    private static HashMap<String, TextureResource> loadedTextures = new HashMap<>();
	private TextureResource m_resource;
	private BufferedImage image;
	private String TextureName;
	
	public Texture(String fileName)
	{
		TextureName = fileName;
	}
	
	public Texture(BufferedImage Image)
	{
		this.image = Image;
	}
	
	public void Bind()
	{
		Bind(0);
	}
	
	public void Bind(int samplerSlot)
	{
        if (m_resource == null)
        {
            m_resource = LoadTexture(image);
        }
		
		assert (samplerSlot >= 0 && samplerSlot <= 31);
		glActiveTexture(GL_TEXTURE0 + samplerSlot);
		glBindTexture(GL_TEXTURE_2D, m_resource.GetId());
	}
	
	public int GetID()
	{
		return m_resource.GetId();
	}
	
	private TextureResource LoadTexture(BufferedImage image)
	{
		try
		{
		    boolean isFile = false;
		    if (image == null)
            {
                image = ImageIO.read(new File("./res/texture/" + TextureName));
                isFile = true;
                if (loadedTextures.get("./res/texture/" + TextureName) != null)
                    return loadedTextures.get("./res/texture/" + TextureName);
                
            }
		    TextureResource resource = getTextureResource(image);
		    if (isFile)
		        loadedTextures.put("./res/texture/" + TextureName, resource);
			return resource;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
	private TextureResource getTextureResource(BufferedImage image)
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
                {
                    buffer.put((byte) ((pixel >> 24) & 0xFF));
                }
                else
                {
                    buffer.put((byte) (0xFF));
                }
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
}
