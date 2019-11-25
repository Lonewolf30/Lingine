package com.lonewolf.lingine.Engine.GameComponents;

import com.lonewolf.lingine.Engine.CoreEngine.Input;
import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;

public class FreeMove extends GameComponent
{
	private float m_speed;
	private int   m_forwardKey;
	private int   m_backKey;
	private int   m_leftKey;
	private int   m_rightKey;
	private Vector3f moveAmount;

	public FreeMove(float speed)
	{
		this(speed, Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D);
	}

	public FreeMove(float speed, int forwardKey, int backKey, int leftKey, int rightKey)
	{
		this.m_speed = speed;
		this.m_forwardKey = forwardKey;
		this.m_backKey = backKey;
		this.m_leftKey = leftKey;
		this.m_rightKey = rightKey;
		moveAmount = new Vector3f(0,0,0);
	}

	@Override
	public void Input(Input input)
	{
		float movAmt = m_speed * 1;

		if(input.GetKey(m_forwardKey))
			Move(GetTransform().GetRot().GetForward(), movAmt);
		if(input.GetKey(m_backKey))
			Move(GetTransform().GetRot().GetForward(), -movAmt);
		if(input.GetKey(m_leftKey))
			Move(GetTransform().GetRot().GetLeft(), movAmt);
		if(input.GetKey(m_rightKey))
			Move(GetTransform().GetRot().GetRight(), movAmt);
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
		moveAmount.SetY(0);
	}
}
