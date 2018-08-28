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
@Table("s_heroes_attr_tp_sel")
public class HeroesAttrTpSel  extends Base<HeroesAttrTpSel> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.AUTO_INCREMENT)
	@Excel(name="自增主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="模版", sort=1, value="0", length="11", isNull=1 )
	private int temp;
	@Excel(name="名称", sort=2, length="100", isNull=1 )
	private String name;
	@Excel(name="字段", sort=3, length="100", isNull=1 )
	private String code;
	@Excel(name="值", sort=4, length="255", isNull=1 )
	private String value;
	
	//get set
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getTemp() {
		return temp;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
