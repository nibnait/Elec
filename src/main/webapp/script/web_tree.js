/**************************************************************
web_tree.js 
All Rights Reserved.
***************************************************************/
this.onerror = function (e){alert(e);return true;}

var style_0 = 'TD.Tree_ParentNode_1{}';
var style_1 = 'TD.Tree_ParentNode_2{}';
var style_2 = 'TD.Tree_ParentNode_3{}';
var style_3 = 'TD.Tree_ChildNode_1{}';
var style_4 = 'TD.Tree_ChildNode_2{}';
var style_5 = 'TD.Tree_ChildNode_3{}';

document.write('<style>'+style_0+'</style>');
document.write('<style>'+style_1+'</style>');
document.write('<style>'+style_2+'</style>');
document.write('<style>'+style_3+'</style>');
document.write('<style>'+style_4+'</style>');
document.write('<style>'+style_5+'</style>');
//*******************************************************\\
var Tree_isNC6 = (document.getElementById && !document.all)?true:false;
var Tree_isIE = (document.all)?true:false;
if(Tree_isNC6==false&&Tree_isIE==false)	alert("IE 5.0 (above) or Netscape Navigator 6(above) is required.");
//-------------------------------
var TREE_CHILD=-1,	TREE_LAST=-2,	TREE_ROOT=-3,	TREE_FIRST=-4,	TREE_SIBLING=-5,	TREE_PREV=-6,
	TREE_NEXT=-7,	TREE_CONST_BEGIN_FOLDER=-1,	TREE_CONST_FOLDER=0,TREE_CONST_END_FOLDER=1,
	TREE_CONST_FILE=2,	TREE_LINK=3,	TREE_SCRIPT=4,	TREE_CONST_END=5;

var HT_LABEL = 1,HT_IMG = 2,HT_BUTTON = 3,HT_CHECKBOX = 4;

var ENUM_SHOW_IMAGE = 1,ENUM_SHOW_TOOLTIP = 2,	ENUM_SHOW_STATUS = 4,ENUM_HIGHLIGHT_SELECT = 8,ENUM_SHOW_LINE = 16,
	ENUM_SHOW_CHECKBOX = 32,ENUM_USETEXTASSTATUS = 64,	ENUM_USETEXTASTIP = 128,ENUM_LINES_AT_ROOT = 256;

var ENUM_ACTION_LABEL =1,	ENUM_ACTION_IMG =2,	ENUM_ACTION_BUTTON = 4,
	ENUM_EXPAND_LABEL =8,	ENUM_EXPAND_IMG =16,ENUM_EXPAND_BUTTON = 32,ENUM_ENABLE_DRAG_DROP = 64,
	ENUM_LOAD_LABEL=128,ENUM_LOAD_IMG=256,ENUM_LOAD_BUTTON=512;
//----------------
if(typeof(this.Tree_View_Index)=='undefined')this.Tree_View_Index=0;
if(typeof(this.Tree_Node_Index)=='undefined')this.Tree_Node_Index=0;
if(typeof(this.Tree_View_Array)=='undefined')this.Tree_View_Array=new Array;
if(typeof(this.Tree_Node_Array)=='undefined')this.Tree_Node_Array=new Array;
var Tree_View_Index=this.Tree_View_Index;
var Tree_Node_Index=this.Tree_Node_Index;
var Tree_View_Array=this.Tree_View_Array;
var Tree_Node_Array=this.Tree_Node_Array;

