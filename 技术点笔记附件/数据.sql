#权限信息表

#左侧菜单
INSERT INTO `elec_popedom` VALUES ('am', '0', '系统管理', null, '../images/MenuIcon/xitongguanli.gif', null, '1', '1');
INSERT INTO `elec_popedom` VALUES ('an', 'am', '用户管理', '../system/elecUserAction_home.do', '../images/MenuIcon/yonghuguanli.gif', 'mainFrame', '0', '1');
INSERT INTO `elec_popedom` VALUES ('ao', 'am', '角色管理', '../system/elecRoleAction_home.do', '../images/MenuIcon/jueseguanli.gif', 'mainFrame', '0', '1');
INSERT INTO `elec_popedom` VALUES ('ap', 'am', '数据字典维护', '../system/elecSystemDDLAction_home.do', '../images/MenuIcon/shujuzidianguanli.gif', 'mainFrame', '0', '1');
INSERT INTO `elec_popedom` VALUES ('aq', 'am', '运行监控', '../system/elecCommonMsgAction_home.do', '../images/MenuIcon/daibanshiyi.gif', 'mainFrame', '0', '1');
INSERT INTO `elec_popedom` VALUES ('ar', '0', '审批流转', '', '../images/MenuIcon/shenpiliuzhuanguanli.gif', 'mainFrame', '1', '1');
INSERT INTO `elec_popedom` VALUES ('as', 'ar', '审批流程管理', '../workflow/processDefinitionList.jsp', '../images/MenuIcon/shenpiliuchengguanli.gif', 'mainFrame', '0', '1');
INSERT INTO `elec_popedom` VALUES ('at', 'ar', '申请模板管理', '../workflow/processDefinitionList.jsp', '../images/MenuIcon/shenqingmobanguanli.gif', 'mainFrame', '0', '1');
INSERT INTO `elec_popedom` VALUES ('au', 'ar', '起草申请', '../workflow/processDefinitionList.jsp', '../images/MenuIcon/qicaoshenqing.gif', 'mainFrame', '0', '1');
INSERT INTO `elec_popedom` VALUES ('av', 'ar', '待我审核', '../workflow/processDefinitionList.jsp', '../images/MenuIcon/daiwoshenpi.gif', 'mainFrame', '0', '1');
INSERT INTO `elec_popedom` VALUES ('aw', 'ar', '我的申请查询', '../workflow/processDefinitionList.jsp', '../images/MenuIcon/wodeshenqingchaxun.gif', 'mainFrame', '0', '1');

#系统登录
INSERT INTO `elec_popedom` VALUES ('ba', '0', '系统登录', null, null, null, '1', '1');
INSERT INTO `elec_popedom` VALUES ('bb', 'ba', '首页显示', '../system/elecMenuAction_menuHome.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('bc', 'ba', '标题', '../system/elecMenuAction_title.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('bd', 'ba', '菜单', '../system/elecMenuAction_left.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('be', 'ba', '加载左侧树形结构', '../system/elecMenuAction_left.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('bf', 'ba', '改变框架', '../system/elecMenuAction_change.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('bg', 'ba', '加载主显示页面', '../system/elecMenuAction_showMenu.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('bh', 'ba', '站点运行', '../system/elecMenuAction_alermStation.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('bi', 'ba', '设备运行', '../system/elecMenuAction_alermDevice.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('bj', 'ba', '重新登陆', '../system/elecMenuAction_logout.do', null, null, '0', '0');

#运行监控
INSERT INTO `elec_popedom` VALUES ('ca', '0', '运行监控', null, null, null, '1', '0');
INSERT INTO `elec_popedom` VALUES ('cb', 'ca', '保存', '/system/elecCommonMsgAction_save.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('cc', 'ca', 'ajax进度条', '/system/elecCommonMsgAction_progereeBar.do', null, null, '0', '0');

#导出设置
INSERT INTO `elec_popedom` VALUES ('da', '0', '导出设置', null, null, null, '1', '0');
INSERT INTO `elec_popedom` VALUES ('db', 'da', '导出设置设置', '/system/elecExportFieldsAction_setExp.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('dc', 'da', '导出设置保存', '/system/elecExportFieldsAction_save.do', null, null, '0', '0');

#数据字典
INSERT INTO `elec_popedom` VALUES ('ea', '0', '数据字典', null, null, null, '1', '0');
INSERT INTO `elec_popedom` VALUES ('eb', 'ea', '查看', '/system/elecSystemDDLAction_edit.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('ed', 'ea', '编辑', '/system/elecSystemDDLAction_edit.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('ed', 'ea', '保存', '/system/elecSystemDDLAction_save.do', null, null, '0', '0');

#用户管理
INSERT INTO `elec_popedom` VALUES ('fa', '0', '用户管理', '', null, null, '1', '0');
INSERT INTO `elec_popedom` VALUES ('fb', 'fa', '新增', '/system/elecUserAction_add.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('fc', 'fa', '保存', '/system/elecUserAction_save.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('fd', 'fa', '编辑', '/system/elecUserAction_edit.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('fe', 'fa', '删除', '/system/elecUserAction_delete.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('ff', 'fa', '验证登陆名', '/system/elecUserAction_checkUser.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('fg', 'fa', '导出excel', '', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('fh', 'fa', 'excel导入页面', null, null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('fi', 'fa', 'excel导入', null, null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('fj', 'fa', '人员统计', null, null, null, '0', '0');

#角色管理
INSERT INTO `elec_popedom` VALUES ('ga', '0', '角色管理', null, null, null, '1', '0');
INSERT INTO `elec_popedom` VALUES ('gb', 'ga', '编辑', '/system/elecUserAction_edit.do', null, null, '0', '0');
INSERT INTO `elec_popedom` VALUES ('gc', 'ga', '保存', '/system/elecUserAction_save.do', null, null, '0', '0');

-- ----------------------------------------