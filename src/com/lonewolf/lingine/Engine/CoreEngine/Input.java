package com.lonewolf.lingine.Engine.CoreEngine;

import com.lonewolf.lingine.Engine.Rendering.Window;

import static  org.lwjgl.glfw.GLFW.*;

public class Input
{
	public static final int NUM_KEYCODES = 512;
	public static final int NUM_MOUSEBUTTONS = 5;

	public static final int KEY_ESCAPE          = GLFW_KEY_ESCAPE;
	public static final int KEY_1               = GLFW_KEY_1;
	public static final int KEY_2               = GLFW_KEY_2;
	public static final int KEY_3               = GLFW_KEY_3;
	public static final int KEY_4               = GLFW_KEY_4;
	public static final int KEY_5               = GLFW_KEY_5;
	public static final int KEY_6               = GLFW_KEY_6;
	public static final int KEY_7               = GLFW_KEY_7;
	public static final int KEY_8               = GLFW_KEY_8;
	public static final int KEY_9               = GLFW_KEY_9;
	public static final int KEY_0               = GLFW_KEY_0;
	public static final int KEY_MINUS           = GLFW_KEY_MINUS; /* - on main keyboard */
	public static final int KEY_EQUALS          = GLFW_KEY_EQUAL;
	public static final int KEY_BACK            = GLFW_KEY_BACKSPACE; /* backspace */
	public static final int KEY_TAB             = GLFW_KEY_TAB;
	public static final int KEY_Q               = GLFW_KEY_Q;
	public static final int KEY_W               = GLFW_KEY_W;
	public static final int KEY_E               = GLFW_KEY_E;
	public static final int KEY_R               = GLFW_KEY_R;
	public static final int KEY_T               = GLFW_KEY_T;
	public static final int KEY_Y               = GLFW_KEY_Y;
	public static final int KEY_U               = GLFW_KEY_U;
	public static final int KEY_I               = GLFW_KEY_I;
	public static final int KEY_O               = GLFW_KEY_O;
	public static final int KEY_P               = GLFW_KEY_P;
	public static final int KEY_LBRACKET        = GLFW_KEY_LEFT_BRACKET;
	public static final int KEY_RBRACKET        = GLFW_KEY_RIGHT_BRACKET;
	public static final int KEY_RETURN          = GLFW_KEY_ENTER; /* Enter on main keyboard */
	public static final int KEY_LCONTROL        = GLFW_KEY_LEFT_CONTROL;
	public static final int KEY_A               = GLFW_KEY_A;
	public static final int KEY_S               = GLFW_KEY_S;
	public static final int KEY_D               = GLFW_KEY_D;
	public static final int KEY_F               = GLFW_KEY_F;
	public static final int KEY_G               = GLFW_KEY_G;
	public static final int KEY_H               = GLFW_KEY_H;
	public static final int KEY_J               = GLFW_KEY_J;
	public static final int KEY_K               = GLFW_KEY_K;
	public static final int KEY_L               = GLFW_KEY_L;
	public static final int KEY_SEMICOLON       = GLFW_KEY_SEMICOLON;
	public static final int KEY_APOSTROPHE      = GLFW_KEY_APOSTROPHE;
	public static final int KEY_GRAVE           = GLFW_KEY_GRAVE_ACCENT; /* accent grave */
	public static final int KEY_LSHIFT          = GLFW_KEY_LEFT_SHIFT;
	public static final int KEY_BACKSLASH       = GLFW_KEY_BACKSLASH;
	public static final int KEY_Z               = GLFW_KEY_Z;
	public static final int KEY_X               = GLFW_KEY_X;
	public static final int KEY_C               = GLFW_KEY_C;
	public static final int KEY_V               = GLFW_KEY_V;
	public static final int KEY_B               = GLFW_KEY_B;
	public static final int KEY_N               = GLFW_KEY_N;
	public static final int KEY_M               = GLFW_KEY_M;
	public static final int KEY_COMMA           = GLFW_KEY_COMMA;
	public static final int KEY_PERIOD          = GLFW_KEY_PERIOD; /* . on main keyboard */
	public static final int KEY_SLASH           = GLFW_KEY_SLASH; /* / on main keyboard */
	public static final int KEY_RSHIFT          = GLFW_KEY_RIGHT_SHIFT;
	public static final int KEY_MULTIPLY        = GLFW_KEY_KP_MULTIPLY; /* * on numeric keypad */
	public static final int KEY_LMENU           = GLFW_KEY_LEFT_ALT; /* left Alt */
	public static final int KEY_LALT            = KEY_LMENU; /* left Alt */
	public static final int KEY_SPACE           = GLFW_KEY_SPACE;
	public static final int KEY_CAPITAL         = GLFW_KEY_CAPS_LOCK;
	public static final int KEY_F1              = GLFW_KEY_F1;
	public static final int KEY_F2              = GLFW_KEY_F2;
	public static final int KEY_F3              = GLFW_KEY_F3;
	public static final int KEY_F4              = GLFW_KEY_F4;
	public static final int KEY_F5              = GLFW_KEY_F5;
	public static final int KEY_F6              = GLFW_KEY_F6;
	public static final int KEY_F7              = GLFW_KEY_F7;
	public static final int KEY_F8              = GLFW_KEY_F8;
	public static final int KEY_F9              = GLFW_KEY_F9;
	public static final int KEY_F10             = GLFW_KEY_F10;
	public static final int KEY_NUMLOCK         = GLFW_KEY_NUM_LOCK;
	public static final int KEY_SCROLL          = GLFW_KEY_SCROLL_LOCK; /* Scroll Lock */
	public static final int KEY_NUMPAD7         = GLFW_KEY_KP_7;
	public static final int KEY_NUMPAD8         = GLFW_KEY_KP_8;
	public static final int KEY_NUMPAD9         = GLFW_KEY_KP_9;
	public static final int KEY_SUBTRACT        = GLFW_KEY_KP_SUBTRACT; /* - on numeric keypad */
	public static final int KEY_NUMPAD4         = GLFW_KEY_KP_4;
	public static final int KEY_NUMPAD5         = GLFW_KEY_KP_5;
	public static final int KEY_NUMPAD6         = GLFW_KEY_KP_6;
	public static final int KEY_ADD             = GLFW_KEY_KP_ADD; /* + on numeric keypad */
	public static final int KEY_NUMPAD1         = GLFW_KEY_KP_1;
	public static final int KEY_NUMPAD2         = GLFW_KEY_KP_2;
	public static final int KEY_NUMPAD3         = GLFW_KEY_KP_3;
	public static final int KEY_NUMPAD0         = GLFW_KEY_KP_0;
	public static final int KEY_DECIMAL         = GLFW_KEY_KP_DECIMAL; /* . on numeric keypad */
	public static final int KEY_F11             = GLFW_KEY_F11;
	public static final int KEY_F12             = GLFW_KEY_F12;
	public static final int KEY_F13             = GLFW_KEY_F13; /*                     (NEC PC98) */
	public static final int KEY_F14             = GLFW_KEY_F14; /*                     (NEC PC98) */
	public static final int KEY_F15             = GLFW_KEY_F15; /*                     (NEC PC98) */
	public static final int KEY_NUMPADENTER     = GLFW_KEY_KP_ENTER; /* Enter on numeric keypad */
	public static final int KEY_RCONTROL        = GLFW_KEY_RIGHT_CONTROL;
	public static final int KEY_DIVIDE          = GLFW_KEY_KP_DIVIDE; /* / on numeric keypad */
	public static final int KEY_RMENU           = GLFW_KEY_RIGHT_ALT; /* right Alt */
	public static final int KEY_RALT            = KEY_RMENU; /* right Alt */
	public static final int KEY_PAUSE           = GLFW_KEY_PAUSE; /* Pause */
	public static final int KEY_HOME            = GLFW_KEY_HOME; /* Home on arrow keypad */
	public static final int KEY_UP              = GLFW_KEY_UP; /* UpArrow on arrow keypad */
	public static final int KEY_LEFT            = GLFW_KEY_LEFT; /* LeftArrow on arrow keypad */
	public static final int KEY_RIGHT           = GLFW_KEY_RIGHT; /* RightArrow on arrow keypad */
	public static final int KEY_END             = GLFW_KEY_END; /* End on arrow keypad */
	public static final int KEY_DOWN            = GLFW_KEY_DOWN; /* DownArrow on arrow keypad */
	public static final int KEY_LMETA           = GLFW_KEY_MENU; /* Left Windows/Option key */
	public static final int KEY_LWIN            = KEY_LMETA; /* Left Windows key */
	
