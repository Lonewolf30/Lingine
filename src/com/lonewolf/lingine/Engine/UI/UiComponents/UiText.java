package com.lonewolf.lingine.Engine.UI.UiComponents;

import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Engine.Rendering.RenderingEngine;
import com.lonewolf.lingine.Engine.Rendering.Shader;
import com.lonewolf.lingine.Engine.Rendering.Texture;
import com.lonewolf.lingine.Engine.UI.UiColor;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.GL11.*;

public class UiText extends UiBlock
{
	private String text;
	private Boolean resizeText;
	
	public UiText(UiColor color, String text, Boolean resizeText)
	{
		super(color, 0);
		this.text = text;
		this.resizeText = resizeText;
	}
	
	private BufferedImage getImage(Vector2f size)
	{
		BufferedImage img = new BufferedImage((int)size.GetX() , (int)size.GetY(), BufferedImage.TYPE_INT_ARGB);
		int w = 16 * text.length();
		int h = 16;
		Graphics2D g2d = img.createGraphics();
		g2d.setPaint(color.getColor());
		g2d.setFont(new Font("Arial", Font.PLAIN, (int)(img.getHeight() * 0.5f)));
		FontMetrics fm = g2d.getFontMetrics();
		int x = (img.getWidth() - fm.stringWidth(text))/2;
		int y = fm.getHeight() + fm.getHeight()/4;
		g2d.drawString(text, x, y/0.9f);
		g2d.dispose();
		
		BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		
		return newImage;
	}
	
	protected void generateTexture(Vector2f windowSize)
	{
		texture = new Texture(getImage(windowSize));
	}
	
	@Override
	public void render(Shader shader, RenderingEngine renderEngine)
	{
		Vector2f location = parent.getTransformedPos();
		Vector2f scale = parent.getTransformedScale();
		
		shader.SetUniform("scale", new Vector2f(scale.GetX(), -scale.GetY()));
		shader.SetUniform("offset", location.Sub(renderEngine.getWindow().getWindowSize().Div(2)).Div(renderEngine.getWindow().getWindowSize().Div(2)));
		shader.SetUniformf("r", cornerRadius);
		
		if (texture != null)
			texture.Bind();
		else
			generateTexture(scale.Mul(renderEngine.getWindow().getWindowSize()));
		
		shader.Bind();
		
		glBegin(GL_TRIANGLE_STRIP);
		glVertex2f(-1,-1);
		glVertex2f(-1,1);
		glVertex2f(1,-1);
		glVertex2f(1,1);
		glEnd();
	}
}
