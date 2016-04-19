
			function verifyAddress(obj){
				var	email	=	obj.email.value;
				var	pattern	=	/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
				
				flag = pattern.test(email);
				if(!flag){
					alert("您的email地址验证有误，请重新输入！");
					obj.select();
					obj.focus();
					return false;
				}else{
					return true;
				}
			}
			
	
			
			function verifyDate(obj){
				var	v	=	obj.value;
				var	pattern	=/^([1000-2500]{4})-(((0)[1-9])|((1)[0-2]))-((0|1|2)\d)|((3)[0-1])$/;
				
				flag = pattern.test(v);
				if(!flag){
					alert("您的日期（yyyy-mm）验证有误，请重新输入！");
					obj.select();
					obj.focus();
					return false;
				}else{
					return true;
				}
			}
			
			
			function verifyDateTime(obj){
				var	v	=	obj.value;
				var	pattern	=/^([1990-2500]{4})-((((0)[0-9])|((1)[0-2])))-(((0|1|2)\d)|((3)[0-1])) (((0|1)\d)|((2)[0-4]))\:(([0-5]\d)|(60))$/;
				//
				flag = pattern.test(v);
				if(!flag){
					alert("您的日期（yyyy-mm-dd hh:mm）验证有误，请重新输入！");
					obj.select();
					obj.focus();
					return false;
				}else{
					return true;
				}
			}
	
	
		
			function isNumber(obj){
				
				if(obj == null)
					alert("输入的内容不能为空");
					
				var v = obj.value;
					
				var pattern = /^[0-9]+/;
				flag = pattern.test(v);
				
				if(!flag){
					alert("您输入的内容必须是整数，请重新输入！");
					
					obj.select();
					obj.focus();
					return false;
				}else{
					return true;
				}
			}
			function checkNum(str){return str.match(/\D/)==null} 
			
		
			function isDecimal(obj){
				if(obj == null)
					alert("输入的内容不能为空");
					
				var v = obj.value;
					
				var pattern = /^[1-9]+\.\d{1,2}$/;
				flag = pattern.test(v);
				
				if(!flag){
					alert("输入的内容必须是小数，且保留2位小数");
					
					obj.value = obj.value + ".00";
					obj.select();
					obj.focus();
					return false;
				}else{
					return true;
				}
			}
			
			
			function isPhone(obj){
				if(obj == null)
					alert("输入的内容不能为空");
					
				var v = obj.value;
					
				var pattern = /^(\(\d{3}\))?\d{3}-\d{8}$/;
				flag = pattern.test(v);
				
				if(!flag){
					alert("电话号码输入有误！格式为（010-88888888或者（086）010-88888888）");
					
					obj.select();
					obj.focus();
					return false;
				}else{
					return true;
				}
			}
			
			
			function isEmpty(obj){
				if(obj == null)
					alert("输入的内容不能为空");
					
				var v = obj.value;
					
				var pattern = /(\d|([^ ]))/;
				flag = pattern.test(v);
				
				if(!flag){
					alert("输入的内容不能为空，且不能输入空格");
					
					obj.select();
					obj.focus();
					return false;
				}else{
					return true;
				}
			}
			
			
function checkNull(theObj)
{
	if(typeof(theObj.value)!="undefined"&&theObj.value!="")
	{
		
		return true;
	}
	return false;
}
		
function checkNumber(theObj)
{
	if(typeof(theObj.value)!="undefined"&&theObj.value!=""&&!isNaN(theObj.value))
	{
		return true;
	}
	return false;
}


