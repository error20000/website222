package com.jian.website.entity;

//import
import com.jian.annotation.PrimaryKey;
import com.jian.annotation.PrimaryKeyType;
import com.jian.annotation.Table;
import com.jian.annotation.Excel;

/**
 * @author liujian
 * @Date 
 */
@Table("s_resources")
public class Resources  extends Base<Resources> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.AUTO_INCREMENT)
	@Excel(name="自增主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="分类", sort=1, value="0", length="11", isNull=1 )
	private int type;
	@Excel(name="推荐  0--否，1--是", sort=2, value="0", length="4", isNull=1 )
	private int recommend;
	@Excel(name="插图", sort=3, length="255", isNull=1 )
	private String illustration;
	@Excel(name="标题", sort=4, length="255", isNull=1 )
	private String title;
	@Excel(name="副标题", sort=5, length="255", isNull=1 )
	private String subtitle;
	@Excel(name="日期  yyyy-MM-dd HH:mm:ss", sort=6, length="20", isNull=1 )
	private String date;
	@Excel(name="使用链接   0--否，1--是", sort=7, value="0", length="4", isNull=1 )
	private int marks;
	@Excel(name="链接地址", sort=8, length="255", isNull=1 )
	private String site;
	@Excel(name="内容", sort=9, length="", isNull=1 )
	private String content;
	@Excel(name="关键字 （seo）", sort=10, length="", isNull=1 )
	private String keywords;
	@Excel(name="描述 （seo）", sort=11, length="", isNull=1 )
	private String description;
	
	//get set
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public String getIllustration() {
		return illustration;
	}
	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
