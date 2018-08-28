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
@Table("s_sms_logs")
public class SmsLogs  extends Base<SmsLogs> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.AUTO_INCREMENT)
	@Excel(name="自增主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="日期", sort=1, length="20", isNull=1 )
	private String date;
	@Excel(name="手机号", sort=2, length="20", isNull=1 )
	private String phone;
	@Excel(name="IP", sort=3, length="100", isNull=1 )
	private String ip;
	@Excel(name="发送内容", sort=4, length="255", isNull=1 )
	private String content;
	@Excel(name="状态   0：失败，1：成功", sort=5, value="1", length="2", isNull=1 )
	private int status;
	@Excel(name="返回信息", sort=6, length="", isNull=1 )
	private String msg;
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
