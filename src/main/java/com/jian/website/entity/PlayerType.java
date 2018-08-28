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
@Table("s_player_type")
public class PlayerType  extends Base<PlayerType> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.NORMAL)
	@Excel(name="主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="名称", sort=1, length="255", isNull=1 )
	private String name;
	@Excel(name="标识", sort=2, length="255", isNull=1 )
	private String en;
	@Excel(name="开始时间  yyyy-MM-dd HH:mm:ss", sort=3, length="20", isNull=1 )
	private String start;
	@Excel(name="结束时间  yyyy-MM-dd HH:mm:ss", sort=4, length="20", isNull=1 )
	private String end;
	@Excel(name="状态   0 -- 关闭  1 --开启", sort=5, value="0", length="4", isNull=1 )
	private int status;
	@Excel(name="参加次数   小于0 --- 无限次", sort=6, value="0", length="11", isNull=1 )
	private int count;
	@Excel(name="是否审核   0 -- 否  1 --是", sort=7, value="0", length="4", isNull=1 )
	private int auth;
	@Excel(name="是否验证  0 -- 否  1 --是", sort=8, value="0", length="4", isNull=1 )
	private int sign;
	@Excel(name="是否分享  0 -- 否  1 --是", sort=9, value="0", length="4", isNull=1 )
	private int share;
	@Excel(name="分享模式限制", sort=10, value="0", length="4", isNull=1 )
	private int share_mode;
	@Excel(name="每次分享获得参与次数", sort=11, value="0", length="11", isNull=1 )
	private int share_count;
	@Excel(name="分享次数   小于0 --- 无限次", sort=12, value="0", length="11", isNull=1 )
	private int share_limit;
	@Excel(name="是否投票  0 -- 否  1 --是", sort=13, value="0", length="4", isNull=1 )
	private int poll;
	@Excel(name="投票模式限制", sort=14, value="0", length="4", isNull=1 )
	private int poll_mode;
	@Excel(name="每次投票获得票数", sort=15, value="0", length="11", isNull=1 )
	private int poll_count;
	@Excel(name="投票次数   小于0 --- 无限次", sort=16, value="0", length="11", isNull=1 )
	private int poll_limit;
	
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public int getSign() {
		return sign;
	}
	public void setSign(int sign) {
		this.sign = sign;
	}
	public int getShare() {
		return share;
	}
	public void setShare(int share) {
		this.share = share;
	}
	public int getShare_mode() {
		return share_mode;
	}
	public void setShare_mode(int share_mode) {
		this.share_mode = share_mode;
	}
	public int getShare_count() {
		return share_count;
	}
	public void setShare_count(int share_count) {
		this.share_count = share_count;
	}
	public int getShare_limit() {
		return share_limit;
	}
	public void setShare_limit(int share_limit) {
		this.share_limit = share_limit;
	}
	public int getPoll() {
		return poll;
	}
	public void setPoll(int poll) {
		this.poll = poll;
	}
	public int getPoll_mode() {
		return poll_mode;
	}
	public void setPoll_mode(int poll_mode) {
		this.poll_mode = poll_mode;
	}
	public int getPoll_count() {
		return poll_count;
	}
	public void setPoll_count(int poll_count) {
		this.poll_count = poll_count;
	}
	public int getPoll_limit() {
		return poll_limit;
	}
	public void setPoll_limit(int poll_limit) {
		this.poll_limit = poll_limit;
	}

}