String.prototype.trim = function()
{
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

function get_obj(id){return document.getElementById(id);}

function Tree_Action(link,target,script,treeViewIndex)
{
	this.link=link;	this.target=target;	this.script=script;	this.treeViewIndex = treeViewIndex;
	var t=new String(script);	t=t.trim();	this.type=(t.length>0)?TREE_SCRIPT:TREE_LINK;
}
//***************************************************************\\
function Utility(){};
Utility.prototype.add_property = function (obj,property,value)
{
	eval("if(typeof(obj."+property+")=='undefined') obj."+property+"="+value);
}
Utility.prototype.remove_property = function (obj,property)
{
	eval("delete obj."+property);
}
Utility.prototype.add_event = function (tree,event_name,cb_function)
{
	var obj = get_obj('Tree_View_'+tree.id);
	if(typeof(tree.event_array)!='object')tree.event_array=new Object;
	Utility.add_property(tree.event_array,event_name,'new Object()');
	eval("var tmp=tree.event_array."+event_name);
	Utility.add_property(tmp,cb_function,cb_function);
	if(Tree_isNC6 && obj) {
		obj.removeEventListener(event_name,Tree_OnEvent,false);
		return obj.addEventListener(event_name,Tree_OnEvent,false);
	}
	if(Tree_isIE && obj) {
		obj.detachEvent("on"+event_name,Tree_OnEvent);
		return obj.attachEvent("on"+event_name,Tree_OnEvent);
	}
}
Utility.prototype.detach_event=function (tree,event_name,cb_function)
{
	var obj = get_obj('Tree_View_'+tree.id);
	if(typeof(tree.event_array)=='object')
	{
		eval("var tmp=tree.event_array."+ event_name);
		Utility.remove_property(tmp,cb_function);
	}
}
var Utility=new Utility();
//********************************Tree_ImgList definition*******************************\\
function Tree_ImgList()
{
	this.add = function add(key,src){
		Utility.add_property(this,'A_'+key,'new Image()');
		eval("this.A_"+key+".src=src;");
	}
	this.remove=function remove(key){
		Utility.remove_property(this,'A_'+key);
	}
	this.item=function item(key){
		try{return eval("this.A_"+key+".src");}
		catch(e){return "";}
	}
}
//********************************Tree_Node definition*******************************\\
function Tree_Node(text,img_normal,img_expanded,tip_text,status,action)
{
	this.next=null;
	this.prev=null;
	this.parent=null;
	this.first_child=null;
	this.last_child=null;
	this.child_count=0;

	this.img_normal=(typeof(img_normal)=='undefined'?"":img_normal);
	this.img_expanded=(typeof(img_expanded)=='undefined'?"":img_expanded);
	this.text=(typeof(text)=='undefined'?"":text);
	this.tip_text=(typeof(tip_text)=='undefined'?"":tip_text);
	this.status=(typeof(status)=='undefined'?"":status);

	this.id=Tree_Node_Index;
	Tree_Node_Array[Tree_Node_Index++]=this;
	this.action=action;
	this.is_expanded=false;
	this.is_selected=false;
	this.is_checked=false;
}
//***************************************************************\\
Tree_Node.prototype.set_text=function(text)
{
	this.text=text;
	var tree=this.get_tree();
	var obj=this.get_label_obj();
	if(obj)	obj.innerHTML=(Tree_FormatText(this.text));
}
Tree_Node.prototype.get_label_obj = function ()
{
	return get_obj('Tree_label_'+this.id);
}

Tree_Node.prototype.get_img_obj = function ()
{
	return get_obj('Tree_img_'+this.id);
}

Tree_Node.prototype.get_checkbox_obj = function ()
{
	return get_obj('Tree_checkbox_'+this.id);
}

Tree_Node.prototype.get_button_obj = function ()
{
	return get_obj('Tree_button_'+this.id);
}

Tree_Node.prototype.get_child = function (index)
{
	var child = this.first_child;
	var i=0;
	while(child && i < index){
		child = child.next;i++;
	}
	return child;
}

Tree_Node.prototype.expand = function (mode)
{
	mode = typeof(mode)=='undefined'?'auto':mode;
	var td=get_obj('Tree_expand_'+this.id);
	var tree = this.get_tree();
	if(!this.is_expanded && typeof(tree.cb_expanding)=='function')
		if(false==tree.cb_expanding(this))
			return;//cancel expand
	if(this.is_expanded && typeof(tree.cb_collapsing)=='function')
		if(false==tree.cb_collapsing(this))
			return;//cancel collapse
	if(this.child_count == 0) this.is_expanded=false;
	else if(mode == 'true' || mode==true)this.is_expanded=true;
	else if(mode == 'false'||mode==false)this.is_expanded=false;
	else if(mode == 'auto')this.is_expanded=!this.is_expanded;
	else return;
	if(td)td.style.display=this.is_expanded?"block":"none";
	var img=this.get_img_obj();
	if(img)	img.src=Tree_imgSrc(this);
	var button=this.get_button_obj();
	if(button)button.src=Tree_GetButtonImg(this);
	if(this.is_expanded && typeof(tree.cb_expanded)=='function')	tree.cb_expanded(this);
	if(!this.is_expanded && typeof(tree.cb_collapsed)=='function')	tree.cb_collapsed(this);
}

Tree_Node.prototype.check=function (mode)
{
	mode = typeof(mode)=='undefined'?'auto':mode;
	if(mode == 'true'||mode==true)this.is_checked=true;
	else if(mode == 'false'||mode==false)this.is_checked=false;
	else if(mode == 'auto')this.is_checked=!this.is_checked;
	else return;
	var obj=this.get_checkbox_obj();
	if(obj)obj.checked=this.is_checked;
}

Tree_Node.prototype.select=function ()
{
	var tree=this.get_tree();
	if(tree.selected_node && tree.selected_node != this)tree.selected_node.unselect();
	this.is_selected = true;
	tree.selected_node = this;
	var label = this.get_label_obj();
	if(label && tree.style & ENUM_HIGHLIGHT_SELECT){
		label.className=this.child_count>0?tree.parentnode_class_selected:tree.childnode_class_selected;
	}
}

Tree_Node.prototype.unselect=function ()
{
	var tree=this.get_tree();
	this.is_selected = false;
	if(tree.selected_node == this)tree.selected_node =null;
	var label = this.get_label_obj();
	if(label && tree.style & ENUM_HIGHLIGHT_SELECT){
		label.className=this.child_count>0?tree.parentnode_class_normal:tree.childnode_class_normal;
	}
}
Tree_Node.prototype.on_click=function (hittest,event)
{
   
    
	var tree=this.get_tree();
	if(hittest==HT_BUTTON){
		if(tree.behavior & ENUM_EXPAND_BUTTON) this.expand('auto');
		if(tree.behavior & ENUM_ACTION_BUTTON) this.do_action();
		//if(this.__need_load && (tree.behavior & ENUM_LOAD_BUTTON))	tree.helper_dynamic_load(this);
		if (tree.behavior & ENUM_LOAD_BUTTON)	tree.helper_dynamic_load(this);
	}
	if(hittest==HT_LABEL){
		this.select();
		
		if(tree.behavior & ENUM_EXPAND_LABEL) 
		{
		
		this.expand('auto');
		}
		if(tree.behavior & ENUM_ACTION_LABEL){ 
		
		this.do_action();
		}
		if(this.__need_load && (tree.behavior & ENUM_LOAD_LABEL) )	tree.helper_dynamic_load(this);
	
	}
	if(hittest==HT_IMG){
		this.select();
		if(tree.behavior & ENUM_EXPAND_IMG) this.expand('auto');
		if(tree.behavior & ENUM_ACTION_IMG) this.do_action();
		if(this.__need_load && (tree.behavior & ENUM_LOAD_IMG) )tree.helper_dynamic_load(this);
		
	}
	if(hittest==HT_CHECKBOX){
		this.check('auto');
	}
}
Tree_Node.prototype.on_mousehover=function (hittest,event)
{
	var tree=this.get_tree();
	if(hittest == HT_LABEL)
	{
		var label = this.get_label_obj(); if(label==null)return;
		if(Tree_isIE)label.style.cursor='hand';	if(Tree_isNC6)label.style.cursor='pointer';
		if(this.child_count>0)		label.className=tree.parentnode_class_hover;
		else	label.className=tree.childnode_class_hover;
		if(tree.style & ENUM_SHOW_STATUS)
		{
			window.status=((tree.style & ENUM_USETEXTASSTATUS==0) ? this.status:(this.status!=""?this.status:this.text));
		}
		label.title=((tree.style &ENUM_SHOW_TOOLTIP) ==0 ? "":	(tree.style & ENUM_USETEXTASTIP ==0 ? this.tip_text:(this.tip_text==""?this.text:this.tip_text)));
	}
	if(hittest == HT_BUTTON){
		var button=this.get_button_obj();if(button==null)return;
		if(this.child_count == 0 && button)button.style.cursor='default';
		if(this.child_count>0 && button ){
			if(Tree_isIE)button.style.cursor='hand';
			if(Tree_isNC6)button.style.cursor='pointer';
		}
	}
	if(hittest == HT_IMG){
		var img = this.get_img_obj();if(img==null)return;
		if(Tree_isIE)img.style.cursor='hand';
		if(Tree_isNC6)img.style.cursor='pointer';
	}
}

Tree_Node.prototype.on_mouseout=function (hittest,event)
{
	var tree=this.get_tree();
	if(hittest == HT_LABEL)
	{
		var label = this.get_label_obj();	if(label===null)return;
		if((tree.style & ENUM_HIGHLIGHT_SELECT) && this==tree.selected_node)
			label.className = this.child_count>0?tree.parentnode_class_selected:tree.childnode_class_selected;
		else if(this.child_count>0)	label.className=tree.parentnode_class_normal;
		else label.className=tree.childnode_class_normal;
	}
}

Tree_Node.prototype.cb__add_child = function (child_node)
{
	var tree = this.get_tree();
	if(tree.__auto_draw == false)return;
	var img = this.get_img_obj();
	if(img)img.src=Tree_imgSrc(this);
	var button=this.get_button_obj();
	if(button)button.src=Tree_GetButtonImg(this);
	var parent=get_obj("Tree_expand_"+ this.id);
	if(parent==null)parent=get_obj("Tree_View_"+ tree.id);
	var div=document.createElement("DIV");
	div.innerHTML=Tree_table(child_node)+"<DIV id=Tree_expand_"+ child_node.id+
				" STYLE='{display:"+(child_node.is_expanded?"block;":"none;")+"}'></DIV>";
	var before=null;
	if(this.child_count==1);
	else if(this.last_child == child_node){
		var node=child_node.prev;
		button=node.get_button_obj();
		if(button){
			button.src=Tree_GetButtonImg(node);
			var line=document.getElementsByName("Tree_tdline_"+ node.id);
			for(var i=line.length-1;i>=0;i--)
				line[i].innerHTML="<IMG  src="+tree.img_list.item('line_I') + ">";
		}
	}
	else {
		button=child_node.next.get_button_obj();
		if(button)button.src=Tree_GetButtonImg(child_node.next);
		before=get_obj("Tree_expand_"+child_node.next.id).parentNode;
	}
	parent.insertBefore(div,before);
	child_node.unselect();
	if(this.is_selected)this.select();else this.unselect();
}

Tree_Node.prototype.cb__del_child = function (child_node)
{
	var tree = this.get_tree();
	if(tree.__auto_draw == false)return;
	if(tree.selected_node && child_node.contain(tree.selected_node))tree.selected_node=null;
	var div=get_obj("Tree_expand_"+child_node.id).parentNode;
	div.parentNode.removeChild(div);
	if(child_node.__is_first==true)
	{
		var img = this.get_img_obj();
		if(img) img.src=Tree_imgSrc(this);
	}
	else if(child_node.__is_last==true){
		var node=child_node.prev;
		var line=document.getElementsByName("Tree_tdline_"+ node.id);
		for(var i=line.length-1;i>=0;i--)
			line[i].innerHTML="<IMG style='width:"+tree.LINE_WIDTH+";height:"+tree.LINE_HEIGHT+";visibility:hidden'>";
		var button=get_obj("Tree_button_"+node.id);
		if(button)	button.src=Tree_GetButtonImg(node);
	}
	if(this.child_count==0){
		var button=get_obj("Tree_button_"+ this.id);
		if(button)	button.src=Tree_GetButtonImg(this);
		var expand = get_obj("Tree_expand_"+this.id);if(expand)expand.style.display="none";
		this.is_expanded=false;
	}
	if(child_node.next){
		var button=get_obj("Tree_button_"+child_node.next.id);
		if(button)	button.src=Tree_GetButtonImg(child_node.next);
	}
}

Tree_Node.prototype.add_child=function (index,text,img1,img2,tip_text,status)
{
	var action=new Tree_Action("","","",this.action.treeViewIndex);
	var node=new Tree_Node(text,img1,img2,tip_text,status,action);
	this.__add_child(node,index);
	return node;
}

Tree_Node.prototype.contain = function (test_node)
{
	if(test_node==null || this.get_tree().id != test_node.get_tree().id)return false;
	var i=test_node.get_level();
	var node = test_node;
	while(i>=1){
		if(node.id==this.id) return true;
		node=node.parent;
		i--;
	}
	return false;
}

Tree_Node.prototype.del_child = function (index)
{
	this.__del_child(index);
}

Tree_Node.prototype.get_index = function ()
{
	if(this.parent == null)return -1;
	var index=0;
	var node = this.parent.first_child;
	while(node.id!=this.id){
		node=node.next;index++;
	}
	return index;
}

Tree_Node.prototype.del = function ()
{
	if(this.parent){
		var tree=this.get_tree();
		this.parent.del_child(this.get_index());
	}
}

Tree_Node.prototype.add_sibling=function (index,text,img1,img2,tip_text, status)
{
	if(this.parent==null)return null;
	var n=0;
	var node=this.parent.first_child;
	while(node.id != this.id && node!=null)
	{
		n++;
		node=node.next;
	}
	if(index==TREE_PREV) index=n;
	if(index==TREE_NEXT) index=n+1;
	return this.parent.add_child(index,text,img1,img2,tip_text,status);
}

Tree_Node.prototype.set_link=function (link,target)
{
	if(this.parent==null) return;
	this.action.type=TREE_LINK;
	this.action.target=target;
	this.action.link=link;
	this.action.script="";
}

Tree_Node.prototype.set_script=function (script)
{
	this.action.type=TREE_SCRIPT;
	this.action.script=script;
	this.action.target="";
	this.action.link="";
}

Tree_Node.prototype.get_tree = function ()
{
	return Tree_View_Array[this.action.treeViewIndex];
}

Tree_Node.prototype.__add_child = function (child,index)
{
	var n=0;
	if(index==TREE_FIRST)
		index=0;
	if(index==TREE_LAST)
		index=this.child_count;
	if(index<0)
		index=0;
	if(index>this.child_count)
		index=this.child_count;
	var p1 = p2 = this.first_child;
	while(n<index && p1!=null){
		n++;
		p2=p1;
		p1=p1.next;
	}
	if(p1 == this.first_child )
	{
		if(this.first_child == null)
		{
			this.first_child = this.last_child = child;
			this.first_child.next = this.first_child.prev = null;
			this.last_child.next = this.last_child.prev = null;
		}
		else
		{
			this.first_child = child; this.first_child.prev = null;
			this.first_child.next = p2; p2.prev = this.first_child;
		}
	}
	else if(p2 == this.last_child)
	{
		this.last_child = child; this.last_child.next= null;
		this.last_child.prev = p2; p2.next = child;
	}
	else
	{
		p2.next = child; child.prev = p2;
		child.next = p1; p1.prev = child;
	}
	child.parent = this;
	this.child_count++;
	this.cb__add_child(child);
}

Tree_Node.prototype.__del_child = function (index)
{
	if(this.child_count <=0 || index>this.child_count-1 ) return;
	var p,temp;
	var i=0;
	p=this.first_child;
	while(i<index&&p!=null&&p.next!=null)
	{
		i++;p=p.next;
	}
	if(p==this.first_child)
	{
		if(this.last_child==this.first_child)
		{
			this.last_child=this.first_child=null;
		}
		else
		{
			this.first_child=this.first_child.next;
			this.first_child.prev=null;
		}
		p.__is_first=true;
	}
	else if(p==this.last_child)
	{
		this.last_child=this.last_child.prev;
		this.last_child.next=null;
		p.__is_last=true;
	}
	else{
		temp=p.prev;temp.next=p.next;
		temp=p.next;temp.prev=p.prev;
	}
	this.child_count--;
	this.cb__del_child(p);
	Tree_Node_Array[p.id]=null;
}

Tree_Node.prototype.get_level=function ()
{
	if(this.parent==null) return -1;
	var tmp=this;var n=0;
	while(tmp && tmp.parent)	{
		n++;
		tmp=tmp.parent;
	}
	return n;
}

Tree_Node.prototype.get_parent_ex=function (level)
{
	var p=this;var n=this.get_level();
	if(n<1)return null;
	while(p && level!=n){
		p=p.parent;n--;
	}
	return p;
}

Tree_Node.prototype.getHtml=function ()
{
	var html="";
	if(this.parent)
	{
		var tree=this.get_tree();
		html+="<DIV>";
		html+=Tree_table(this);
		html+="<DIV id=Tree_expand_"+this.id+" STYLE='{display:"+(this.is_expanded?"block;":"none;")+"}'>";
	}
	if(this.child_count>0) {
		for(var node=this.first_child;node;node=node.next)
		{
			html+=node.getHtml();
		}
	}
	if(this.parent)html+="</DIV></DIV>";
	return html;
}
Tree_Node.prototype.refresh=function ()
{
	var obj=get_obj("Tree_expand_"+this.id);
	var html="";
	if(obj && this.parent){
		var tree=this.get_tree();
		html+=Tree_table(this);
		html+="<DIV id=Tree_expand_"+this.id+" STYLE='{display:"+(this.is_expanded?"block;":"none;")+"}'>";
		if(this.child_count>0) {
			for(var node=this.first_child;node;node=node.next)
			{
				html+=node.getHtml();
			}
		}
		html+="</DIV>";
		obj.parentNode.innerHTML=html;
		if(this.is_selected)this.select();else this.unselect();
	}
}
Tree_Node.prototype.__refresh=function ()
{
	if(this.parent)return;
	var div=get_obj("Tree_View_"+this.get_tree().id);
	div.innerHTML=this.getHtml();
	var tree_id = this.get_tree().id;
	for(var i=(Tree_Node_Array.length)-1;i>=0 ;i--)
	{
		var n=Tree_Node_Array[i];
		if(n&&n.action.treeViewIndex==tree_id)
		{
			if(n.is_selected)n.select();else n.unselect();
		}
	}
}

Tree_Node.prototype.do_action = function()//!!!!!!!!!!!!!
{
	var action = this.action;
	if(action.type==TREE_LINK)//link
	{
		var url=action.link;
		var target = action.target;
		if(target == null || target=="")//default target
		{
		    alert("null or ==")
			target = Tree_View_Array[action.treeViewIndex].target;
			}
		if(url!="")	{
		//alert("open");
		//window.open(url,"");
	//var tar= document.parentWindow.frames[0];
//	alert(tar);
	var s =window.open(url,target);
	//s.location.reload(true);
	
		//alert(target);
//submitAction(target,url);
         

		}
	}
	else if(action.type==TREE_SCRIPT){//run script
		eval(action.script);
	}
}
function __helper_move(node)
{
	var tree=node.get_tree();
	if((tree.style & ENUM_SHOW_LINE)==0)return;
	if((tree.style & ENUM_LINES_AT_ROOT) == 0 && node.get_level()==1)return;
	var obj=node.get_button_obj();	if(obj)obj.src=Tree_GetButtonImg(node);
	var parent = node.parent;
	var line=document.getElementsByName("Tree_tdline_"+ node.id);
	var html;
	if(parent && node==parent.last_child)
		html="<IMG style='height:"+tree.LINE_HEIGHT+";width:"+tree.LINE_WIDTH+";visibility:hidden'>";
	else html="<IMG  src="+tree.img_list.item('line_I')+">";
	if(line){
		for(var i=line.length-1;i>=0;i--)
		{
			line[i].innerHTML = html;
		}
	}
}
Tree_Node.prototype.move_up=function()
{
	var node = this;
	if(node && node.prev){
		if(node.prev==node.parent.first_child)node.parent.first_child=node;
		if(node==node.parent.last_child)node.parent.last_child=node.prev;
		var p3 = node;		var p2=node.prev;		var p4=node.next;		var p1=null;
		if(p2)	p1=p2.prev;		if(p1)	p1.next=p3;		if(p4)	p4.prev=p2;
		p3.prev=p1;		p3.next=p2;		p2.prev=p3;		p2.next=p4;
		if(Tree_isIE){
			get_obj("Tree_expand_"+node.id).swapNode(get_obj("Tree_expand_"+node.next.id));
			get_obj("Tree_table_"+node.id).swapNode(get_obj("Tree_table_"+node.next.id));
		}
		if(Tree_isNC6){
			__NC_swapNode(get_obj("Tree_expand_"+node.id),get_obj("Tree_expand_"+node.next.id));
			__NC_swapNode(get_obj("Tree_table_"+node.id),get_obj("Tree_table_"+node.next.id));
		}
		__helper_move(node);__helper_move(node.next);
		return true;
	}
	return false;
}
function __NC_swapNode(src,des)
{
	var obj_1 = src.cloneNode(true);
	var obj_2 = des.cloneNode(true);
	src.parentNode.replaceChild(obj_2,src);
	des.parentNode.replaceChild(obj_1,des);
}
Tree_Node.prototype.move_down=function()
{
	var node = this;
	if(node && node.next){
		if(node.next==node.parent.last_child)node.parent.last_child=node;
		if(node==node.parent.first_child)node.parent.first_child=node.next;
		var p2=node;		var p1=node.prev;		var p3=node.next;		var p4=null;
		if(p3)p4=p3.next;		if(p1)p1.next=p3;		if(p4)p4.pre=p2;
		p2.next=p4;		p2.prev=p3;		p3.next=p2;		p3.prev=p1;
		if(Tree_isIE){
			get_obj("Tree_expand_"+node.id).swapNode(get_obj("Tree_expand_"+node.prev.id));
			get_obj("Tree_table_"+node.id).swapNode(get_obj("Tree_table_"+node.prev.id));
		}
		if(Tree_isNC6){
			__NC_swapNode(get_obj("Tree_expand_"+node.id),get_obj("Tree_expand_"+node.prev.id));
			__NC_swapNode(get_obj("Tree_table_"+node.id),get_obj("Tree_table_"+node.prev.id));
		}
		__helper_move(node.prev); __helper_move(node);
		return true;
	}
	return false;
}

Tree_Node.prototype.copy_attr=function(src_node)
{
	this.text=src_node.text;
	this.img_normal=src_node.img_normal;
	this.img_expanded=src_node.img_expanded;
	this.tip_text=src_node.tip_text;
	this.status=src_node.status;
	this.is_checked=src_node.is_checked;
	this.action.type=src_node.action.type;
	this.action.link=src_node.action.link;
	this.action.target=src_node.action.target;
	this.action.script=src_node.action.script;
}

Tree_Node.prototype.deep_copy=function(src_node,is_copy_attr)
{
	if(is_copy_attr==true)this.copy_attr(src_node);
	var node=src_node.first_child;
	while(node!=null){
		var a=this.add_child(TREE_LAST,node.text,node.img_normal);
		a.deep_copy(node,is_copy_attr);
		node=node.next;
	}
}
//***************************************************************\\
function Tree_View(img_list)
{
	this.id=Tree_View_Index++;
	Tree_View_Array[this.id]=this;

	this.style =   ENUM_SHOW_IMAGE | ENUM_SHOW_TOOLTIP | ENUM_SHOW_STATUS | ENUM_LINES_AT_ROOT
					| ENUM_SHOW_LINE | ENUM_HIGHLIGHT_SELECT | ENUM_USETEXTASSTATUS	| ENUM_USETEXTASTIP;
	this.parentnode_class_normal=this.parentnode_class_hover=this.parentnode_class_selected=this.childnode_class_normal=this.childnode_class_hover=this.childnode_class_selected="";
	this.target="_blank";
	this.selected_node = null;
	this.indent = "16px";
	this.LINE_HEIGHT = "14px";
	this.LINE_WIDTH='19px';
	this.behavior = ENUM_ACTION_LABEL | ENUM_ACTION_IMG | ENUM_ACTION_BUTTON
					   | ENUM_EXPAND_LABEL | ENUM_EXPAND_IMG | ENUM_EXPAND_BUTTON | ENUM_ENABLE_DRAG_DROP;

	this.img_list = ((typeof(img_list)=='undefined')?new Tree_ImgList():img_list);
	with(this.img_list){//img_list can be shared
		if(item('default_child')=='')	add('default_child','img/img_child.gif');
		if(item('default_parent_normal')=='')	add('default_parent_normal','img/img_parent_normal.gif');	
		if(item('default_parent_expanded')=='')	add('default_parent_expanded','img/img_parent_expanded.gif');

		if(item('line_I')=='')	add('line_I','img/tree_I.gif');
		if(item('line_L')=='')	add('line_L','img/tree_L.gif');
		if(item('line_Lminus')=='')	add('line_Lminus','img/tree_Lminus.gif');
		if(item('line_Lplus')=='')	add('line_Lplus','img/tree_Lplus.gif');
		if(item('line_T')=='')	add('line_T','img/tree_T.gif');
		if(item('line_Tminus')=='')	add('line_Tminus','img/tree_Tminus.gif');
		if(item('line_Tplus')=='')	add('line_Tplus','img/tree_Tplus.gif');
		if(item('line_Tminus_root')=='')add('line_Tminus_root','img/tree_Tminus_root.gif');
		if(item('line_Tplus_root')=='')	add('line_Tplus_root','img/tree_Tplus_root.gif');
		if(item('line_Lminus_root')=='')add('line_Lminus_root','img/tree_Lminus_root.gif');
		if(item('line_Lplus_root')=='')	add('line_Lplus_root','img/tree_Lplus_root.gif');
		if(item('line_line')=='')add('line_line','img/tree_line.gif');
		if(item('line_L2')=='')add('line_L2','img/tree_L2.gif');
	}

	if(document.body==null)document.write('<body></body>');
	document.write("<DIV id = 'Tree_View_"+this.id+"' ondragstart='return false' onselectstart='return false'/></DIV>");
	document.write("<iframe style='{display:none}' id='Tree_Frame_" + this.id +"'></iframe>");
	this.__container=new Tree_Node("","","","","",new Tree_Action(0,0,0,this.id));
	this.__container.is_expanded=true;
	this.add_default_event('click');
	this.add_default_event('contextmenu');
	this.add_default_event('mouseover');
	this.add_default_event('mouseout');
	//this.add_event("mousedown","Tree_On_DragDown");
	//this.add_event("mouseup","Tree_On_DragUp");
}


Tree_View.prototype.get_style_obj= function (){
	return get_obj("Tree_View_"+this.id).style;
}

Tree_View.prototype.load_doc = function (doc)
{
	var fr = get_obj('Tree_Frame_'+this.id);
	var xml_parent_node=doc.getElementsByTagName('node');
	var tree_parent_node = Tree_Node_Array[this.__loading_id];if(tree_parent_node.__need_load==false)return;
	for(var i=0;i<xml_parent_node.length;i++)
	{
		var node = xml_parent_node[i];
		var text = node.getAttribute('text');if(text==null)text="";
		var img1 = node.getAttribute('img_normal');if(img1==null)img1="";
		if(img1!=""){
			this.img_list.add('dynamic_img_A'+Tree_Node_Index,img1);
			img1='dynamic_img_A'+Tree_Node_Index;
		}
		var img2 = node.getAttribute('img_expanded');if(img2==null)img2="";
		if(img2!=""){
			this.img_list.add('dynamic_img_B'+Tree_Node_Index,img2);
			img2='dynamic_img_B'+Tree_Node_Index;
		}
		var tip_text = node.getAttribute('tip_text');if(tip_text==null)tip_text="";
		var status = node.getAttribute('status');if(status==null)status="";
		var link = node.getAttribute('link');if(link==null)link="";
		var target = node.getAttribute('target');if(target==null)target="";
		var script = node.getAttribute('script');if(script==null)script="";
		var tag=node.getAttribute('tag');if(tag==null)tag="";
		var tmp = tree_parent_node.add_child(TREE_LAST,text,img1,img2,tip_text,status);
		tmp.tag=tag;
		if(link.length>0)tmp.set_link(link,target);
		if(script.length>0)tmp.set_script(script);
		//-------------
		var attrs = node.attributes;
		for(var t=0 ; t < attrs.length; t++)
		{
			var att = attrs[t];
			if(att.specified && att.nodeName!= 'img_normal' && att.nodeName!= 'img_expanded' && att.nodeName!= 'tip_text' && att.nodeName!= 'status' && att.nodeName!= 'link' 
					&& att.nodeName!= 'target' && att.nodeName!= 'script' && att.nodeName!= 'tag' ) 
			{
				try{
				eval("tmp." + att.nodeName + " = att.nodeValue;" );
				}catch(e){}
			}
		}
		//-------------
		if(node.getAttribute('has_child') == 'true'){
			tmp.__need_load = true;
			tmp = tmp.add_child(TREE_LAST,"loading....");
			tmp.__need_del = true;
		}
	}
	tree_parent_node.__need_load = false;
	if(tree_parent_node.first_child && tree_parent_node.first_child.__need_del)
		tree_parent_node.del_child(0);
}
//*******************************************************\\
Tree_View.prototype.dynamic_load = function(server_url)
{
	this.__server_url = server_url;
	this.helper_dynamic_load(null);
}

Tree_View.prototype.helper_dynamic_load = function(node)
{
	var url=this.__server_url;
	if(node==null) url += "?tag=null";
	else url += "?tag=" + node.tag ;
	this.__loading_id=(node==null?this.__container.id:node.id);
	//////////////////////////////////////////////
	get_obj('Tree_Frame_'+this.id).src  = url;
}

Tree_View.prototype.add_event = function (event_name,fun_name)
{
	return Utility.add_event(this,event_name,fun_name);
}
Tree_View.prototype.detach_event = function (event_name,fun_name)
{
	return Utility.detach_event(this,event_name,fun_name);
}

Tree_View.prototype.add_default_event = function (event_name)
{
	if(event_name=='click') this.add_event('click','Tree_On_ClickNode');
	if(event_name=='mouseover') this.add_event('mouseover','Tree_On_MouseHover');
	if(event_name=='mouseover') this.add_event('mouseout','Tree_On_MouseOut');
}

Tree_View.prototype.del_default_event = function (event_name){
	if(event_name=='click') this.detach_event('click','Tree_On_ClickNode');
	if(event_name=='mouseover') this.detach_event('mouseover','Tree_On_MouseHover');
	if(event_name=='mouseover') this.detach_event('mouseout','Tree_On_MouseOut');
}

Tree_View.prototype.helper_expand_all = function (node,mode)
{
	if(node){
		node.expand(mode);
		var child=node.first_child;
		while(child){
			this.helper_expand_all(child,mode);
			child=child.next;
		}
	}
}

Tree_View.prototype.expand_all = function (mode)
{
	if(typeof(mode)!='boolean')return;
	var node = this.get_root();
	while(node){
		this.helper_expand_all(node,mode);
		node=node.next;
	}
}

Tree_View.prototype.get_root = function ()
{
	var node;
	try{
		node = this.__container.first_child;
	}catch(e){node = null;}
	return node;
}
//--------------
Tree_View.prototype.get_node = function (node_id)
{
	var obj=null;
	try{
		obj=Tree_Node_Array[node_id];
		if(obj&&obj.get_tree()==this)
			return obj;
		return null;
	}
	catch(e){return null;}
}

Tree_View.prototype.add_root = function (nIndex,text,img1,img2,tip_text,status)
{
	return this.__container.add_child(nIndex,text,img1,img2,tip_text,status);
}

Tree_View.prototype.add_sibling = function (relate_id,nIndex,text,img1,img2,tip_text,status)
{
	if(relate_id==this.__container.id) return null;
	var node=this.get_node(relate_id);
	if(node==null) return null;
	return node.add_sibling(nIndex,text,img1,img2,tip_text,status);
}

Tree_View.prototype.add_child = function (parent_id,nIndex,text,img1,img2,tip_text,status)
{
	var parent_node=this.get_node(parent_id);
	if(parent_node==null) return null;
	return parent_node.add_child(nIndex,text,img1,img2,tip_text,status);
}

Tree_View.prototype.refresh=function ()
{
	this.__container.__refresh();
}

//*******************************************************\\
function Tree_OnEvent()
{
	var event = arguments[0];
	event.cancel = function cancel(){
		if(Tree_isNC6)event.preventDefault();
		if(Tree_isIE)event.returnValue=false;
	}
	var element;
	if(Tree_isNC6) event.srcElement = event.target;
	element=event.srcElement;
	var node=null,tree=null;
	node= Tree_Node_Array[element.id.replace(/(Tree_[a-z]+_)([0-9]+)/g,"$2")];
	if(null==node){
		tree = Tree_View_Array[element.id.replace(/(Tree_[a-z]+_)([0-9]+)/ig,"$2")];
		if(tree==null){
			for(var i=0;i<Tree_View_Array.length;i++)
			{
				var tmp=get_obj('Tree_View_'+i);
				if(Tree_isIE && tmp.contains(element))
				{
					tree=Tree_View_Array[i];
					break;
				}
				if(Tree_isNC6)
				{
					var tmp_2=element.parentNode;
					while(tmp_2){
						if(tmp.id==tmp_2.id);
						{
							tree=Tree_View_Array[i];
							break;
						}
						tmp_2=tmp_2.parentNode;
					}
				}
			}
			if(tree==null)return;
		}
	}
	else tree = node.get_tree();
	var hittest=null;
	if(-1 != element.id.search('Tree_label')) hittest = HT_LABEL;
	else if(-1 != element.id.search('Tree_img')) hittest = HT_IMG;
	else if(-1 != element.id.search('Tree_button')) hittest = HT_BUTTON;
	else if(-1 != element.id.search('Tree_checkbox')) hittest = HT_CHECKBOX;
	var fun = tree.event_array;
	var key;
	for(key in fun[event.type])
	{
		try{
			if(event.type.search('key')!=-1){
				eval(key+"(tree,tree.selected_node,hittest,event);");
			}
			else eval(key+"(tree,node,hittest,event);");
		}
		catch(e) {alert('runtime error\r\n' + e.description+"\r\nevent:"+event.type);}
	}
}
//*******************************************************\\
function Tree_imgSrc(node)
{
	var tree=node.get_tree();
	var src1=(node.img_normal.length>0)?tree.img_list.item(node.img_normal):tree.img_list.item("default_parent_normal");
	var src2=(node.img_expanded.length>0)?tree.img_list.item(node.img_expanded):tree.img_list.item("default_parent_expanded");
	var src3=(node.img_normal.length>0)?tree.img_list.item(node.img_normal):tree.img_list.item("default_child");
	return ((node.child_count>0)?(((node.is_expanded)?src2:src1)):src3);
}
//-------------------------------------------
function Tree_img(node)
{
	var src=Tree_imgSrc(node);
	if( (node.get_tree().style & ENUM_SHOW_IMAGE) && src.length > 0 )
		return ("<IMG SRC=\"" + src + "\" id='Tree_img_" + node.id+"'></IMG>");
	else return "";
}
//---------------------
function Tree_GetButtonImg(node)
{
	var tree=node.get_tree();
	var img_list = tree.img_list;
	if((tree.style & ENUM_SHOW_LINE) ==0||node.parent==null) return "";
	if(node == tree.get_root() && tree.__container.child_count==1 && node.child_count==0)
		return img_list.item("line_line");
	if(node.child_count>0){
		if(node.is_expanded)
			return ((node==node.parent.last_child)?((node==tree.get_root())?img_list.item("line_Lminus_root"):img_list.item("line_Lminus")):((node==tree.get_root())?img_list.item("line_Tminus_root"):img_list.item("line_Tminus")));
		else
			return ((node==node.parent.last_child)?((node==tree.get_root())?img_list.item("line_Lplus_root"):img_list.item("line_Lplus")):((node==tree.get_root())?img_list.item("line_Tplus_root"):img_list.item("line_Tplus")));
	}
	else return ((node==node.parent.last_child)?img_list.item("line_L"):((node==tree.get_root())?img_list.item("line_L2"):img_list.item("line_T")));
}
//-------------
function Tree_table(node)
{
	var tree=node.get_tree();
	var html="<TABLE border=0 cellspacing=0  cellpadding=0 id='Tree_table_" + node.id + "'><TBODY><TR>";
	var level=node.get_level();
	var count=1;
	if(tree.style & ENUM_SHOW_LINE){
		if((tree.style & ENUM_LINES_AT_ROOT) == 0 )count=2;
		while(count<level){
			var parent=node.get_parent_ex(count++);
			if( parent && parent==parent.parent.last_child)
				html+="<TD NAME=Tree_tdline_"+parent.id+" id=Tree_tdline_"+parent.id+"><IMG style='height:"+tree.LINE_HEIGHT+";width:"+tree.LINE_WIDTH+";visibility:hidden'></TD>";
			else html+="<TD NAME=Tree_tdline_"+parent.id+" id=Tree_tdline_"+parent.id+"><IMG  src="+tree.img_list.item('line_I')+"></TD>";
		}
		if((tree.style & ENUM_LINES_AT_ROOT) == 0  && node.get_level()==1){}
		else	html+="<TD><IMG id=Tree_button_"+node.id+" src="+Tree_GetButtonImg(node)+"></TD>";
	}
	else {
		while(count++<level){
			html+="<TD><IMG style='height:0;width:"+tree.indent+";visibility:hidden'></TD>";
		}
	}
	html+="<TD>"+Tree_img(node)+"</TD>";
	html+=Tree_Label(node)+"</TR></TBODY></TABLE>";
	return html;
}
//-------------------------------------------
function Tree_Label(node)
{
	var tree=node.get_tree();
	var ret = "<TD valign=center NOWRAP id='Tree_label_"+node.id+"'class=\"";
	if(node.child_count>0)
		ret += (node.is_selected?tree.parentnode_class_selected:tree.parentnode_class_normal)+"\">";
	else
		ret += (node.is_selected?tree.childnode_class_selected:tree.childnode_class_normal)+"\">";
	if(tree.style & ENUM_SHOW_CHECKBOX)
		ret = "<TD><INPUT hideFocus=true TYPE='checkbox' " +
				(node.is_checked ?"checked":"" ) + " id='Tree_checkbox_"  + node.id + "'></TD>" + ret;
	return (ret+Tree_FormatText(node.text)+"</TD>");
}
//----------------
function Tree_FormatText(text)
{
	text=text.replace(/</g,"&lt;");
	text=text.replace(/>/g,"&gt;");
	text=text.replace(/ /g,"&nbsp;");
	return text.replace(/\"/g,"&quot;");
}
//----------event function---------------------
function Tree_On_ClickNode(Tree,node,hittest,event)
{
	if(node==null)return;
	node.on_click(hittest,event);
}
function Tree_On_MouseHover(Tree,node,hittest,event)
{
	if(node==null || Tree==null)return;
	node.on_mousehover(hittest,event);
}
function Tree_On_MouseOut(Tree,node,hittest,event)
{
	if(node==null || Tree==null)return;	
	node.on_mouseout(hittest,event);
}
 
var settimeID;
function Tree_On_DragDown(Tree,node,hittest,event)
{
	if(Tree.behavior & ENUM_ENABLE_DRAG_DROP)
	{
		if(hittest == HT_LABEL && node){
			//??????????????
			//settimeID=setTimeout("document.all.ShowFloder.style.display=''",200);
			//document.all.ShowFloderText.innerText=node.text;			
			document.drag_node=node;
			
		}
		else document.drag_node=null;
	}
}
function Tree_On_DragUp(Tree,node,hittest,event)
{   
   
	if(ENUM_ENABLE_DRAG_DROP & Tree.behavior )
	{   
		var drag_node=document.drag_node;
		if(drag_node==null)return null;
		//clearTimeout(settimeID);
	   // document.all.ShowFloder.style.display="none";// ????????????
		if(Tree_isIE && event.button!=1 )return null;
		if(Tree_isNC6 && event.button!=0 )return null;
		if(hittest == HT_LABEL && drag_node!=node && node && drag_node){
			if(drag_node.contain(node)) return null;
			if(drag_node.parent==node && event.ctrlKey==false )return null;
			if(typeof(Tree.cb_dragdrop)=='function' && Tree.cb_dragdrop(drag_node,node,Tree,event)==false)
				return null;
			var tmp = node.add_child(TREE_LAST,drag_node.text,drag_node.img_normal);
			tmp.deep_copy(drag_node,true);
			tmp.select();
			if(event.ctrlKey==false)drag_node.del();
			return tmp;
		}
		else if((node == null || hittest==null || (node.child_count==0 && hittest==HT_BUTTON))&& drag_node!=null)
		{
			if(drag_node.get_level()==1 && drag_node.get_tree()==Tree && event.ctrlKey==false)return null;
			if(typeof(Tree.cb_dragdrop)=='function' && Tree.cb_dragdrop(drag_node,null,Tree,event)==false)
				return null;
			var tmp;
			tmp= Tree.__container.add_child(TREE_LAST,drag_node.text,drag_node.img_normal);
			tmp.deep_copy(drag_node,true);
			tmp.select();
			if(event.ctrlKey==false)drag_node.del();
			return tmp;
		}
		return null;
	}
   
}
//--------------------
function Tree_buildTree(data_array,treeView)//init Tree_View from array
{
	Tree_View_Array[treeView.id]=treeView;
	treeView.__auto_draw=false;
	var nLen = data_array.length-1;
	var index = 0;
	var count = 0;
	var nodeChild=null;
	var nodeParent=null;
	while( index <= nLen )
	{
		switch( data_array[index] ){
		case TREE_CONST_END:
			break;
		case TREE_CONST_BEGIN_FOLDER:
			count++;
			break;
		case TREE_CONST_END_FOLDER:
			nodeParent=nodeParent.parent;
			count--;
			break;
		case TREE_CONST_FILE:;
		case TREE_CONST_FOLDER:
			index++;
			var action = new Tree_Action(data_array[index+5],data_array[index+6],data_array[index+7],treeView.id);
			nodeChild=new Tree_Node(data_array[index],data_array[index+3],data_array[index+4],data_array[index+1],data_array[index+2],action);
			if(count==0) treeView.__container.__add_child(nodeChild,TREE_LAST);
			else nodeParent.__add_child(nodeChild,TREE_LAST);
			if(data_array[index-1]==TREE_CONST_FOLDER)	nodeParent=nodeChild;
			break;
		}
		index ++;
	}
	treeView.__auto_draw=true;
	treeView.refresh();
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function newXMLHttpRequest() {
  var xmlreq = false;
  if (window.XMLHttpRequest) {
  
    xmlreq = new XMLHttpRequest();
  } else if (window.ActiveXObject) {
     
    try {
      
      xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e1) {
       
      try {
      
        xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e2) {
          
        alert(e2);
      }
    }
  }
  return xmlreq;
}

function getReadyStateHandler(req, eleid,responseXmlHandler) {
  
  return function () {
    
    if (req.readyState == 4) {
       
      if (req.status == 200) {
          
        responseXmlHandler(req.responseText,eleid);
 
      } else {
        
        alert("HTTP error: "+req.status);
      }
    }
  }
}

function handleResponse(data,eleid){

      var ele =document.parentWindow.frames[0];
      alert(ele);
      //alert(data);
      ele.src.innerHTML = data;
}


function submitAction(domId,action){


  var req = newXMLHttpRequest();
  
  var handlerFunction = getReadyStateHandler(req, domId,handleResponse);
  req.onreadystatechange = handlerFunction;
  req.open("POST", action, true);
  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

   
  req.send(null);

}
