/*firefox*/function __firefox(){    HTMLElement.prototype.__defineGetter__("runtimeStyle", __element_style);    window.constructor.prototype.__defineGetter__("event", __window_event);    Event.prototype.__defineGetter__("srcElement", __event_srcElement);}function __element_style(){    return this.style;}function __window_event(){    return __window_event_constructor();}function __event_srcElement(){    return this.target;}function __window_event_constructor(){    if(document.all){        return window.event;    }    var _caller = __window_event_constructor.caller;    while(_caller!=null){        var _argument = _caller.arguments[0];        if(_argument){            var _temp = _argument.constructor;            if(_temp.toString().indexOf("Event")!=-1){                return _argument;            }        }        _caller = _caller.caller;    }    return null;}if(window.addEventListener){    __firefox();}/*end firefox*/ 

function showInfoWithPanel(obj){
   try{
          //var e=event||window.event;
		  var showInfoWindow=document.getElementById("showInfomation");
		  showInfoWindow.className="clsShowInfoWindow";
		  showInfoWindow.style.visibility="visible";
		  var x=document.body.scrollLeft+event.clientX+10;
          var y=event.clientY+document.body.scrollTop+10;//+document.documentElement.scrollTop;
		  showInfoWindow.style.left=x;
		  showInfoWindow.style.top=y;
		  showInfoWindow.innerHTML="";
		  showInfoWindow.innerHTML=obj.innerHTML;//+" clientY:"+y+" clientX:"+x;
		  obj.style.color="red";
   }catch(e){alert("showInfoWithPanel:"+e.message);}
}
function hiddenInfoPanel(obj){
      try{
		 var showInfoWindow=document.getElementById("showInfomation");
		 if(showInfoWindow || typeof(showInfoWindow)!='undefined'){
		  showInfoWindow.innerHTML="";
		  showInfoWindow.style.visibility="hidden";
		  }
		  obj.style.color="#000000";
	}catch(e){alert("hiddenInfoPanel:"+e.message);}
}
