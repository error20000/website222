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
@Table("s_interface_logs")
public class InterfaceLogs  extends Base<InterfaceLogs> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.AUTO_INCREMENT)
	@Excel(name="自增主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="接口", sort=1, value="0", length="11", isNull=1 )
	private int ifs;
	@Excel(name="参数", sort=2, length="", isNull=1 )
	private String params;
	@Excel(name="日期  yyyy-MM-dd HH:mm:ss", sort=3, length="20", isNull=1 )
	private String date;
	@Excel(name="状态   0：失败 ， 1 ： 成功", sort=4, value="0", length="4", isNull=1 )
	private int status;
	@Excel(name="接口返回信息", sort=5, length="", isNull=1 )
	private String ifsRes;
	@Excel(name="重发次数", sort=6, value="0", length="4", isNull=1 )
	private int reCount;
	@Excel(name="重发人", sort=7, length="100", isNull=1 )
	private String reUser;
	@Excel(name="重发时间", sort=8, length="20", isNull=1 )
	private String reDate;
	@Excel(name="重发结果", sort=9, length="", isNull=1 )
	private String reSend;
	@Excel(name="附加信息", sort=10, length="255", isNull=1 )
	private String info;
	
	//get set
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getIfs() {
		return ifs;
	}
	public void setIfs(int ifs) {
		this.ifs = ifs;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getIfsRes() {
		return ifsRes;
	}
	public void setIfsRes(String ifsRes) {
		this.ifsRes = ifsRes;
	}
	public int getReCount() {
		return reCount;
	}
	public void setReCount(int reCount) {
		this.reCount = reCount;
	}
	public String getReUser() {
		return reUser;
	}
	public void setReUser(String reUser) {
		this.reUser = reUser;
	}
	public String getReDate() {
		return reDate;
	}
	public void setReDate(String reDate) {
		this.reDate = reDate;
	}
	public String getReSend() {
		return reSend;
	}
	public void setReSend(String reSend) {
		this.reSend = reSend;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

}
