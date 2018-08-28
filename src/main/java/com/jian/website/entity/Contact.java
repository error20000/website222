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
@Table("s_contact")
public class Contact  extends Base<Contact> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.AUTO_INCREMENT)
	@Excel(name="自增主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="分类", sort=1, value="0", length="11", isNull=1 )
	private int type;
	@Excel(name="社交配置", sort=2, value="0", length="11", isNull=1 )
	private int config;
	@Excel(name="名称", sort=3, length="100", isNull=1 )
	private String name;
	@Excel(name="值", sort=4, length="255", isNull=1 )
	private String value;
	@Excel(name="地址", sort=5, length="255", isNull=1 )
	private String site;
	@Excel(name="图片", sort=6, length="255", isNull=1 )
	private String pic;
	@Excel(name="排序", sort=7, value="999", length="11", isNull=1 )
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
	public int getConfig() {
		return config;
	}
	public void setConfig(int config) {
		this.config = config;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}

}
