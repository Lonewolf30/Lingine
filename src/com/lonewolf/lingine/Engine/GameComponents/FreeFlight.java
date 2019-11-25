package com.lonewolf.lingine.Engine.GameComponents;

import com.lonewolf.lingine.Engine.CoreEngine.Input;
import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;

public class FreeFlight extends GameComponent
{
	private float m_speed;
	private int   m_upKey;
	private int   m_downKey;
	private Vector3f moveAmount;
	
	public FreeFlight(float speed)
	{
		this(speed, Input.KEY_SPACE, Input.KEY_LSHIFT);
	}
	
	public FreeFlight(float speed, int upKey, int downKey)
	{
		this.m_speed = speed;
		moveAmount = new Vector3f(0,0,0);
		this.m_upKey = upKey;
		this.m_downKey = downKey;
	}
	
	@Override
	public void Input(Input input)
	{
		float movAmt = m_speed * 1;
		
		if(input.GetKey(m_upKey))
			Move(GetTransform().GetRot().GetUp(), movAmt);
		if(input.GetKey(m_downKey))
			Move(GetTransform().GetRot().GetUp(), -movAmt);
	}
	
	@Override
	public void Update(float delta)
	{
		GetTransform().SetPos(GetTransform().GetPos().Add(moveAmount.Mul(delta)));
		moveAmount = moveAmount.Sub(moveAmount.Mul(delta*3));
		if (moveAmount.Length() < 2)
			moveAmount = new Vector3f(0,0,0);
	}
	
	private void Move(Vector3f dir, float amt)
	{
		moveAmount = dir.Mul(amt);
		moveAmount.SetX(0);
		moveAmount.SetZ(0);
	}
}
