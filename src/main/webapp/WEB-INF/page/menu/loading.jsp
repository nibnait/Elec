<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>     
    <title></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache"><link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet" />
<style type="text/css">
<!--
body {
	background-color: #FFFFFF;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
body,td,th {
	color: #000000;
}
-->
    </style>
<style>
BODY {SCROLLBAR-FACE-COLOR: #cccccc; SCROLLBAR-HIGHLIGHT-COLOR: #ffffFF; SCROLLBAR-SHADOW-COLOR: #ffffff; SCROLLBAR-3DLIGHT-COLOR: #cccccc; SCROLLBAR-ARROW-COLOR:  #ffffff; SCROLLBAR-TRACK-COLOR: #ffffFF; SCROLLBAR-DARKSHADOW-COLOR: #cccccc; }
</style>
<!-- 浮动窗口样式css begin -->
<style type="text/css">
	#msg_win{border:1px solid #A67901;background:#EAEAEA;width:240px;position:absolute;right:0;font-size:12px;font-family:Arial;margin:0px;display:none;overflow:hidden;z-index:99;}
	#msg_win .icos{position:absolute;top:2px;*top:0px;right:2px;z-index:9;}
	.icos a{float:left;color:#833B02;margin:1px;text-align:center;font-weight:bold;width:14px;height:22px;line-height:22px;padding:1px;text-decoration:none;font-family:webdings;}
	.icos a:hover{color:#fff;}
	#msg_title{background:#BBDEF6;border-bottom:1px solid #A67901;border-top:1px solid #FFF;border-left:1px solid #FFF;color:#000;height:25px;line-height:25px;text-indent:5px;}
	#msg_content{margin:5px;margin-right:0;width:230px;height:126px;overflow:hidden;}
</style>
<!-- 浮动窗口样式css end -->

<script language="javascript">
	function  shiftiframe(value)
	{    
	    if(value==1){
	        if(document.all.station.width==500)
			{
				document.all.station.width=1100;
				document.getElementById("devtd").style.display="none";
			}
			else if(document.all.station.width==1100)
			{
				document.all.station.width=500;
				document.getElementById("devtd").style.display="";
			}
	    }
	    else{
	        if(document.all.dev.width==500)
			{
				document.all.dev.width=1100;
				document.getElementById("stationtd").style.display="none";
			}
			else if(document.all.dev.width==1100)
			{
				document.all.dev.width=500;
				document.getElementById("stationtd").style.display="";
			}
	    }
	}
	/**添加10分钟后自动刷新页面，站点和设备运行的实时性*/
	window.onload=function(){
		setTimeout('refresh10()',1000*60*10) ;
    }
	function refresh10(){
		window.location.reload();
	}
</script>
<link href="${pageContext.request.contextPath }/css/login.css" rel="stylesheet" type="text/css">
</head>

<body>

<form name="Form1" method="post" action="name.aspx" id="Form1">

	<table width="100%" border="0" height="88" border="1" background=${pageContext.request.contextPath }/images/back1.jpg>
		<tr>
			<td colspan=3 class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif"><strong>系统首页</strong></td>
		</tr>

		<tr>
			<td width="50%" height="84" align="left" valign="top" id="stationtd">
			
			    <fieldset id="stationset" style="width: 500px; height: 430px; padding: 1 background:${pageContext.request.contextPath }/images/back1.JPG"><legend>
				    <font color="#0000FF">
				    <img border="0" src="${pageContext.request.contextPath }/images/zoom.gif" width="14" height="14"><a href="#" onclick="shiftiframe('1')">站点运行情况</a></font></legend>
					<IFRAME src="elecMenuAction_alermStation.do"  name="station" id="station" frameBorder="0" width="500" scrolling="auto" height="400"></IFRAME>	
					     
			    </fieldset>
			
			</td>
           			
			<td width="50%" align="left" valign="top" id="devtd">
				<fieldset id="devset" style="width: 500px; height: 430px; padding: 1 background:${pageContext.request.contextPath }/images/back1.JPG"><legend>
					<font color="#0000FF">
					<img border="0" src="${pageContext.request.contextPath }/images/zoom.gif" width="14" height="14"><a href="#" onclick="shiftiframe('2')">设备运行情况</a></font></legend>
	             	<IFRAME src="elecMenuAction_alermDevice.do"  name="dev" id="dev" frameBorder="0" width="500" scrolling="auto" height="400"></IFRAME>	
				</fieldset>
			</td>
		</tr>
		<tr><td height=2></td></tr>
	
	</table>

	</form>
	<!-- 浮动窗口html代码 begin -->
	<hr>
	<div id="msg_win" style="display:block;top:490px;visibility:visible;opacity:1;">
		<div class="icos">
			<a id="msg_min" title="最小化" href="javascript:void 0">_</a><a id="msg_close" title="关闭" href="javascript:void 0">×
			</a>
		</div>
		<div id="msg_title">
			设备运行情况-->
		</div>
		<div id="msg_content" style="overflow:auto;height:150px;width:100%;white-space:nowrap">
			<s:property value="devRun" escape="false"/>
			
		</div>
	</div>
	<!-- 浮动窗口html代码 end -->
	
</body>

</html>
<!-- 浮动窗口js，必须要放置到最后  begin-->
<script language="javascript">
var Message={
	set: function() {//最小化与恢复状态切换
		var set=this.minbtn.status == 1?[0,1,'block',this.char[0],'最小化']:[1,0,'none',this.char[1],'展开'];
		this.minbtn.status=set[0];
		this.win.style.borderBottomWidth=set[1];
		this.content.style.display =set[2];
		this.minbtn.innerHTML =set[3]
		this.minbtn.title = set[4];
		this.win.style.top = this.getY().top;
	},
	close: function() {//关闭
		this.win.style.display = 'none';
		window.onscroll = null;
	},
	setOpacity: function(x) {//设置透明度
		var v = x >= 100 ? '': 'Alpha(opacity=' + x + ')';
		this.win.style.visibility = x<=0?'hidden':'visible';//IE有绝对或相对定位内容不随父透明度变化的bug
		this.win.style.filter = v;
		this.win.style.opacity = x / 100;
	},
	show: function() {//渐显
		clearInterval(this.timer2);
		var me = this,fx = this.fx(0, 100, 0.1),t = 0;
		this.timer2 = setInterval(function() {
			t = fx();
			me.setOpacity(t[0]);
			if (t[1] == 0) {clearInterval(me.timer2) }
		},10);
	},
	fx: function(a, b, c) {//缓冲计算
		var cMath = Math[(a - b) > 0 ? "floor": "ceil"],c = c || 0.1;
		return function() {return [a += cMath((b - a) * c), a - b]}
	},
	getY: function() {//计算移动坐标
		var d = document,b = document.body, e = document.documentElement;
		var s = Math.max(b.scrollTop, e.scrollTop);
		var h = /BackCompat/i.test(document.compatMode)?b.clientHeight:e.clientHeight;
		var h2 = this.win.offsetHeight;
		return {foot: s + h + h2 + 2+'px',top: s + h - h2 - 2+'px'}
	},
	moveTo: function(y) {//移动动画
		clearInterval(this.timer);
		var me = this,a = parseInt(this.win.style.top)||0;
		var fx = this.fx(a, parseInt(y));
		var t = 0 ;
		this.timer = setInterval(function() {
			t = fx();
			me.win.style.top = t[0]+'px';
			if (t[1] == 0) {
				clearInterval(me.timer);
				me.bind();
			}
		},10);
	},
	bind:function (){//绑定窗口滚动条与大小变化事件
		var me=this,st,rt;
		window.onscroll = function() {
			clearTimeout(st);
			clearTimeout(me.timer2);
			me.setOpacity(0);
			st = setTimeout(function() {
			me.win.style.top = me.getY().top;
			me.show();
			},600);
		};
		window.onresize = function (){
			clearTimeout(rt);
			rt = setTimeout(function() {me.win.style.top = me.getY().top},100);
		}
	},
	init: function() {//创建HTML
		function $(id) {return document.getElementById(id)};
		this.win=$('msg_win');
		var set={minbtn: 'msg_min',closebtn: 'msg_close',title: 'msg_title',content: 'msg_content'};
		for (var Id in set) {this[Id] = $(set[Id])};
		var me = this;
		this.minbtn.onclick = function() {me.set();this.blur()};
		this.closebtn.onclick = function() {me.close()};
		this.char=navigator.userAgent.toLowerCase().indexOf('firefox')+1?['_','::','×']:['0','2','r'];//FF不支持webdings字体
		this.minbtn.innerHTML=this.char[0];
		this.closebtn.innerHTML=this.char[2];
		setTimeout(function() {//初始化最先位置
			me.win.style.display = 'block';
			me.win.style.top = me.getY().foot;
			me.moveTo(me.getY().top);
		},0);
		return this;
	}
};
Message.init();
</script>
<!-- 浮动窗口js end-->

