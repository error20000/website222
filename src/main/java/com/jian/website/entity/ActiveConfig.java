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
@Table("s_active_config")
public class ActiveConfig  extends Base<ActiveConfig> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.NORMAL)
	@Excel(name="主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="分类", sort=1, value="0", length="11", isNull=1 )
	private int type;
	@Excel(name="名称", sort=2, length="255", isNull=1 )
	private String name;
	@Excel(name="排序", sort=3, value="999", length="10", isNull=1 )
	private int sort;
	@Excel(name="描述", sort=4, length="255", isNull=1 )
	private String desc;
	@Excel(name="成功后操作  0：无操作，1：发送礼包，2：发送短信，3：发送邮件，4：调用接口", sort=5, value="0", length="4", isNull=1 )
	private int smode;
	@Excel(name="操作内容", sort=6, value="0", length="11", isNull=1 )
	private int svalue;
	
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
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getSmode() {
		return smode;
	}
	public void setSmode(int smode) {
		this.smode = smode;
	}
	public int getSvalue() {
		return svalue;
	}
	public void setSvalue(int svalue) {
		this.svalue = svalue;
	}

}
