package com.lonewolf.lingine.Engine.UI.UITranslation;

import com.lonewolf.lingine.Engine.CoreEngine.Vector2f;
import com.lonewolf.lingine.Engine.UI.UiObject;

public interface ITranslation
{
	void setParent(UiObject parent);
	
	void calculateTranslation(Vector2f windowSize);
	
	int GetXTranslation();
	
	int GetYTranslation();
}
