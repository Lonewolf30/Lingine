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
		if (resizeText)
		{
			if (size.GetX() < 1)
				size.SetX(1);
			if (size.GetY() < 1)
				size.SetY(1);
			BufferedImage img = new BufferedImage((int) size.GetX(), (int) size.GetY(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = img.createGraphics();
			g2d.setPaint(color.getColor());
			g2d.setFont(new Font("Source Sans Pro", Font.PLAIN, (int) (img.getHeight() * 0.5f)));
			FontMetrics fm = g2d.getFontMetrics();
			int x = (img.getWidth() - fm.stringWidth(text)) / 2;
			int y = fm.getHeight() + fm.getHeight() / 100000;
			g2d.drawString(text, x, y / 0.9f);
			g2d.dispose();
			
			BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = newImage.createGraphics();
			g.drawImage(img, 0, 0, null);
			g.dispose();
			return newImage;
		} else
		{
			if (size.GetX() < 1)
				size.SetX(1);
			if (size.GetY() < 1)
				size.SetY(1);
			BufferedImage img = new BufferedImage((int) size.GetX(), (int) size.GetY(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = img.createGraphics();
			g2d.setPaint(color.getColor());
			g2d.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
			FontMetrics fm = g2d.getFontMetrics();
			float inc = 1;
			for (String line : text.split("\n"))
			{
				g2d.drawString(line, 0, fm.getHeight() * inc);
				inc++;
			}
			g2d.dispose();
			
			BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = newImage.createGraphics();
			g.drawImage(img, 0, 0, null);
			g.dispose();
			return newImage;
		}
	}
	
	protected void generateTexture(Vector2f windowSize)
	{
		texture = new Texture(getImage(windowSize));
	}
	
	public void setText(String text)
	{
		this.text = text;
		resetTexture();
	}
	
	@Override
	public void render(Shader shader, RenderingEngine renderEngine)
	{
		Vector2f location = parent.getTransformedPos();
		Vector2f scale = parent.getTransformedScale().Div(renderEngine.getWindow().getWindowSize());
		
		shader.SetUniform("scale", new Vector2f(scale.GetX(), -scale.GetY()));
		shader.SetUniform("offset", location.Sub(renderEngine.getWindow().getWindowSize().Div(2)).Div(renderEngine.getWindow().getWindowSize().Div(2)));
		shader.SetUniformf("r", cornerRadius);
		
		if (texture == null)
			generateTexture(parent.getTransformedScale());
		
		if (renderEngine.hasResized())
			generateTexture(parent.getTransformedScale());
		
		texture.Bind();
		
		shader.Bind();
		
		glBegin(GL_TRIANGLE_STRIP);
		glVertex2f(-1, -1);
		glVertex2f(-1, 1);
		glVertex2f(1, -1);
		glVertex2f(1, 1);
		glEnd();
	}
	
	public void resetTexture()
	{
		texture = null;
	}
}
