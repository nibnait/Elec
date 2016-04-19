function Pub(){ } 

Pub.submit=function(formindex) {
			
  				document.forms[formindex].submit();
  				
}

Pub.formsubmitAction=function(formindex,action) {
            
			document.forms[formindex].action=action;
  				document.forms[formindex].submit();
  				
}


Pub.submitform=function(formname){
   

   var theform =document.forms[formname];
   theform.submit();
}

Pub.reset=function(formindex){
				document.forms[formindex].reset();
}


////////////////////////////////////////////////////////////////////////////////
Pub.checkAll=function(parent, itemName)
{
  
  var parentbox = document.getElementsByName(itemName);
  
  for (var i=0; i<parentbox.length; i++){
  
  
   parentbox[i].checked = parent.checked;
   
   }
}
Pub.checkItem=function (child, allName)
{
  var all = document.getElementsByName(allName)[0];
  if(!child.checked) all.checked = false;
  else
  {
    var childbox = document.getElementsByName(child.name);
    for (var i=0; i<childbox.length; i++)
     if(!childbox[i].checked) return;
    all.checked = true;
  }
}

Pub.getAllCheckItemValue=function(storename,removevalue){


var obj_all = document.getElementsByTagName("INPUT");
var all_value="";
for(i=0;i<obj_all.length;i++){
if(obj_all[i].type=="checkbox")
   if(obj_all[i].checked ){
      if(obj_all[i].value!=removevalue)
      all_value=all_value+obj_all[i].value+",";
   }
      
   
}
var obj = document.getElementById(storename);
obj.value=all_value;

}

Pub.getAllCheckItemValueWithRemove=function(storename,removevalue){


var obj_all = document.getElementsByTagName("INPUT");
var all_value="";
for(i=0;i<obj_all.length;i++){
if(obj_all[i].type=="checkbox")
   if(obj_all[i].checked ){
      if(obj_all[i].value.indexOf("NULL")==-1){ 
      if(obj_all[i].value!=removevalue)
      all_value=all_value+obj_all[i].value+",";
   }
      }
   
}
var obj = document.getElementById(storename);
obj.value=all_value;

}

///////////////////////////////////////////////////////////////////////////////////////////////////////
Pub.copyRole=function(paramname,action){

var obj_all = document.getElementsByTagName("INPUT");
var value="";
for(i=0;i<obj_all.length;i++){
if(obj_all[i].type=="radio")
   if(obj_all[i].checked ){
       
        value = obj_all[i].value;
      }
   
}

 if(value==""){
 return false;}
  document.forms[0].action=action+"?"+paramname+"="+value;
  document.forms[0].submit();

}
///////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * 创建ajax引擎
 */
