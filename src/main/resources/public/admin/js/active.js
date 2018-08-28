var baseUrl = parent.window.baseUrl || '../';

var queryUrl = baseUrl + "api/active/findPage";
var addUrl = baseUrl + "api/active/add";
var modUrl = baseUrl + "api/active/update";
var delUrl = baseUrl + "api/active/delete";
var excelUrl = baseUrl + "api/active/excel";
var configUrl = baseUrl + "api/activeconfig/findAll";

var ajaxReq = parent.window.ajaxReq || "";


var myvue = new Vue({
	    el: '#app',
	    data: function(){
	    	return {
				filters: {
					start: '',
					end: '',
					phone: '',
					config: ''
				},
				list: [],
				total: 0,
				page: 1,
				rows: 10,
				listLoading: false,
				sels: [],//列表选中列
				configOptions:[],
				//新增界面数据
				addFormVisible: false,//新增界面是否显示
				addLoading: false, //loading
				//数据
				addForm: {},
				//效验
				addFormRules: {
					
				},
				//编辑界面数据
				editFormVisible: false,//编辑界面是否显示
				editLoading: false, //loading
				//数据
				editForm: {},
				//效验
				editFormRules: {
					
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
			configFormatter: function(row){
				var name = row.config;
				for (var i = 0; i < this.configOptions.length; i++) {
					var item = this.configOptions[i];
					if(item.value == row.config){
						name = item.label;
						break
					}
				}
				return name;
			},
			handleSizeChange: function (val) {
				this.rows = val;
				this.getList();
			},
			handleCurrentChange: function (val) {
				this.page = val;
				this.getList();
			},
			handleFilters: function(){
				this.filters={
					start: '',
					end: '',
					phone: '',
					config: ''
				};
				this.getList();
			},
			handleConfigOptions: function(cb){
				var self = this;
				var params = {};
				ajaxReq(configUrl, params, function(res){
					if(res.code > 0){
						for (var i = 0; i < res.data.length; i++) {
							var item = {
									label: res.data[i].name,
									value: res.data[i].pid
							};
							self.configOptions.push(item);
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
				if(this.filters.start){
					params.startDate = this.filters.start;
				}
				if(this.filters.end){
					params.endDate = this.filters.end;
				}
				if(this.filters.phone){
					params.phone = this.filters.phone;
				}
				if(this.filters.config){
					params.config = this.filters.config;
				}
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
			//导出
			handleExcel: function () {
				var self = this;
				var params = {};
				if(this.filters.start){
					params.startDate = this.filters.start;
				}
				if(this.filters.end){
					params.endDate = this.filters.end;
				}
				if(this.filters.phone){
					params.phone = this.filters.phone;
				}
				if(this.filters.config){
					params.config = this.filters.config;
				}
				var str = "";
				for ( var key in params) {
					str += key + "=" + params[key];
				}
				window.location.href = excelUrl + "?" + str;
				/*ajaxReq(excelUrl, params, function(res){
					if(res.code > 0){
						self.total = res.total;
						self.list = res.data;
					}else{
						self.$message({
							message: res.msg,
							type: 'warning'
						})
					}
				});*/
			},
			//显示新增界面
			handleAdd: function () {
				this.addFormVisible = true;
				this.addForm = {
						
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
			this.handleConfigOptions(this.getList);
		}
	  });
	
	

