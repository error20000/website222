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
@Table("s_heroes")
public class Heroes  extends Base<Heroes> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.AUTO_INCREMENT)
	@Excel(name="自增主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="日期", sort=1, length="20", isNull=1 )
	private String date;
	@Excel(name="名称", sort=2, length="100", isNull=1 )
	private String name;
	@Excel(name="分类", sort=3, value="0", length="10", isNull=1 )
	private int type;
	@Excel(name="标签：多个标签“ , ”分隔。", sort=4, length="255", isNull=1 )
	private String tags;
	@Excel(name="排序", sort=5, value="999", length="11", isNull=1 )
	private int sort;
	
	//get set
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}

}
