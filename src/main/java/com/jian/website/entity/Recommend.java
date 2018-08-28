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
@Table("s_recommend")
public class Recommend  extends Base<Recommend> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.AUTO_INCREMENT)
	@Excel(name="自增主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="分类", sort=1, value="0", length="11", isNull=1 )
	private int type;
	@Excel(name="名称", sort=2, length="100", isNull=1 )
	private String name;
	@Excel(name="图片地址", sort=3, length="255", isNull=1 )
	private String pic;
	@Excel(name="启用链接：0 -- 不用； 1 -- 启用消息；2 -- 启用地址", sort=4, value="0", length="2", isNull=1 )
	private int marks;
	@Excel(name="消息 id", sort=5, value="0", length="11", isNull=1 )
	private int newsId;
	@Excel(name="链接地址", sort=6, length="255", isNull=1 )
	private String site;
	@Excel(name="状态：0 --禁用；1 -- 启用", sort=7, value="0", length="2", isNull=1 )
	private int status;
	@Excel(name="排序", sort=8, value="999", length="11", isNull=1 )
	private int sort;
	
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
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}

}
