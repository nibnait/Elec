

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