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
@Table("s_bespeak_type")
public class BespeakType  extends Base<BespeakType> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.NORMAL)
	@Excel(name="主键", sort=0, length="10", isNull=0 )
	private int pid;
	@Excel(name="名称", sort=1, length="255", isNull=1 )
	private String name;
	@Excel(name="开始时间  yyyy-MM-dd HH:mm:ss", sort=2, length="20", isNull=1 )
	private String start;
	@Excel(name="结束时间  yyyy-MM-dd HH:mm:ss", sort=3, length="20", isNull=1 )
	private String end;
	@Excel(name="状态   0 -- 关闭  1 --开启", sort=4, value="0", length="4", isNull=1 )
	private int status;
	@Excel(name="偏移量", sort=5, value="0", length="11", isNull=1 )
	private int offset;
	
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
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}

}
