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
@Table("s_system_logs")
public class SystemLogs  extends Base<SystemLogs> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.AUTO_INCREMENT)
	@Excel(name="自增主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="分类", sort=1, length="10", isNull=1 )
	private String type;
	@Excel(name="日期", sort=2, length="20", isNull=1 )
	private String date;
	@Excel(name="用户", sort=3, value="0", length="11", isNull=1 )
	private int user;
	@Excel(name="ip", sort=4, length="100", isNull=1 )
	private String ip;
	@Excel(name="按钮", sort=5, length="100", isNull=1 )
	private String uri;
	@Excel(name="参数", sort=6, length="", isNull=1 )
	private String params;
	@Excel(name="更新前", sort=7, length="", isNull=1 )
	private String before;
	@Excel(name="更新后", sort=8, length="", isNull=1 )
	private String after;
	
	//get set
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getBefore() {
		return before;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	public String getAfter() {
		return after;
	}
	public void setAfter(String after) {
		this.after = after;
	}

}
