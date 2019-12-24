package com.lonewolf.lingine.Modules;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Mod
{
	String moduleID();
	String moduleVersion();
	String moduleName();
	boolean masterModule();
}
