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
@Table("s_active_config_attr_tp")
public class ActiveConfigAttrTp  extends Base<ActiveConfigAttrTp> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.AUTO_INCREMENT)
	@Excel(name="自增主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="分类", sort=1, value="0", length="11", isNull=1 )
	private int type;
	@Excel(name="类型  0：text，1：textarea，2：file，3：select，4：radio，5：checkbox", sort=2, value="0", length="4", isNull=1 )
	private int input;
	@Excel(name="名称", sort=3, length="100", isNull=1 )
	private String name;
	@Excel(name="字段", sort=4, length="100", isNull=1 )
	private String code;
	@Excel(name="默认值", sort=5, length="255", isNull=1 )
	private String def;
	@Excel(name="是否必填  0：是，1：否", sort=6, value="1", length="4", isNull=1 )
	private int isnull;
	@Excel(name="是否展示  0：否，1：是", sort=7, value="1", length="4", isNull=1 )
	private int isshow;
	@Excel(name="描述", sort=8, length="255", isNull=1 )
	private String desc;
	
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
	public int getInput() {
		return input;
	}
	public void setInput(int input) {
		this.input = input;
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
	public String getDef() {
		return def;
	}
	public void setDef(String def) {
		this.def = def;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public int getIsshow() {
		return isshow;
	}
	public void setIsshow(int isshow) {
		this.isshow = isshow;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
