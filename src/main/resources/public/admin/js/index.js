var baseUrl = '../';

function ajaxReq(url, param, callback, cp){
	$.ajax({
		   dataType: "json",
		   type: "POST",
		   url: url,
		   data: param,
		   success: function(data){
			   	if(data.code == -203 || data.code == -111){ // token 超时
			   		parent.window.location.href = "login.html";
			   	}
				if (typeof callback === "function") {
					callback(data, cp);
				}
		   },
		   error: function(data){
		   }
		});
}

new Vue({
    el: '#app',
    data: function(){
		return {
			sysName:'后台管理',
			collapsed:false,
			sysUserName: 'admin',
			sysUserAvatar: '',
			form: {
				name: '',
				region: '',
				date1: '',
				date2: '',
				delivery: false,
				type: [],
				resource: '',
				desc: ''
			},
			menuNames: [],
			authMenu: [{
		        path: '',
		        component: "",
		        name: '预约管理',
		        iconCls: 'el-icon-menu',
		        children: [{
			        path: 'bespeakConfig.html',
			        component: "",
			        name: '预约配置',
			        iconCls: '',
			        children: []
			    },{
			        path: 'bespeak.html',
			        component: "",
			        name: '预约记录',
			        iconCls: '',
			        children: []
			    }]
		    },{
		        path: '',
		        component: "",
		        name: '扭蛋管理',
		        iconCls: 'el-icon-menu',
		        children: [{
			        path: 'activeType.html',
			        component: "",
			        name: '扭蛋配置',
			        iconCls: '',
			        children: []
			    },{
			        path: 'activeConfig.html',
			        component: "",
			        name: '扭蛋列表',
			        iconCls: '',
			        children: []
			    },{
			        path: 'active.html',
			        component: "",
			        name: '扭蛋记录',
			        iconCls: '',
			        children: []
			    }]
		    }/*,{
		        path: 'config.html',
		        component: "",
		        name: '系统配置',
		        iconCls: 'el-icon-setting',
		        children: []
		    }*/],
		    //pwd
		    pwdFormVisible: false,
		    pwdLoading: false,
		    pwdFormRules:{
				oldPwd: [
					{ required: true, message: '请输入原始密码', trigger: 'blur' }
				],
				newPwd: [
					{ required: true, message: '请输入新密码', trigger: 'blur' }
				],
				newPwd2: [
					{ required: true, message: '请再次输入新密码', trigger: 'blur' },
					{ validator: (rule, value, callback) => {
				          if (value !== this.pwdForm.newPwd) {
				            callback(new Error('两次输入密码不一致!'));
				          } else {
				            callback();
				          }
					}, trigger: 'blur' }
				]
			},
			pwdForm:{
				oldPwd: '',
				newPwd: '',
				newPwd2: ''
			}
		}
	},
	methods: {
		onSubmit() {
			console.log('submit!');
		},
		handleopen() {
			//console.log('handleopen');
		},
		handleclose() {
			//console.log('handleclose');
		},
		handleselect: function (a) {
			this.showIframe(a);
		},
		//修改密码
		handlepwdChange: function(){
			this.pwdFormVisible = true;
			this.pwdForm = {
					oldPwd: '',
					newPwd: '',
					newPwd2: ''
				};
		},
		pwdChangeClose: function(){
			this.pwdFormVisible = false;
			this.pwdLoading = false;
			this.$refs.pwdForm.resetFields();
		},
		pwdChange: function(){
			this.$refs.pwdForm.validate((valid) => {
				if (valid) {
					this.$confirm('确认提交吗？', '提示', {}).then(() => {
						var params = Object.assign({}, this.pwdForm);
						delete params.newPwd2;
						var self = this;
						this.pwdLoading = true;
						var url = baseUrl+"api/user/changePWD";
						ajaxReq(url, params, function(res){
							self.addLoading = false;
							if(res.code > 0){
								self.$message({
									message: '新增成功',
									type: 'success'
								});
								self.addFormVisible = false;
								parent.window.location.href = "login.html";
							}else{
								self.$message({
									message: res.msg,
									type: 'warning'
								})
							}
						});
					});
				}
			});
		},
		//退出登录
		logout: function () {
			this.$confirm('确认退出吗?', '提示', {
				//type: 'warning'
			}).then(() => {
				var url = baseUrl + "api/user/logout";
				var params = {};
				ajaxReq(url, params, function(res){
					if(res.code > 0){
						parent.window.location.href = "login.html";
					}
				});
			}).catch(() => {

			});
		},
		//退出登录
		isLogin: function () {
			var url = baseUrl + "api/user/isLogin";
			var params = {};
			ajaxReq(url, params, function(res){
				if(res.code <= 0){
					parent.window.location.href = "login.html";
				}
			});
		},
		//折叠导航栏
		handlecollapse:function(){
			this.collapsed=!this.collapsed;
		},
		showMenu(i,status){
			this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-'+i)[0].style.display=status?'block':'none';
		},
		showIframe: function(index){
			var items = String(index).split("_");
			this.menuNames = []; //面包屑
			var name = "";
			var url = "";
			
			switch (items.length) {
			case 1:
				this.menuNames.push(this.authMenu[Number(items[0])].name);
				name = this.authMenu[Number(items[0])].name;
				url = this.authMenu[Number(items[0])].path;
				break;
			case 2:
				this.menuNames.push(this.authMenu[Number(items[0])].name);
				this.menuNames.push(this.authMenu[Number(items[0])].children[Number(items[1])].name);
				name = this.authMenu[Number(items[0])].children[Number(items[1])].name;
				url = this.authMenu[Number(items[0])].children[Number(items[1])].path;
				break;
			case 3:
				this.menuNames.push(this.authMenu[Number(items[0])].name);
				this.menuNames.push(this.authMenu[Number(items[0])].children[Number(items[1])].name);
				this.menuNames.push(this.authMenu[Number(items[0])].children[Number(items[1])].children[Number(items[2])].name);
				name = this.authMenu[Number(items[0])].children[Number(items[1])].children[Number(items[2])].name;
				url = this.authMenu[Number(items[0])].children[Number(items[1])].children[Number(items[2])].path;
				break;
				
			default:
				break;
			}
//			$('.breadcrumb-container .title').html(name);
			$('.content-iframe').attr('src', url);
		}
	},
	mounted: function() {
		this.isLogin();
	}
  });