	private boolean[] m_lastKeys = new boolean[NUM_KEYCODES];
	private boolean[] m_lastMouse = new boolean[NUM_MOUSEBUTTONS];
	private Window window;

	public Input(Window window)
	{
		this.window = window;
	}
	
	public void Update()
	{
		for(int i = 0; i < NUM_KEYCODES; i++)
			m_lastKeys[i] = GetKey(i);
		
		for(int i = 0; i < NUM_MOUSEBUTTONS; i++)
			m_lastMouse[i] = GetMouse(i);
	}
	
	public boolean GetKey(int keyCode)
	{
		return window.isKeyDown(keyCode);
	}
	
	public boolean GetKeyDown(int keyCode)
	{
		if (keyCode != -1)
			return GetKey(keyCode) && !m_lastKeys[keyCode];
		else
			return false;
	}
	
	public boolean GetKeyUp(int keyCode)
	{
		return !GetKey(keyCode) && m_lastKeys[keyCode];
	}
	
	public boolean GetMouse(int mouseButton)
	{
		return window.isMouseButtonDown(mouseButton);
	}
	
	public boolean GetMouseDown(int mouseButton)
	{
		return GetMouse(mouseButton) && !m_lastMouse[mouseButton];
	}
	
	public boolean GetMouseUp(int mouseButton)
	{
		return !GetMouse(mouseButton) && m_lastMouse[mouseButton];
	}
	
	public Vector2f GetMousePosition()
	{
		return window.MousePos();
	}
	
	public void SetMousePosition(Vector2f pos)
	{
		window.setCursorPosition((int)pos.GetX(), (int)pos.GetY());
	}
	
	public void SetCursor(boolean enabled)
	{
		window.setDisplayMouseGrabbed(!enabled);
	}

	public boolean isCursorGrabbed()
	{
		return window.isMouseGrabbed();
	}

	public String decodeKey(int keyCode)
	{
		String keyName = window.getKeyName(keyCode);

		if (keyName.length() < 2)
			return keyName;

		return "";
	}
}
