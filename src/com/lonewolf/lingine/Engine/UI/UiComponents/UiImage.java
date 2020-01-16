package com.lonewolf.lingine.Engine.UI.UiComponents;

import com.lonewolf.lingine.Engine.CoreEngine.Input;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Engine.Rendering.RenderingEngine;
import com.lonewolf.lingine.Engine.Rendering.Shader;
import com.lonewolf.lingine.Engine.Rendering.Texture;
import com.lonewolf.lingine.Engine.UI.UiColor;

import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.GL11.*;

public class UiImage extends UiComponent
{
	public String imageName;
	public float cornerRadius;
	
	protected Texture texture;
	
	public UiImage(String imageName, float cornerRadius)
	{
		this.imageName = imageName;
		this.cornerRadius = cornerRadius;
	}
	
	protected void generateTexture()
	{
		texture = new Texture(imageName);
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
		Vector2f scale = parent.getTransformedScale().Div(renderEngine.getWindow().getWindowSize());
		
		shader.SetUniform("scale", scale.Mul(new Vector2f(1,-1)));
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
