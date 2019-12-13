package com.lonewolf.lingine.Engine.UI.UIAnimation;

public class UIAnimator
{
	private LocationShift locationShift;
	private ScaleShift scaleShift;
	
	public LocationShift getLocationShift()
	{
		return locationShift;
	}
	
	public ScaleShift getScaleShift()
	{
		return scaleShift;
	}
	
	public void setLocationShift(LocationShift locationShift)
	{
		this.locationShift = locationShift;
	}
	
	public void setScaleShift(ScaleShift scaleShift)
	{
		this.scaleShift = scaleShift;
	}
}