function checkDatetime(checktext)
{
 var datetime;
 var year,month,day;
 var gone,gtwo;
if(checktext!=""){
datetime=checktext;
year=datetime.substring(0,datetime.indexOf("-"));
if(isNaN(year)==true){
 return false;
}
gone=datetime.substring(datetime.indexOf("-"),datetime.indexOf("-")+1);
month=datetime.substring(datetime.indexOf("-")+1,datetime.lastIndexOf("-"));
if(isNaN(month)==true){
 return false;
}
gtwo=datetime.substring(datetime.lastIndexOf("-"),datetime.lastIndexOf("-")+1);
day=datetime.substring(datetime.lastIndexOf("-")+1,datetime.length);
if(isNaN(day)==true){
 return false;
}
if((gone=="-")&&(gtwo=="-")){
 if(month<1||month>12) { 
  return false; 
  } 
 if(day<1||day>31){ 
  return false; 
 }else{
  if(month==2){  
   if(isLeapYear(year)&&day>29){ 
     return false;
   }       
   if(!isLeapYear(year)&&day>28){ 
     return false; 
   } 
  } 
  if((month==4||month==6||month==9||month==11)&&(day>30)){ 
   return false; 
  } 
 }
}else{
 return false;
}
}else{
return false;
}
return true;
}			
function isLeapYear(intYear) { 
   if (intYear % 100 == 0) { 
      if (intYear % 400 == 0) { return true; } 
   } 
   else { 
      if ((intYear % 4) == 0) { return true; } 
   } 
   return false; 
} 

function checkBirthday(checktext)
{
	var dateObj = new Date();
 var datetime;
 var year,month,day;
 var gone,gtwo;
if(checktext!=""){
datetime=checktext;
year=datetime.substring(0,datetime.indexOf("-"));
if(isNaN(year)==true || dateObj.getYear()<year){
 return false;
}

gone=datetime.substring(datetime.indexOf("-"),datetime.indexOf("-")+1);
month=datetime.substring(datetime.indexOf("-")+1,datetime.lastIndexOf("-"));
if(isNaN(month)==true || dateObj.getMonth()<month && dateObj.getYear()<year){
 return false;
}
gtwo=datetime.substring(datetime.lastIndexOf("-"),datetime.lastIndexOf("-")+1);
day=datetime.substring(datetime.lastIndexOf("-")+1,datetime.length);
if(isNaN(day)==true || dateObj.getDay()<day && dateObj.getMonth()<month && dateObj.getYear()<year){
 return false;
}
if((gone=="-")&&(gtwo=="-")){
 if(month<1||month>12) { 
  return false; 
  } 
 if(day<1||day>31){ 
  return false; 
 }else{
  if(month==2){  
   if(isLeapYear(year)&&day>29){ 
     return false;
   }       
   if(!isLeapYear(year)&&day>28){ 
     return false; 
   } 
  } 
  if((month==4||month==6||month==9||month==11)&&(day>30)){ 
   return false; 
  } 
 }
}else{
 return false;
}
}else{
return false;
}
return true;
}			





function checkIdentityNumber(strNumber)
{
	
	if((!isNaN(strNumber)||(!isNaN(strNumber.substr(0,17))&&strNumber.substr(17,18)=="x"))&&(strNumber.length==15||strNumber.length==18))return true;
	return false;
}
function checkMobilPhone(strNumber)
{
	var pattern = /(^0{0,1}13[0-9]{9}$)/;
	if(pattern.test(strNumber))return true;
	return false;
}


function checkPhone(strNumber)
{
	var pattern = /(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/;
	if(pattern.test(strNumber))return true;
	return false;
}

function checkEmail(strEmail)
{
	var	pattern	=	/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	if(pattern.test(strEmail)) return true;
	return false;
}
 
function checkUserList(strUserlist)
{
	var	pattern	=	/^([a-zA-Z0-9_-])+(\,[a-zA-Z0-9_-]*)*$/;
	if(pattern.test(strUserlist)) return true;
	return false;
}



function LTrim(str) 
{ 
var i; 
for(i=0;i<str.length;i++) 
{ 
if(str.charAt(i)!=" "&&str.charAt(i)!="?")break; 
} 
str=str.substring(i,str.length); 
return str; 
} 

function RTrim(str) 
{ 
var i; 
for(i=str.length-1;i>=0;i--) 
{ 
if(str.charAt(i)!=" "&&str.charAt(i)!="?")break; 
} 
str=str.substring(0,i+1); 
return str; 
} 

function Trim(str) 
{ 
return LTrim(RTrim(str)); 
} 








