package com.lonewolf.lingine.Engine.UI.UiComponents;

import com.lonewolf.lingine.Engine.CoreEngine.Input;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Engine.Rendering.RenderingEngine;
import com.lonewolf.lingine.Engine.Rendering.Shader;
import com.lonewolf.lingine.Engine.Rendering.Texture;
import com.lonewolf.lingine.Engine.UI.UiColor;

import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.GL11.*;

public class UiBlock extends UiComponent
{
	public UiColor color;
	public float cornerRadius;
	
	protected Texture texture;
	
	public UiBlock(UiColor color, float cornerRadius)
	{
		this.color = color;
		this.cornerRadius = cornerRadius;
	}
	
	protected void generateTexture()
	{
		BufferedImage image = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
		image.setRGB(0,0,color.getColor().getRGB());
		texture = new Texture(image);
	}
	
	@Override
	public void input(Input input)
	{
		super.input(input);
	}
	
	@Override
	public void render(Shader shader, RenderingEngine renderEngine)
	{
		Vector2f location = parent.getTransformedPos();
		Vector2f scale = parent.getTransformedScale();
		
		shader.SetUniform("scale", scale);
		shader.SetUniform("offset", location.Sub(renderEngine.getWindow().getWindowSize().Div(2)).Div(renderEngine.getWindow().getWindowSize().Div(2)));
		shader.SetUniformf("r", cornerRadius);
		
		if (texture != null)
			texture.Bind();
		else
			generateTexture();
		
		shader.Bind();
		
		glBegin(GL_TRIANGLE_STRIP);
		
		glVertex2f(-1,-1);
		glVertex2f(-1,1);
		glVertex2f(1,-1);
		glVertex2f(1,1);
		
		glEnd();
	}
}
