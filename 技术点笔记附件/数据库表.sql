

#运行监控
create table Elec_CommonMsg(
	comID varchar(50) not null primary key,		#主键
	stationRun varchar(5000) null,		#站点运行情况
	devRun varchar(5000) null,		#设备运行情况
	createDate datetime null		#创建日期
);

create table Elec_CommonMsg_Content(
	comID varchar(50) not null primary key,		#主键
	type char(2) null,		# 站点 1; 设备 2
	content varchar(5000) null,		#数据内容
	orderby int null		#数据显示排序
);


#数据字典 
create table Elec_SystemDDL(
	seqID int not null primary key,		#主键ID(自增长)
	keyword varchar(20) null,		#数据类型
	ddlCode int null,		#数据项的code
	ddlName varchar(50) null		#数据项的value
);

#用户表
create table Elec_User(
	userID varchar(50) not null primary key, 	#主键
	jctID varchar(50) null,	#所属单位code
	jctUnitID varchar(50) null,	#所属单位的单位名称
	userName varchar(50) null,	#用户姓名
	logonName varchar(50) null,	#登录名
	logonPwd varchar(50) null,	#密码
	sexID varchar(50) null,	#性别
	birthday datetime null, #出生日期
	address varchar(100) null, #联系地址
	contactTel varchar(50) null, #联系电话
	email varchar(100) null,	 #电子邮箱
	mobile varchar(50) null,	#手机
	isDuty varchar(50) null,	#是否在职
	postID varchar(50) null,	#职位（主要用于工作流审核）
	onDutyDate datetime null,	#入职时间
	offDutyDate datetime null	#离职时间	
	#idDelete int null	#是否删除 （假删除）
);

#用户职称附件表
create table Elec_User_File(
	fileID varchar(50) not null primary key,	#主键
	userID varchar(50) null,	#用户ID
	fileName varchar(50) null,	#文件名
	fileURL varchar(1000) null,	#文件路径
	progressTime timestamp null,	#上传时间
	constraint foreign key(userID) references Elec_User(userID)
);


#角色信息表
create table Elec_Role(
	roleID varchar(32) not null primary key, #角色ID
	roleName varchar(500)		#角色名称
);

INSERT INTO `elec_role` VALUES ('1', '系统管理员');
INSERT INTO `elec_role` VALUES ('2', '高级管理员');
INSERT INTO `elec_role` VALUES ('3', '中级管理员');
INSERT INTO `elec_role` VALUES ('4', '业务用户');
INSERT INTO `elec_role` VALUES ('5', '一般用户');
INSERT INTO `elec_role` VALUES ('6', '普通用户');
-- ----------------------------


#权限信息表
create table Elec_Popedom(
	mid varchar(32) not null, 		#权限Code(主键ID)
	pid varchar(32) not null,		#父级权限Code，如果已经的根节点则为0
	name varchar(32) null,			#权限名称
	url varchar(5000) null,			#权限在系统中执行的URL
	icon varchar(5000) null,		#如果是菜单，则为显示图片的URL
	target varchar(5000) null,		#如果是菜单,链接执行的Frame区域名称
	isParent boolean null,			#是否是父节点，父节点为true，子节点为false
	isMenu boolean null,			#是否是系统菜单结构	
	primary key(mid,pid)		#设置联合主键
);


#用户_角色 关联表
#create table Elec_User_Role(
#	userID varchar(32) not null,	#用户ID
#	roleID varchar(32) not null,	#权限角色ID
#	primary key(userID,roleID)		#设置联合逐渐
#);

#角色_权限 关联表
create table Elec_Role_Popedom(
	roleID varchar(32) not null,	#权限角色ID
	mid varchar(32) not null,		#权限Code(主键ID)
	pid varchar(32) not null,		#父级权限code,如果已经是根节点则为0
	primary key(roleID,mid,pid)		#设置联合主键
);


#导出设置表
create table Elec_ExportFields(
	belongTo varchar(10) not null primary key,	#所属模块（自然主键），如 用户管理为：5-1
	expNameList varchar(5000) null,		#导出字段的中文名
	expFieldName varchar(5000) null, 	#导出字段的英文名
	noexpNameList varchar(5000) null,	#未导出字段的中文名
	noexpFieldName varchar(5000) null 	#未导出字段的英文名
);

#资料图书管理
create table Elec_FileUpload(
	SeqID int not null primary key,		#主键
	ProjID varchar(50) null,			#工程ID/所属单位
	BelongTo varchar(50) null,			#所属模块/图纸类别
	FileName varchar(50) null,			#文件名
	FileURL varchar(1000) null,			#文件路径
	ProgressTime varchar(20) null,		#上传时间
	comment varchar(500) null,			#文件描述
#	IsDelete varchar(10) null,			#是否删除
);




