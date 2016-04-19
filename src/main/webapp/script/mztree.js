
String.prototype.getParam = function(name) 
{
	var reg = new RegExp("(^|;|\\s)"+name+"\\s*:\\s*([^;]*)(\\s|;|$)", "i");
	var str = this.replace(/(;[^:]+)(;|$)/g, function(a,b,c){return b.replace(/;/g, "\x0f")+c;});
	var r = str.match(reg); 
	if (r!=null) 
	return unescape(r[2].replace(/[\x0f]/g, ";")); 
	return "";
}

function getObjectById(id)
{
	if (typeof(id) != "string" || id == "") return null;
	if (document.all) return document.all(id);
	if (document.getElementById) return document.getElementById(id);
	try {return eval(id);} catch(e){ return null;}
}


function MzTreeView(Tname)
{
	if(typeof(Tname) != "string" || Tname == "")
		throw(new Error(-1, 'no Class Name'));
  
	this.divider  = "_";
	this.url      = "#";
	this.target   = "_self";
	this.name     = Tname;
	this.index    = 0;
	this.nodes    = {};
	this.nodeFilePath = "";
	this.currentNode = null;

	//var highLight = "#0A246A";
	//var highLightText   = "#FFFFFF";
	//var mouseOverBgColor= "#D4D0C8";
	var highLight = "#FFFFFF";
	var highLightText   = "#003399";
	var mouseOverBgColor= "#FFFFFF";
 
	this.createHTML = function(node, AtEnd)
	{
		var data = node.data; 
		var id   = node.id; 
		var url =  node.url; 
		if(!url) url = this.url;
		if(data) url += (url.indexOf("\?")==-1?"\?":"&") + data;
		var HCN  = node.hasChild, isRoot = node.parentId=="0";
		if(isRoot && node.icon=="") node.icon = "root";
		if(node.icon=="") node.icon = HCN ? "folder" : "file";
			node.iconExpand  = HCN ? AtEnd ? "PM2" : "PM1" : AtEnd ? "L2" : "L1";
		if(node.id!="0" && !isRoot)  node.childAppend = node.parentNode.childAppend +
			"<IMG border='0' align='absmiddle' src='"+this.icons[(AtEnd?"empty":"L4")].src+"'>";

		var nodeHTML = "<DIV noWrap><SPAN onmouseover='this.style.backgroundColor=\""+
			mouseOverBgColor +"\"' onmouseout='this.style.backgroundColor=\"\"'><NOBR>"+
			(isRoot ? "" : node.parentNode.childAppend +
			"<IMG border='0' align='absmiddle' id='"+ Tname +"_expand_"+ id +"' "+
			(HCN ?"onclick=\""+ Tname +".expand('"+ id +"')\" " : "") +
			"src='"+ this.icons[node.iconExpand].src +"' style='cursor: hand'>")+
			"<SPAN oncontextmenu='return "+ Tname +".popupmenu(\""+ id +"\")' "
			// + " onclick=\""+ Tname +".focus('"+ id +"', true)\" " + (HCN ? " ondblclick=\""+ Tname +".expand('"+ id +"')\"" : "") 
			+ (HCN ? " onclick =\""+ Tname +".expand('"+ id +"', true);"+ Tname +".focus('"+ id +"', true);\"" : " onclick =\""+ Tname +".focus('"+ id +"', true);\" ") 
			+">"+
			"<IMG border='0' align='absmiddle' id='"+ Tname +"_icon_"+ id +"' "+
			"src='"+ this.icons[node.icon].src +"'>"+
			"<A class='MzTreeview' id='"+ Tname +"_link_"+ id +"' ";
	    nodeHTML +=	" target='"+ this.target + "' ";
        nodeHTML +=	" href='"+ url + "' ";
		nodeHTML +=	"title='"+ node.hint +"' onclick='"+
			"return "+ Tname +".click(\""+ id +"\");'>"+ node.text +"</A></NOBR>"+
			"</SPAN></SPAN></DIV>\r\n"; if(isRoot && node.text=="") nodeHTML = "";
			nodeHTML += "<SPAN id='"+ Tname +"_tree_"+ id +"' style='DISPLAY: none'></SPAN>";
		return nodeHTML;
	};
	
	var _d = "\x0f";
	this.node = {};
	this.node["0"] =
	{
		"id": "0",
		"path": "0",
		"isLoad": false,
		"childNodes": new Array(),
		"childAppend": "",
		"sourceIndex": "0",
		"childNodesFileName" : ""
	};

    this.XMLHttpLoadFile = function(url,id) 
    {
        var httpRequest;
        if (typeof XMLHttpRequest != 'undefined') {
            httpRequest = new XMLHttpRequest();
        }
        else if (typeof ActiveXObject != 'undefined') {
            httpRequest = new ActiveXObject('Microsoft.XMLHTTP');
        }
        if (httpRequest) {
            httpRequest.open('GET', url, false);
            httpRequest.send(null);
            if(httpRequest.status == 200)
            {
                var textInfo = httpRequest.responseText;
                eval(textInfo);
            }
        }
    }

	this.load = function(id,sign)
	{
		var me  = this, node = this.node[id];
        if(sign && (id > 0))
        {
			setTimeout(me.name +".load('"+ id +"',false)", 1);
			getObjectById(me.name +"_tree_"+ id).innerHTML = node.childAppend +
			"<IMG border='0' align='absmiddle' src='"+this.icons["L2"].src+"'>"+
			"<IMG border='0' align='absmiddle' src='"+this.icons["file"].src+"'>"+
			"<span style='background-Color: "+ highLight +"; color: "+ highLightText +
			"; font-size: 9pt'>"+ unescape("%u52A0%u8F7D%u4E2D") +"...</span>";
			return;
        }
        
		if(!node.isLoad)
		{
		    if((node.childNodesFileName)&&(node.childNodesFileName != "undefined")&&(node.childNodesFileName.length > 0))
		    {
		        this.XMLHttpLoadFile(node.childNodesFileName,id);
		        node.isLoad = true;
		    }
		}

		var sid = node.sourceIndex.substr(node.sourceIndex.indexOf(this.divider) + this.divider.length);
		var str = "(^|"+ _d +")"+ sid + this.divider +"[^"+ _d + this.divider +"]+("+ _d +"|$)";
		var reg = new RegExp(str, "g"), cns = this.names.match(reg), tcn = this.node[id].childNodes;
		if (cns){ 
			reg = new RegExp(_d, "g"); 
			for(var i=0; i<cns.length; i++) 
			{
			    var tmp = cns[i].replace(reg, "");
			    tcn[tcn.length] = this.nodeInit(tmp, id);
			}
		}
		node.isLoad = true; if(node.id=="0") return;
		this.drawNode(id);
	};

	this.nodeInit = function(sourceIndex, parentId)
	{
		this.index++;
		var param = this.nodes[sourceIndex];
		var hint  = param.getParam("hint");
		var text  = param.getParam("text");
		var childNodesFileNameValue = param.getParam("childNodesFileName");
		var sid   = sourceIndex.substr(sourceIndex.indexOf(this.divider) + this.divider.length);
		
		this.node[this.index] = {
			"id"    : this.index,
			"text"  : text,
			"hint"  : hint ? hint : text,
			"icon"  : param.getParam("icon"),
			"path"  : this.node[parentId].path + this.divider + this.index,
			"isLoad": false,
			"isExpand": false,
			"parentId": parentId,
			"parentNode": this.node[parentId],
			"sourceIndex" : sourceIndex,
			"childAppend" : "",
			"childNodesFileName" : childNodesFileNameValue ? (this.nodeFilePath + childNodesFileNameValue) : "",
			"url"   : param.getParam("url"),
			"data"   : param.getParam("data")
			
		};
		if(childNodesFileNameValue)
		{
		    this.node[this.index].hasChild = true;
		}
		else
		{
		    this.node[this.index].hasChild = this.names.indexOf(_d + sid + this.divider)>-1;
		}
		if(this.node[this.index].hasChild)  this.node[this.index].childNodes = new Array();
		this.nodes[sourceIndex] = this.index;
		return this.node[this.index];
	};

	this.drawNode = function(id)
	{
		var tcn     = this.node[id].childNodes, str = "";
		for (var i=0; i<tcn.length; i++) str += this.createHTML(tcn[i], i==tcn.length-1);
		getObjectById(Tname +"_tree_"+ id).innerHTML = str;
	};

	this.getPath= function(id)
	{
		var A = new Array(); A[0] = id, pid=id;
		while(id!="0" && id!="")
		{
			var str = "(^|"+_d+")([^"+_d+ this.divider +"]+"+ this.divider + id +")("+_d+"|$)";
			var ids = this.names.match(new RegExp(str, "g"));
			if(ids)
			{
				id = ids[0].replace(_d, "").split(this.divider)[0];
				A[A.length] = id;
			} else break;
		}
		return A.reverse();
	};

	this.focus      = function(id, onlySimpleFocus)
	{
		if(!this.currentNode) this.currentNode=this.node["0"]; if(!onlySimpleFocus){
		if(typeof(this.node[id])=="undefined") var apid = this.getPath(id);
		else var apid = this.node[id].path.split(this.divider);
		for(var i=0; i<apid.length-1; i++) this.expand(apid[i], true);}

		var a = getObjectById(Tname +"_link_"+ id); if (a) { a.focus();// alert("focus "+ id);
		var link = getObjectById(Tname +"_link_"+ this.currentNode.id);
		if(link)with(link.style){color="";   backgroundColor="";}
		with(a.style){color = highLightText; backgroundColor = highLight;}
		this.currentNode= this.node[id];}
	};
  
	this.expand   = function(id, sureExpand)
	{
		var node  = this.node[id];
		if (sureExpand && node.isExpand) return;
		var area    = getObjectById(Tname +"_tree_"+ id);
		if (area)
		{
		    var icon  = this.icons[node.icon];
			var iconE = this.iconsExpand[node.icon];
			var exp   = this.icons[node.iconExpand];
			var expE  = this.iconsExpand[node.iconExpand];
			var Bool  = node.isExpand = sureExpand || area.style.display == "none";

			var img   = getObjectById(Tname +"_icon_"+ id);
			if (img)  img.src = !Bool ? icon.src :typeof(iconE)=="undefined" ? icon.src : iconE.src;
			
			var img   = getObjectById(Tname +"_expand_"+ id);
			if (img)  img.src = !Bool ? exp.src : typeof(expE) =="undefined" ? exp.src  : expE.src;
			
			if(!Bool && this.currentNode.path.indexOf(node.path)==0)
			{
				this.focus(id, true); 
				this.click(id);
			}
			area.style.display = Bool ? "block" : "none";
			if(!node.isLoad) 
			{
			    this.load(id,true);
			}
		}
	};
	
  
	this.toString = function()
	{
		var a = new Array(); for (id in this.nodes) a[a.length] = id;
		this.names = a.join(_d + _d); this.load("0",true);
		var rootCN = this.node["0"].childNodes;
		var str = "<A id='"+ Tname +"_RootLink' href='#' style='DISPLAY: none'></A>";
		if(rootCN.length>0)
		{
			for(var i=0; i<rootCN.length; i++) str += this.createHTML(rootCN[i], i==rootCN.length-1);
			setTimeout(Tname +".expand('"+ rootCN[0].id +"', true); "+ 
				Tname +".focus('"+ rootCN[0].id +"'); "+ Tname +".atRootIsEmpty()",10);
		}
		return str;
	};
}