function formatDate(d, s){
    var date = new Date();
    if(d){
        if(typeof d == 'object'){
            date = d;
        }else{
            if(isNaN(d)){
                date = new Date(d.replace(/-/g, "/").replace(/年/g, "/").replace(/月/g, "/").replace(/日/g, " ").replace(/时/g, ":").replace(/分/g, ":").replace(/秒/g, ""));
            }else{
                d = String(d).length == 10 ? d + "000" : String(d).length == 13 ? d : new Date().getTime() + Number(d);
                date = new Date(Number(d));
            }
        }
    }
    var weekday = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
    var weekdayS = ["日","一","二","三","四","五","六"];
    var weekdayEn = ["Sunday","Monday","Tuesday","Wednesday","Thursday ","Friday","Saturday"];
    var weekdayEnS = ["Sun.","Mon.","Tues.","Wed.","Thurs. ","Fri.","Sat."];
    var t = String(s);
    t = t.replace('yyyy', date.getFullYear());
    t = t.replace('yy', date.getYear);
    t = t.replace('MM', (date.getMonth()+1) < 10 ? "0"+(date.getMonth()+1) : (date.getMonth()+1));
    t = t.replace('M', (date.getMonth()+1));
    t = t.replace('dd', date.getDate() < 10 ? "0"+date.getDate() : date.getDate());
    t = t.replace('d', date.getDate());
    t = t.replace('HH', date.getHours() < 10 ? "0"+date.getHours() : date.getHours());
    t = t.replace('H', date.getHours());
    t = t.replace('mm', date.getMinutes() < 10 ? "0"+date.getMinutes() : date.getMinutes());
    t = t.replace('m', date.getMinutes());
    t = t.replace('ss', date.getSeconds() < 10 ? "0"+date.getSeconds() : date.getSeconds());
    t = t.replace('s', date.getSeconds());
    t = t.replace('S', date.getMilliseconds());
    t = t.replace('en:ww', weekdayEn[date.getDay()]);
    t = t.replace('en:w', weekdayEnS[date.getDay()]);
    t = t.replace('cn:ww', weekday[date.getDay()]);
    t = t.replace('cn:w', weekdayS[date.getDay()]);
    t = t.replace('ww', weekday[date.getDay()]);
    t = t.replace('w', weekdayS[date.getDay()]);
    return t;
};
