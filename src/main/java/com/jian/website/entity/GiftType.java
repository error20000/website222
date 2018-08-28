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
@Table("s_gift_type")
public class GiftType  extends Base<GiftType> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.NORMAL)
	@Excel(name="主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="名称", sort=1, length="100", isNull=1 )
	private String name;
	@Excel(name="领取方式  0：只记录，1：返回礼包码，2：短信发送，3：邮件发送，4：接口发送", sort=2, value="0", length="4", isNull=1 )
	private int mode;
	@Excel(name="方式内容", sort=3, value="0", length="11", isNull=1 )
	private int mvalue;
	@Excel(name="限制  0：不限制，1：只能自己使用，2：只能他人使用", sort=4, value="0", length="4", isNull=1 )
	private int limit;
	
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
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public int getMvalue() {
		return mvalue;
	}
	public void setMvalue(int mvalue) {
		this.mvalue = mvalue;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}

}
