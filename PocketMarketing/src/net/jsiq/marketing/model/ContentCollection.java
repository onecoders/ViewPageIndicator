package net.jsiq.marketing.model;

public class ContentCollection {

	private long _id;

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	private int contentId;
	private String contentTitle;
	private String contentSummary;

	public ContentCollection() {

	}

	public ContentCollection(int contentId, String contentTitle,
			String contentSummary) {
		this.contentId = contentId;
		this.contentTitle = contentTitle;
		this.contentSummary = contentSummary;
	}

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

	public String getContentSummary() {
		return contentSummary;
	}

	public void setContentSummary(String contentSummary) {
		this.contentSummary = contentSummary;
	}

}
