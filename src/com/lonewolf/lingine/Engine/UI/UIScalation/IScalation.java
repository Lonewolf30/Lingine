package com.lonewolf.lingine.Engine.UI.UIScalation;

import com.lonewolf.lingine.Engine.Rendering.Window;
import com.lonewolf.lingine.Engine.UI.IInterfaceProperty;
import com.lonewolf.lingine.Engine.UI.UiObject;

public interface IScalation extends IInterfaceProperty
{
	void setParent(UiObject parent);
	
	void calculateScalation(Window window);
	
	int getWidth();
	
	int getHeight();
}
