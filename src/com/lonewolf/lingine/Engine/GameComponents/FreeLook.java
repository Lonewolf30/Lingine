package com.lonewolf.lingine.Engine.GameComponents;

import com.lonewolf.lingine.Engine.CoreEngine.Input;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Engine.CoreEngine.Vector3f;

public class FreeLook extends GameComponent
{
	private static final Vector3f Y_AXIS = new Vector3f(0,1,0);

	private boolean m_mouseLocked = false;
	private float   m_sensitivity;
	private int     m_unlockMouseKey;

	public FreeLook(float sensitivity)
	{
		this(sensitivity, Input.KEY_ESCAPE);
	}

	public FreeLook(float sensitivity, int unlockMouseKey)
	{
		this.m_sensitivity = sensitivity;
		this.m_unlockMouseKey = unlockMouseKey;
	}

	@Override
	public void Input(Input input)
	{
		Vector2f centerPosition = engine.getRenderEngine().getWindow().getWindowSize().Div(2);

		if(input.GetKey(m_unlockMouseKey))
		{
			input.SetCursor(true);
			m_mouseLocked = false;
		}
		if(input.GetMouseDown(0))
		{
			input.SetMousePosition(centerPosition);
			input.SetCursor(false);
			m_mouseLocked = true;
		}

		if(m_mouseLocked)
		{
			Vector2f deltaPos = input.GetMousePosition().Sub(centerPosition);

			boolean rotY = deltaPos.GetX() != 0;
			boolean rotX = deltaPos.GetY() != 0;

			if(rotY)
				GetTransform().Rotate(Y_AXIS, (float) Math.toRadians(deltaPos.GetX() * m_sensitivity));
			if(rotX)
				GetTransform().Rotate(GetTransform().GetRot().GetRight(), (float) Math.toRadians(-deltaPos.GetY() * m_sensitivity));

			if(rotY || rotX)
				input.SetMousePosition(centerPosition);
		}
	}
}