MzTreeView.prototype.setIconPath  = function(path)
{
	this.icons    = {
		L0        : 'L0.gif',  //???
		L1        : 'L1.gif',  //???
		L2        : 'L2.gif',  //???
		L3        : 'L3.gif',  //???
		L4        : 'L4.gif',  //???
		PM0       : 'P0.gif',  //??????
		PM1       : 'P1.gif',  //??????
		PM2       : 'P2.gif',  //??????
		PM3       : 'P3.gif',  //??????
		empty     : 'L5.gif',     //blank
	//	root      : 'root.gif',   // root
	//	folder    : 'folder.gif', // folder 
	//	file      : 'file.gif',   // file 
	
		root      : 'root.gif',   // root
		folder    : 'folder.gif', // folder 
		file      : 'file.gif',   // file 

		event     : 'event.gif',
		object    : 'object.gif',
		behavior  : 'behavior.gif',
		property  : 'property.gif',
		method    : 'method.gif',
		collection: 'collection.gif',

	    exit      : 'exit.gif'
	};

  this.iconsExpand = { 
    PM0       : 'M0.gif',     //??????
    PM1       : 'M1.gif',     //??????
    PM2       : 'M2.gif',     //??????
    PM3       : 'M3.gif',     //??????
   // folder    : 'folderopen.gif',
   folder    : 'folderopen.gif',

    exit      : 'exit.gif'
  };
  for(var i in this.icons)
  {
    var tmp = this.icons[i];
    this.icons[i] = new Image();
    this.icons[i].src = path + tmp;
  }
  for(var i in this.iconsExpand)
  {
    var tmp = this.iconsExpand[i];
    this.iconsExpand[i]=new Image();
    this.iconsExpand[i].src = path + tmp;
  }
}

