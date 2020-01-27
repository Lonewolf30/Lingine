package com.lonewolf.lingine.Engine.UI.UITranslation;

import com.lonewolf.lingine.Engine.UI.UiObject;
import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;

public interface ITranslation
{
	void setParent(UiObject parent);
	
	void calculateTranslation(Vector2f windowSize);
	
	int GetXTranslation();
	
	int GetYTranslation();
}
