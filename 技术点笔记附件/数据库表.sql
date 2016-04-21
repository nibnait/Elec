

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
)
