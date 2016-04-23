var privilegeDate = [{
    mid: 'aa',
    pid: '0',
    isParent: true,
	icon:'../images/MenuIcon/jishusheshiweihuguanli.gif',
	//open:true,
    name: '技术设施维护管理',
	nodes:[
		{
			mid:'ab',
			pid:'aa',
			isParent:false,
			target:'mainFrame',
			url:'../equapment/equapmentIndex.jsp',
			icon:'../images/MenuIcon/yiqishebeiguanli.gif',
			name:'仪器设备管理'
		},{
			mid:'ac',
			pid:'aa',
			isParent:false,
			target:'mainFrame',
			url:'../equapment/adjustIndex.jsp',
			icon:'../images/MenuIcon/shebeijiaozhunjianxiu.gif',
			name:'设备校准检修'
		},{
			mid:'ad',
			pid:'aa',
			isParent:false,
			target:'mainFrame',
			url:'../equapment/planIndex.jsp',
			icon:'../images/MenuIcon/shebeigouzhijihua.gif',
			name:'设备购置计划'
		}
	]
}, {
    mid: 'ae',
    pid: '0',
    name: '技术资料图纸管理',
	icon:'../images/MenuIcon/jishuziliaotuzhiguanli.gif',
    isParent: true,
	nodes:[
		{
			mid:'af',
			pid:'ae',
			isParent:false,
			icon:'../images/MenuIcon/ziliaotuzhiguanli.gif',
			target:'mainFrame',
			url:'../dataChart/dataChartIndex.jsp',
			name:'资料图纸管理'
		}
	]
}, 
{
    mid: 'ag',
    pid: '0',
    name: '站点设备运行管理',
	icon:'../images/MenuIcon/zhuandianshebeiyunxingguanli.gif',
    isParent: true,
	nodes:[
		{
			mid:'ah',
			pid:'ag',
			isParent: false,
			icon:'../images/MenuIcon/zhandianjibenxinxi.gif',
			target:'mainFrame',
			url:'../siteEquapment/siteInfoIndex.jsp',
			name:'站点基本信息'
		},{
			mid:'ai',
			pid:'ag',
			isParent: false,
			icon:'../images/MenuIcon/yunxingqingkuang.gif',
			target:'mainFrame',
			url:'../siteEquapment/siteRunIndex.jsp',
			name:'运行情况'
		},{
			mid:'aj',
			pid:'ag',
			isParent: false,
			icon:'../images/MenuIcon/weihuqingkuang.gif',
			target:'mainFrame',
			url:'../siteEquapment/siteMaintainIndex.jsp',
			name:'维护情况'
		}
	]
}, {
    mid: 'ak',
    pid: '0',
    name: '监测台建筑管理',
	icon:'../images/MenuIcon/jiancetaijianzhuguanli.gif',
    isParent: true,
	nodes:[
		{
			mid:'al',
			pid:'ak',
			isParent: false,
			icon:'../images/MenuIcon/jiancetaijianzhu.gif',
			target:'mainFrame',
			url:'../building/buildingIndex.jsp',
			name:'检测台建筑管理'
		}
	]
},
 {
    mid: 'am',
    pid: '0',
    name: '系统管理',
	icon:'../images/MenuIcon/xitongguanli.gif',
    isParent: true,
	nodes:[
		{
			mid:'an',
			pid:'am',
			name:'用户管理',
			icon:'../images/MenuIcon/yonghuguanli.gif',
			target:'mainFrame',
			url:'../system/elecUserAction_home.do',
			isParent:false
		},{
			mid:'ao',
			pid:'am',
			name:'角色管理',
			icon:'../images/MenuIcon/jueseguanli.gif',
			target:'mainFrame',
			url:'../system/elecRoleAction_home.do',
			isParent:false
		},{
			mid:'ap',
			pid:'am',
			name:'运行监控',
			icon:'../images/MenuIcon/daibanshiyi.gif',
			target:'mainFrame',
			url:'../system/elecCommonMsgAction_home.do',
			isParent:false
		},{
			mid:'aq',
			pid:'am',
			name:'数据字典维护',
			icon:'../images/MenuIcon/shujuzidianguanli.gif',
			target:'mainFrame',
			url:'../system/elecSystemDDLAction_home.do',
			isParent:false
		}
	]
}, {
    mid: 'ar',
    pid: '0',
    name: '审批流转',
	icon:'../images/MenuIcon/shenpiliuzhuanguanli.gif',
    isParent: true,
	nodes:[
		{
			mid:'as',
			pid:'ar',
			name:'审批流程管理',
			target:'mainFrame',
			icon:'../images/MenuIcon/shenpiliuchengguanli.gif',
			url:'../workflow/processDefinitionList.jsp',
			isParent:false
		},{
			mid:'at',
			pid:'ar',
			name:'申请模板管理',
			target:'mainFrame',
			url:'../workflow/applicationTemplateList.jsp',
			icon:'../images/MenuIcon/shenqingmobanguanli.gif',
			isParent:false
		},{
			mid:'au',
			pid:'ar',
			name:'起草申请',
			target:'mainFrame',
			url:'../workflow/flowTemplateList.jsp',
			icon:'../images/MenuIcon/qicaoshenqing.gif',
			isParent:false
		},{
			mid:'av',
			pid:'ar',
			name:'待我审批',
			target:'mainFrame',
			url:'../workflow/flowMyTaskList.jsp',
			icon:'../images/MenuIcon/daiwoshenpi.gif',
			isParent:false
		},{
			mid:'aw',
			pid:'ar',
			name:'我的申请查询',
			target:'mainFrame',
			url:'../workflow/flowMyApplicationList.jsp',
			icon:'../images/MenuIcon/wodeshenqingchaxun.gif',
			isParent:false
		}
	]
}];
