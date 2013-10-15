package com.intro.compintro.datastruct;

public class SecondaryMenuItem {

	private int iconId;
	private int titleId;

	public SecondaryMenuItem(int iconId, int titleId) {
		this.iconId = iconId;
		this.titleId = titleId;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public int getTitleId() {
		return titleId;
	}

	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

}
