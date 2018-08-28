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
@Table("s_menu")
public class Menu  extends Base<Menu> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.AUTO_INCREMENT)
	@Excel(name="主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="父级pid", sort=1, value="0", length="11", isNull=1 )
	private int parent;
	@Excel(name="名称", sort=2, length="20", isNull=1 )
	private String name;
	@Excel(name="地址", sort=3, length="100", isNull=1 )
	private String site;
	@Excel(name="图标", sort=4, length="100", isNull=1 )
	private String icon;
	@Excel(name="状态  0--禁用，1--启用", sort=5, value="0", length="4", isNull=1 )
	private int status;
	
	//get set
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