MzTreeView.prototype.atRootIsEmpty = function()
{
  var RCN = this.node["0"].childNodes;
  for(var i=0; i<RCN.length; i++)
  {
    if(RCN[i].text=="")
    {
      var node = RCN[i].childNodes[0], HCN  = node.hasChild;
      node.iconExpand  =  RCN[i].childNodes.length>1 ? HCN ? "PM0" : "L0" : HCN ? "PM3" : "L3"
      getObjectById(this.name +"_expand_"+ node.id).src = this.icons[node.iconExpand].src;
    }
  }
};

MzTreeView.prototype.popupmenu  = function(id)
{
  try
  {
    if(this.popupMenu)
    {
      if(id)
      {
        this.popupMenu.data = this.node[id].data;
        this.popupMenu.treeviewitem = this.node[id];
      }
      for(var i=0; i<this.popupMenu.items.length; i++)
      {
        if(this.popupMenu.items[i].bind != "")
        this.popupMenu.items[i].bind = Tname +"_tree_"+ Tname;
      }
      this.popupMenu.show(window.event, window.event.srcElement);
      return false;
    }
    else return true;
  }catch(e){}
};

MzTreeView.prototype.click    = function(id)
{
  if( (this.node[id].url == this.url) || ((this.node[id].url == "")&&(this.node[id].data == ""))) return false; 
  return true;
};
