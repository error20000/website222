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
@Table("s_player")
public class Player  extends Base<Player> {
	
	//field
	@PrimaryKey(type=PrimaryKeyType.AUTO_INCREMENT)
	@Excel(name="自增主键", sort=0, length="11", isNull=0 )
	private int pid;
	@Excel(name="IP", sort=1, length="100", isNull=1 )
	private String ip;
	@Excel(name="日期", sort=2, length="20", isNull=1 )
	private String date;
	@Excel(name="分类", sort=3, value="0", length="11", isNull=1 )
	private int type;
	@Excel(name="分组", sort=4, length="100", isNull=1 )
	private String group;
	@Excel(name="参与者", sort=5, length="255", isNull=1 )
	private String player;
	@Excel(name="头像", sort=6, length="255", isNull=1 )
	private String icon;
	@Excel(name="文章", sort=7, length="", isNull=1 )
	private String content;
	@Excel(name="图片", sort=8, length="", isNull=1 )
	private String pic;
	@Excel(name="视频", sort=9, length="", isNull=1 )
	private String video;
	@Excel(name="音频", sort=10, length="", isNull=1 )
	private String audio;
	@Excel(name="标题", sort=11, length="255", isNull=1 )
	private String title;
	@Excel(name="描述", sort=12, length="255", isNull=1 )
	private String desc;
	@Excel(name="附加信息", sort=13, length="255", isNull=1 )
	private String info;
	@Excel(name="", sort=14, length="255", isNull=1 )
	private String info1;
	@Excel(name="", sort=15, length="255", isNull=1 )
	private String info2;
	@Excel(name="", sort=16, length="255", isNull=1 )
	private String info3;
	@Excel(name="", sort=17, length="255", isNull=1 )
	private String info4;
	@Excel(name="浏览量", sort=18, value="0", length="11", isNull=1 )
	private int view;
	@Excel(name="点赞量", sort=19, value="0", length="11", isNull=1 )
	private int good;
	@Excel(name="偏移量", sort=20, value="0", length="11", isNull=1 )
	private int offset;
	@Excel(name="状态：0 -- 待审核，1 -- 通过，2 -- 未通过", sort=21, value="0", length="4", isNull=1 )
	private int status;
	@Excel(name="审核人", sort=22, length="100", isNull=1 )
	private String auther;
	@Excel(name="审核时间", sort=23, length="20", isNull=1 )
	private String adate;
	
	//get set
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getInfo1() {
		return info1;
	}
	public void setInfo1(String info1) {
		this.info1 = info1;
	}
	public String getInfo2() {
		return info2;
	}
	public void setInfo2(String info2) {
		this.info2 = info2;
	}
	public String getInfo3() {
		return info3;
	}
	public void setInfo3(String info3) {
		this.info3 = info3;
	}
	public String getInfo4() {
		return info4;
	}
	public void setInfo4(String info4) {
		this.info4 = info4;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	public String getAdate() {
		return adate;
	}
	public void setAdate(String adate) {
		this.adate = adate;
	}

}
