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
@Table("s_video")
public class Video  extends Base<Video> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.AUTO_INCREMENT)
	@Excel(name="自增主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="分类", sort=1, value="0", length="11", isNull=1 )
	private int type;
	@Excel(name="名称", sort=2, length="100", isNull=1 )
	private String name;
	@Excel(name="封面图片", sort=3, length="255", isNull=1 )
	private String pic;
	@Excel(name="ogg", sort=4, length="255", isNull=1 )
	private String ogg;
	@Excel(name="mp4", sort=5, length="255", isNull=1 )
	private String mp4;
	@Excel(name="webm", sort=6, length="255", isNull=1 )
	private String webm;
	@Excel(name="flash", sort=7, length="255", isNull=1 )
	private String flash;
	@Excel(name="网址", sort=8, length="255", isNull=1 )
	private String site;
	@Excel(name="日期", sort=9, length="20", isNull=1 )
	private String date;
	@Excel(name="描述", sort=10, length="", isNull=1 )
	private String description;
	@Excel(name="作者", sort=11, length="255", isNull=1 )
	private String author;
	@Excel(name="推荐  0--否，1--是", sort=12, value="0", length="2", isNull=1 )
	private int recommend;
	@Excel(name="加精  0--否，1--是", sort=13, value="0", length="2", isNull=1 )
	private int highlight;
	@Excel(name="下载地址", sort=14, length="255", isNull=1 )
	private String down;
	@Excel(name="排序", sort=15, value="999", length="11", isNull=1 )
	private int sort;
	@Excel(name="是否推到首页  0--否，1--是", sort=16, value="0", length="4", isNull=1 )
	private int home;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getOgg() {
		return ogg;
	}
	public void setOgg(String ogg) {
		this.ogg = ogg;
	}
	public String getMp4() {
		return mp4;
	}
	public void setMp4(String mp4) {
		this.mp4 = mp4;
	}
	public String getWebm() {
		return webm;
	}
	public void setWebm(String webm) {
		this.webm = webm;
	}
	public String getFlash() {
		return flash;
	}
	public void setFlash(String flash) {
		this.flash = flash;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public int getHighlight() {
		return highlight;
	}
	public void setHighlight(int highlight) {
		this.highlight = highlight;
	}
	public String getDown() {
		return down;
	}
	public void setDown(String down) {
		this.down = down;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getHome() {
		return home;
	}
	public void setHome(int home) {
		this.home = home;
	}

}
