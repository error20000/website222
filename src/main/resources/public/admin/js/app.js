var baseUrl = parent.window.baseUrl || '../';

var queryUrl = baseUrl + "api/app/findPage";
var addUrl = baseUrl + "api/app/add";
var modUrl = baseUrl + "api/app/update";
var delUrl = baseUrl + "api/app/delete";

var ajaxReq = parent.window.ajaxReq || "";


	new Vue({
	    el: '#app',
	    data: function(){
	    	return {
				filters: {
					name: ''
				},
				list: [],
				total: 0,
				page: 1,
				rows: 10,
				listLoading: false,
				sels: [],//列表选中列
				//新增界面数据
				addFormVisible: false,//新增界面是否显示
				addLoading: false, //loading
				//数据
				addForm: {},
				//效验
				addFormRules: {
					appId: [
						{ required: true, message: '请输入appId', trigger: 'blur' }
					],
					captchaType: [
						{ validator: (rule, value, callback) => {
							console.log(String(value).replace(/[0-9]/g, "") != '');
					          if (String(value).replace(/[0-9]/g, "") != '' || value < 0) {
					            callback(new Error('请输入数字，默认值：9'));
					          } else {
					            callback();
					          }
						}, trigger: 'blur' }
					],
					disturbLevel: [
						{ validator: (rule, value, callback) => {
							console.log(String(value).replace(/[0-9]/g, "") != '');
					          if (String(value).replace(/[0-9]/g, "") != '' || value < 0) {
					            callback(new Error('请输入数字，默认值：1'));
					          } else {
					            callback();
					          }
						}, trigger: 'blur' }
					]
				},
				//编辑界面数据
				editFormVisible: false,//编辑界面是否显示
				editLoading: false, //loading
				//数据
				editForm: {},
				//效验
				editFormRules: {
					appId: [
						{ required: true, message: '请输入appId', trigger: 'blur' }
					],
					captchaType: [
						{ validator: (rule, value, callback) => {
							console.log(String(value).replace(/[0-9]/g, "") != '');
					          if (String(value).replace(/[0-9]/g, "") != '' || value < 0) {
					            callback(new Error('请输入数字，默认值：9'));
					          } else {
					            callback();
					          }
						}, trigger: 'blur' }
					],
					disturbLevel: [
						{ validator: (rule, value, callback) => {
							console.log(String(value).replace(/[0-9]/g, "") != '');
					          if (String(value).replace(/[0-9]/g, "") != '' || value < 0) {
					            callback(new Error('请输入数字，默认值：1'));
					          } else {
					            callback();
					          }
						}, trigger: 'blur' }
					]
				},
				//查看界面数据
				viewFormVisible: false,//编辑界面是否显示
				viewLoading: false, //loading
				//数据
				viewForm: {},
				
				
				end: ''
				
			}
		},
		methods: {
			formatDate: function(date){
				return parent.window.formatDate(date, 'yyyy-MM-dd HH:mm:ss');
			},
			handleSizeChange: function (val) {
				this.rows = val;
				this.getList();
			},
			handleCurrentChange: function (val) {
				this.page = val;
				this.getList();
			},
			//查询
			getList: function () {
				var self = this;
				var params = {
					page: this.page,
					rows: this.rows
				};
				this.listLoading = true;
				ajaxReq(queryUrl, params, function(res){
					self.listLoading = false;
					if(res.code > 0){
						self.total = res.total;
						self.list = res.data;
					}else{
						self.$message({
							message: res.msg,
							type: 'warning'
						})
					}
				});
			},
			//显示新增界面
			handleAdd: function () {
				this.addFormVisible = true;
				this.addForm = {
						name: '',
						appId: '',
						captchaType: 9,
						disturbLevel: 1,
						isHttps: 0,
						clientType: 2,
						status: 0
				};
			},
			//显示查看界面
			handleView: function (index, row) {
				this.viewFormVisible = true;
				this.viewForm = Object.assign({}, row);
			},
			//显示编辑界面
			handleEdit: function (index, row) {
				this.editFormVisible = true;
				this.editForm = Object.assign({}, row);
			},
			//删除
			handleDel: function (index, row) {
				this.$confirm('确认删除该记录吗？', '提示', {
					type: 'warning'
				}).then(() => {
					var self = this;
					this.listLoading = true;
					ajaxReq(delUrl, {pid: row.pid }, function(res){
						self.listLoading = false;
						if(res.code > 0){
							self.$message({
								message: '删除成功',
								type: 'success'
							});
							self.getList();
						}else{
							self.$message({
								message: res.msg,
								type: 'warning'
							})
						}
					});
					
				}).catch(() => {
				});
			},
			//新增
			addClose: function () {
				this.addFormVisible = false;
				this.addLoading = false;
				this.$refs.addForm.resetFields();
			},
			addSubmit: function () {
				this.$refs.addForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							var params = Object.assign({}, this.addForm);
							var self = this;
							this.addLoading = true;
							ajaxReq(addUrl, params, function(res){
								self.addLoading = false;
								if(res.code > 0){
									self.$message({
										message: '新增成功',
										type: 'success'
									});
									self.addFormVisible = false;
									self.getList();
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
			//编辑
			editClose: function () {
				this.editFormVisible = false;
				this.editLoading = false;
				this.$refs.editForm.resetFields();
			},
			editSubmit: function () {
				this.$refs.editForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							var self = this;
							this.editLoading = true;
							var params = Object.assign({}, this.editForm);
							ajaxReq(modUrl, params, function(res){
								self.editLoading = false;
								if(res.code > 0){
									self.$message({
										message: '提交成功',
										type: 'success'
									});
									self.editFormVisible = false;
									self.getList();
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
			selsChange: function (sels) {
				this.sels = sels;
			}
		},
		mounted: function() {
			this.getList();
		}
	  });
	
	

