package com.intro.compintro.model;

public class MenuItem {
	private int iconId;
	private String titleZh;
	private String titleEn;

	public MenuItem(int iconId, String titleCh, String titleEn) {
		this.iconId = iconId;
		this.titleZh = titleCh;
		this.titleEn = titleEn;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public String getTitleZh() {
		return titleZh;
	}

	public void setTitleZh(String titleZh) {
		this.titleZh = titleZh;
	}

	public String getTitleEn() {
		return titleEn;
	}

	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}

}
