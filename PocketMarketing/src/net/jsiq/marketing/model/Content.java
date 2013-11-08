package net.jsiq.marketing.model;

public class Content {

	private int contentId;
	private String contentTitle;
	private String contentListPic;
	private String contentTopPic;
	private String contentSummary;
	private int topShowFlag;

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public String getContentListPic() {
		return contentListPic;
	}

	public void setContentListPic(String contentListPic) {
		this.contentListPic = contentListPic;
	}

	public String getContentTopPic() {
		return contentTopPic;
	}

	public void setContentTopPic(String contentTopPic) {
		this.contentTopPic = contentTopPic;
	}

	public String getContentSummary() {
		return contentSummary;
	}

	public void setContentSummary(String contentSummary) {
		this.contentSummary = contentSummary;
	}

	public int getTopShowFlag() {
		return topShowFlag;
	}

	public void setTopShowFlag(int topShowFlag) {
		this.topShowFlag = topShowFlag;
	}

}
