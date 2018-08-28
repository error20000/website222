var baseUrl = parent.window.baseUrl || '../';

var queryUrl = baseUrl + "api/activeconfig/findPage";
var addUrl = baseUrl + "api/activeconfig/add";
var modUrl = baseUrl + "api/activeconfig/update";
var delUrl = baseUrl + "api/activeconfig/delete";
var uploadUrl = baseUrl + "api/file/uploadImg";
var typeUrl = baseUrl + "api/activetype/findAll";


var ajaxReq = parent.window.ajaxReq || "";


var myvue = new Vue({
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
				typeOptions:[],
				uploadUrl: uploadUrl,
				//新增界面数据
				addFormVisible: false,//新增界面是否显示
				addLoading: false, //loading
				//数据
				addForm: {},
				//效验
				addFormRules: {
					pid: [
						{  required: true, message: '请输入pid', trigger: 'blur' }
					],
					type: [
						{  required: true, message: '请选择分类', trigger: 'blur' }
					],
					name: [
						{  required: true, message: '请输入名称', trigger: 'blur' }
					],
					chance: [
						{  required: true, message: '请输入概率', trigger: 'blur' },
						{ validator: (rule, value, callback) => {
					          if (value < 0 || value > 1) {
					            callback(new Error('请输入0-1之间的小数！'));
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
					pid: [
						{  required: true, message: '请输入pid', trigger: 'blur' }
					],
					type: [
						{  required: true, message: '请选择分类', trigger: 'blur' }
					],
					name: [
						{  required: true, message: '请输入名称', trigger: 'blur' }
					],
					chance: [
						{  required: true, message: '请输入概率', trigger: 'blur' },
						{ validator: (rule, value, callback) => {
					          if (value < 0 || value > 1) {
					            callback(new Error('请输入0-1之间的小数！'));
					          } else {
					            callback();
					          }
						}, trigger: 'blur' }
					]
					
				},
				//查看界面数据
				viewFormVisible: false,//编辑界面是否显示
				//数据
				viewForm: {},
				
				
				end: ''
			}
		},
		methods: {
			formatDate: function(date){
				return parent.window.formatDate(date, 'yyyy-MM-dd HH:mm:ss');
			},
			typeFormatter: function(row){
				var name = row.type;
				for (var i = 0; i < this.typeOptions.length; i++) {
					var item = this.typeOptions[i];
					if(item.value == row.type){
						name = item.label;
						break
					}
				}
				return name;
			},
			handleAddUpload: function(res){
				if(res.code > 0){
					this.addForm.pic = res.data.path;
				}else{
					this.$message({
						message: res.msg,
						type: 'warning'
					});
				}
			},
			handleEditUpload: function(res){
				if(res.code > 0){
					this.editForm.pic = res.data.path;
				}else{
					this.$message({
						message: res.msg,
						type: 'warning'
					});
				}
			},
			handleSizeChange: function (val) {
				this.rows = val;
				this.getList();
			},
			handleCurrentChange: function (val) {
				this.page = val;
				this.getList();
			},
			handleTypeOptions: function(cb){
				var self = this;
				var params = {};
				ajaxReq(typeUrl, params, function(res){
					if(res.code > 0){
						for (var i = 0; i < res.data.length; i++) {
							var item = {
									label: res.data[i].name,
									value: res.data[i].pid
							};
							self.typeOptions.push(item);
						}
						if(typeof cb == 'function'){
							cb();
						}
					}else{
						self.$message({
							message: res.msg,
							type: 'warning'
						})
					}
				});
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
						pid: '',
						type: 2,
						name: '',
						painter: '',
						cv: '',
						ship: '',
						star: 0,
						pic: '',
						desc: '',
						info: '',
						sort: 999,
						chance: '',
						count: ''
				};
			},
			//显示编辑界面
			handleEdit: function (index, row) {
				this.editFormVisible = true;
				this.editForm = Object.assign({}, row);
			},
			//显示查看界面
			handleView: function (index, row) {
				this.viewFormVisible = true;
				this.viewForm = Object.assign({}, row);
			},
			//删除
			handleDel: function (index, row) {
				this.$confirm('确认删除该记录吗？删除后不可恢复。', '提示', {
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
			this.handleTypeOptions(this.getList);
		}
	  });
	
	

