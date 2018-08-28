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
@Table("s_share")
public class Share  extends Base<Share> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.AUTO_INCREMENT)
	@Excel(name="自增主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="活动", sort=1, value="0", length="11", isNull=1 )
	private int active;
	@Excel(name="分类", sort=2, value="0", length="11", isNull=1 )
	private int type;
	@Excel(name="日期  yyyy-MM-dd HH:mm:ss", sort=3, length="20", isNull=1 )
	private String date;
	@Excel(name="ip", sort=4, length="255", isNull=1 )
	private String ip;
	@Excel(name="参与人", sort=5, length="255", isNull=1 )
	private String other;
	@Excel(name="附加信息", sort=6, length="255", isNull=1 )
	private String info;
	@Excel(name="附加信息1", sort=7, length="255", isNull=1 )
	private String info1;
	@Excel(name="附加信息2", sort=8, length="255", isNull=1 )
	private String info2;
	@Excel(name="附加信息3", sort=9, length="", isNull=1 )
	private String info3;
	@Excel(name="附加信息4", sort=10, length="", isNull=1 )
	private String info4;
	
	//get set
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getInfo1() {
		return info1;
	}
	public void setInfo1(String info1) {
		this.info1 = info1;
	}
	public String getInfo2() {
		return info2;
	}
	public void setInfo2(String info2) {
		this.info2 = info2;
	}
	public String getInfo3() {
		return info3;
	}
	public void setInfo3(String info3) {
		this.info3 = info3;
	}
	public String getInfo4() {
		return info4;
	}
	public void setInfo4(String info4) {
		this.info4 = info4;
	}

}
