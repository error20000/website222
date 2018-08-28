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
@Table("s_news_type")
public class NewsType  extends Base<NewsType> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.NORMAL)
	@Excel(name="主键", sort=0, value="0", length="11", isNull=0 )
	private int pid;
	@Excel(name="名称", sort=1, length="100", isNull=1 )
	private String name;
	@Excel(name="图片", sort=2, length="255", isNull=1 )
	private String icon;
	@Excel(name="排序", sort=3, value="999", length="11", isNull=1 )
	private int sort;
	@Excel(name="首页展示  0：否，1：是", sort=4, value="1", length="4", isNull=1 )
	private int home;
	@Excel(name="是否过滤  0 --否，1--是    适用发布其他渠道的新闻", sort=5, value="0", length="4", isNull=1 )
	private int filter;
	
	//get set
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
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
	public int getFilter() {
		return filter;
	}
	public void setFilter(int filter) {
		this.filter = filter;
	}

}
