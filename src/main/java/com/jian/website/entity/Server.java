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
@Table("s_server")
public class Server  extends Base<Server> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.GROUP)
	@Excel(name="主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="日期", sort=1, length="20", isNull=1 )
	private String date;
	@PrimaryKey(type=PrimaryKeyType.GROUP)
	@Excel(name="服务器ID", sort=2, length="100", isNull=0 )
	private String serverId;
	@Excel(name="名称", sort=3, length="100", isNull=1 )
	private String name;
	@Excel(name="地址", sort=4, length="255", isNull=1 )
	private String url;
	@Excel(name="密钥", sort=5, length="255", isNull=1 )
	private String key;
	@Excel(name="状态   0：禁用，1：启用", sort=6, value="0", length="4", isNull=1 )
	private int status;
	
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
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
