package com.lonewolf.lingine.Entities;

import com.lonewolf.lingine.Engine.CoreEngine.GameObject;

public class Entity extends GameObject
{
	protected float health;
	
	public Entity()
	{
		this.health = getMaxHealth();
	}
	
	public float getMaxHealth()
	{
		return 1;
	}
	
	public void damage(float damage)
	{
		health -= Math.abs(damage);
		
		if (health < 0)
		{
			game.removeObject(this);
		}
	}
	
	public void heal(float amount)
	{
		health += Math.abs(amount);
		health = Math.min(health, getMaxHealth());
	}
	
	public float getHealth()
	{
		return health;
	}
}
