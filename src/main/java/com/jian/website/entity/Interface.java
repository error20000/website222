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
@Table("s_interface")
public class Interface  extends Base<Interface> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.AUTO_INCREMENT)
	@Excel(name="自增主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="名称", sort=1, length="100", isNull=1 )
	private String name;
	@Excel(name="英文名  调用接口时使用", sort=2, length="100", isNull=1 )
	private String en;
	@Excel(name="接口地址", sort=3, length="255", isNull=1 )
	private String url;
	@Excel(name="状态    0：禁用，1：启用", sort=4, value="0", length="4", isNull=1 )
	private int status;
	@Excel(name="请求方式   0：POST，1：GET", sort=5, value="0", length="4", isNull=1 )
	private int reqType;
	@Excel(name="请求数据格式   0：无要求，1：JSON，2：XML", sort=6, value="0", length="4", isNull=1 )
	private int reqStructure;
	@Excel(name="请求参数   多个逗号隔开", sort=7, length="", isNull=1 )
	private String reqParams;
	@Excel(name="参数加密方式  0：不加密，1：MD5", sort=8, value="0", length="4", isNull=1 )
	private int signType;
	@Excel(name="密文参数名", sort=9, length="100", isNull=1 )
	private String signName;
	@Excel(name="参数链接方式   0：key=value&...，1：keyvalue...", sort=10, value="0", length="4", isNull=1 )
	private int signMode;
	@Excel(name="连接符", sort=11, value="=", length="10", isNull=1 )
	private String connector;
	@Excel(name="分隔符", sort=12, value="&", length="10", isNull=1 )
	private String separator;
	@Excel(name="密钥方式  0：无密钥，1：服务器密钥，2：时间戳（毫秒），3：时间戳（秒），4：自定义密钥，5：固定密钥123456700", sort=13, value="0", length="4 unsigned", isNull=1 )
	private int keyType;
	@Excel(name="密钥参数名", sort=14, length="100", isNull=1 )
	private String keyName;
	@Excel(name="自定义密钥", sort=15, length="100", isNull=1 )
	private String keyValue;
	@Excel(name="响应方式   0：respones stream，1：respones body", sort=16, value="0", length="4", isNull=1 )
	private int respType;
	@Excel(name="响应数据类型    0：字符串，1：JSON，2：XML", sort=17, value="0", length="4", isNull=1 )
	private int respStructure;
	@Excel(name="是否处理结果   0：否，1：是", sort=18, value="0", length="4", isNull=1 )
	private int success;
	@Excel(name="结果的标识", sort=19, length="100", isNull=1 )
	private String sname;
	@Excel(name="成功结果的标识", sort=20, length="100", isNull=1 )
	private String scode;
	@Excel(name="图形码验证  0：不验证，1：正确后失效，2：正确后不失效", sort=21, value="0", length="4", isNull=1 )
	private int tcode;
	@Excel(name="短信验证码验证   0：不验证，1：正确后失效，2：正确后不失效", sort=22, value="0", length="4", isNull=1 )
	private int vcode;
	@Excel(name="其他验证   0：不验证，1：DH验证后失效，2：DH验证后不失效", sort=23, value="0", length="4", isNull=1 )
	private int sign;
	@Excel(name="IP限制  0：否，1：是", sort=24, value="0", length="4", isNull=1 )
	private int limit;
	@Excel(name="失败重发  0：否，1：是", sort=25, value="0", length="4", isNull=1 )
	private int reSend;
	@Excel(name="重发次数", sort=26, value="0", length="11", isNull=1 )
	private int reCount;
	@Excel(name="接口文档说明", sort=27, length="", isNull=1 )
	private String info;
	
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
	public String getEn() {
		return en;
	}
	public void setEn(String en) {
		this.en = en;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getReqType() {
		return reqType;
	}
	public void setReqType(int reqType) {
		this.reqType = reqType;
	}
	public int getReqStructure() {
		return reqStructure;
	}
	public void setReqStructure(int reqStructure) {
		this.reqStructure = reqStructure;
	}
	public String getReqParams() {
		return reqParams;
	}
	public void setReqParams(String reqParams) {
		this.reqParams = reqParams;
	}
	public int getSignType() {
		return signType;
	}
	public void setSignType(int signType) {
		this.signType = signType;
	}
	public String getSignName() {
		return signName;
	}
	public void setSignName(String signName) {
		this.signName = signName;
	}
	public int getSignMode() {
		return signMode;
	}
	public void setSignMode(int signMode) {
		this.signMode = signMode;
	}
	public String getConnector() {
		return connector;
	}
	public void setConnector(String connector) {
		this.connector = connector;
	}
	public String getSeparator() {
		return separator;
	}
	public void setSeparator(String separator) {
		this.separator = separator;
	}
	public int getKeyType() {
		return keyType;
	}
	public void setKeyType(int keyType) {
		this.keyType = keyType;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	public int getRespType() {
		return respType;
	}
	public void setRespType(int respType) {
		this.respType = respType;
	}
	public int getRespStructure() {
		return respStructure;
	}
	public void setRespStructure(int respStructure) {
		this.respStructure = respStructure;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	public int getTcode() {
		return tcode;
	}
	public void setTcode(int tcode) {
		this.tcode = tcode;
	}
	public int getVcode() {
		return vcode;
	}
	public void setVcode(int vcode) {
		this.vcode = vcode;
	}
	public int getSign() {
		return sign;
	}
	public void setSign(int sign) {
		this.sign = sign;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getReSend() {
		return reSend;
	}
	public void setReSend(int reSend) {
		this.reSend = reSend;
	}
	public int getReCount() {
		return reCount;
	}
	public void setReCount(int reCount) {
		this.reCount = reCount;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

}