Pub.newXMLHttpRequest=function newXMLHttpRequest() {
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



/**
 * 
 * @param req:引擎对象
 * @param eleid：表单Form2的名称
 * @param responseXmlHandler：Pub.handleResponse（函数体）
 * @returns {Function}
 */
Pub.getReadyStateHandler =function getReadyStateHandler(req, eleid,responseXmlHandler) {
  
  return function () {
    /**
     * req.readyState:用来监听客户端与服务器端的连接状态
     * 0：表示此时客户端没有调用open()方法
     * 1：表示客户端执行open方法，但是send方法没有执行
     * 2：open方法执行，send方法也执行
     * 3：服务器开始处理数据，并返回数据
     * 4：返回数据成功，只有4这个状态，才能获取服务器端返回的结果
     */
    if (req.readyState == 4) {
       /**
        * req.status：表示java的状态码
        * 404：路径错误
        * 500：服务器异常
        * 200：表示没有异常，正常访问数据，只有200这个状态的时候，才能获取服务器端返回的结果
        */
      if (req.status == 200) {
          /**
           * req.responseText:获取服务器端返回的结果，返回的是文本的结果（字符串，json数据）
           * req.responseXML:获取服务器端返回的结果，返回的是XML数据的结果
           */
        responseXmlHandler(req.responseText,eleid);
 
      } else {
        
        alert("HTTP error: "+req.status);
        return false;
      }
    }
  }
}





Pub.getStatisticReadyStateHandler =function (req, eleid,responseXmlHandler,noteId) {
  alert(req.readyState);
  return function () {
    if (req.readyState == 4) {
       
      if (req.status == 200) {
          
        responseXmlHandler(req.responseText,eleid);
 
      } else {
        
        alert("HTTP error: "+req.status);
        return false;
      }
    }else{
   
     var ele11 =document.getElementById(noteId);
    ele11.innerHTML="Loading Data";
    }
  }
}

/**
 * 
 * @param data：服务器返回的结果
 * @param eleid：表单Form2的名称
 */
Pub.handleResponse= function handleResponse(data,eleid){
	  //获取表单Form2的对象
      var ele =document.getElementById(eleid);
      //将返回的结果放置到表单Form2的元素中
      ele.innerHTML = data;
    
}


Pub.submitAction=function(domId,action){

  var req = Pub.newXMLHttpRequest();
  
  var handlerFunction = Pub.getReadyStateHandler(req, domId,Pub.handleResponse);
  req.onreadystatechange = handlerFunction;
  req.open("POST", action, false);
  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

   
  req.send(null);

}




Pub.submitActionWithNote=function(domId,action,noteId){


  var req = Pub.newXMLHttpRequest();
  
  var handlerFunction = Pub.getStatisticReadyStateHandler(req, domId,Pub.handleResponse,noteId);
  req.onreadystatechange = handlerFunction;
  req.open("POST", action, true);
  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

  req.send(null);

}



/**
 * 
 * @param sForm：传递表单Form1的名称
 * @returns {String}：使用ajax返回服务器端的参数，传递的就是表单Form1中元素的参数
 */
Pub.getParams2Str=function getParams2Str(sForm){

 var strDiv="";
      
 try {
    var objForm=document.forms[sForm];
  if (!objForm)
    return strDiv;
  var elt,sName,sValue;
  for (var fld = 0; fld < objForm.elements.length; fld++) {
      elt = objForm.elements[fld];    
      sName=elt.name;
      sValue=""+elt.value;
      if(fld==objForm.elements.length-1)
          strDiv=strDiv + sName+"="+sValue+"";
       else   
          strDiv=strDiv + sName+"="+sValue+"&";
   }


  }
  catch (ex) {
    return strDiv;
	}
 
   return strDiv;
}

function doContents(){
    if (xmlhttp.readyState < 4){
        alert("a");
    }
    else if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
        alert("b");
    }
}

/***
 * domId：表单Form2的名称
 * action：表示URL连接
 * sForm：表单Form1的名称
 */
Pub.submitActionWithForm=function(domId,action,sForm){
  /**第一步：创建Ajax引擎对象*/
  var req = Pub.newXMLHttpRequest();
  /**第二步：req.onreadystatechange表示事件处理函数（相当于一个监听），用来监听客户端与服务器端的连接状态*/
  var handlerFunction = Pub.getReadyStateHandler(req, domId,Pub.handleResponse);
  req.onreadystatechange = handlerFunction;
  /**第三步：打开一个连接，要求：如果是POST请求，需要添加一个头部信息，否则此时不能使用send向服务器发送数据*/
  req.open("POST", action, true);
  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); 
  /**第四步：向服务器发送数据，格式：name=张三&age=28*/
  var str = Pub.getParams2Str(sForm); 
  //传递表单Form1中的元素作为参数给服务器
  req.send(str);
}

Pub.submitActionWithFormGet=function(domId,action,sForm){
	  
	  var req = Pub.newXMLHttpRequest();
	  
	  var handlerFunction = Pub.getReadyStateHandler(req, domId,Pub.handleResponse);
	  req.onreadystatechange = handlerFunction;
	  
	  req.open("Get", action, true);
	  req.send(null);
}

