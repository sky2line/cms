package cn.com.weiwang.cms.backend.entity;

import java.util.Date;

public class Article {
	private int id;
	private String title;
	private String content;
	private String source;
	private Date createTime;
	private Date updateTime;
	private Date deployTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getDeployTime() {
		return deployTime;
	}
	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content="
				+ content + ", source=" + source + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", deployTime=" + deployTime
				+ "]";
	}
	
	public Article() {
	}
	public Article(int id, String title, String content, String source,
			Date createTime, Date updateTime, Date deployTime) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.source = source;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.deployTime = deployTime;
	}
			
}
