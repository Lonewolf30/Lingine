package com.lonewolf.lingine.Engine.UI.UiObjects;

import com.lonewolf.lingine.Engine.UI.UiObject;

import java.util.ArrayList;

public class UiItemSelection<T> extends UiObject
{
	private ArrayList<T> items;
	private int index;
	
	public UiItemSelection(ArrayList<T> items, int index)
	{
		this.items = items;
		this.index = index;
	}
	
	public T getSelectedItem()
	{
		return items.get(index);
	}
	
	public ArrayList<T> getItems()
	{
		return items;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
}
