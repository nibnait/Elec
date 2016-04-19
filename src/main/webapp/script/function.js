var yicunzai="yicunzai"

/*************************************************************************************************/
			function Add(leftsel,rightsel,stroe) {
			   //alert('add');
  				allRoles = document.getElementById(leftsel);
  				selectids = document.getElementById(stroe);
  				selectRoles = document.getElementById(rightsel);
  				for(var i =0;i<allRoles.options.length;i++){
  					if(allRoles.options[i].selected==true){
  						var ifExit = false;
  						for(var n =0;n<selectRoles.options.length;n++){
  							if(selectRoles.options[n].value == allRoles.options[i].value){
  								ifExit = true;
  							}
  						}
  						if(ifExit == false){
  							selectRoles.add( new Option(allRoles.options[i].text,allRoles.options[i].value));
  						}else{
  							alert(yicunzai);
  						}
  						allRoles.remove(i);
  						i--;
  					}
  				}
  				id="";
  				for(var m =0;m<selectRoles.options.length;m++){
  					id+=selectRoles.options[m].value +",";
  				}
  				selectids.value=id;
			}
			function Remove(leftsel,rightsel,stroe) {
			    //alert('remove');
				selectids = document.getElementById(stroe);
  				allRoles = document.getElementById(leftsel);
  				selectRoles = document.getElementById(rightsel);
  				for(var i =0;i<selectRoles.options.length;i++){
  					if(selectRoles.options[i].selected==true){
  						allRoles.add( new Option(selectRoles.options[i].text,selectRoles.options[i].value));
  						selectRoles.remove(i);
  						i--;
  					}
  				}
  				id="";
  				for(var m =0;m<selectRoles.options.length;m++){
  					id+=selectRoles.options[m].value +",";
  				}
  				selectids.value=id;
			}
		
		/**************************/
		
   function loadselect(all,old,store){

                var all = document.getElementById(all);
  				var old = document.getElementById(old);
  				
  				for(var i =0;i<all.options.length;i++){
  				 
  				  var item=all.options[i].value;
  				
  				     for(var m =0;m<old.options.length;m++){
  					
  					   if(item==old.options[m].value){
  					     all.remove(i);
  					     i--;
  					   }				
  				     }
  				} 
  				
  				var stor=document.getElementById(store);
  				var id="";
  			    for(var j =0;j<old.options.length;j++){
  					id+=old.options[j].value +",";
  				}
  				stor.value=id;
 											
}	

		 
/*************************************************************************************************/


function insertRowInTable(sTable,iNum){
	var oTable=document.getElementById(sTable);
	if (!oTable)
	  return -1;
	  
	var oTr=oTable.insertRow(iNum);

	oTr.setAttribute("align","center");

    var rowindex = oTable.rows.length-1;
    var index=rowindex-1;
    var  stepnum=index+1;
    oTr.setAttribute("number",rowindex);
	oTr.id="casestep"+stepnum;
   
    
	//
	var oTd0 = oTr.insertCell(0);
	oTd0.setAttribute("width","5%");
	oTd0.setAttribute("align","center");
	oTd0.innerHTML="<input onclick=\"getSelectRow(event.srcElement,document.all.selectrow)\" type=\"checkbox\" value=\""+iNum+"\" name=\"selprocess"+index+"\" id=\"selprocess\"  >  "+"<input  type=\"hidden\" name=\"stepForm["+index+"].stepnum\" value=\""+iNum+"\">" +"<INPUT  type=\"hidden\" name=\"stepForm["+index+"].id\" value=\"\" >";
	//
	var oTd1 = oTr.insertCell(1);
    oTd1.setAttribute("width","5%");
	oTd1.innerHTML=iNum;
	
	//	
	var oTd2 = oTr.insertCell(2);
    oTd2.setAttribute("width","27%");
	oTd2.innerHTML="<TEXTAREA name=\"stepForm["+index+"].teststep\" maxlength=\"1024\" style=\"behavior:url(../htc/maxlength.htc)\"  onchange=\"limitarea(this,1024)\" ></TEXTAREA>";
	
	//
	var oTd3 = oTr.insertCell(3);
    oTd3.setAttribute("width","23%");
    oTd3.setAttribute("align","center");
	oTd3.innerHTML="<TEXTAREA name=\"stepForm["+index+"].condition\" maxlength=\"332\" style=\"behavior:url(../htc/maxlength.htc)\"  onchange=\"limitarea(this,332)\" ></TEXTAREA>";
	
	var oTd4 = oTr.insertCell(4);
    oTd4.setAttribute("width","26%");
    oTd4.setAttribute("align","center");
	oTd4.innerHTML="<TEXTAREA name=\"stepForm["+index+"].resuexpect\" maxlength=\"332\" style=\"behavior:url(../htc/maxlength.htc)\"  onchange=\"limitarea(this,332)\" ></TEXTAREA>";
	
	var oTd5 = oTr.insertCell(5);
    oTd5.setAttribute("width","14%");
   oTd5.setAttribute("align","center");
//	oTd5.innerHTML="<img border=\"0\" src=\"../images/delete.gif\" width=\"18\" height=\"18\"  onclick=\"deleteCaseStepClient('processelisttable',event.srcElement)\">";
	oTd5.innerHTML="<input style=\"width:50\" type=\"button\" onclick=\"deleteCaseStepClient('processelisttable',event.srcElement)\"  class=btn_mouseout onmouseover=\"this.className='btn_mouseover'\" onmouseout=\"this.className='btn_mouseout'\" value=\"??\" name=\"delbtn\" >";


}




/*************************************************************************************************/
function checkNull(theObj)
{
	if(typeof(theObj.value)!="undefined"&&theObj.value!="")
	{
		
		return true;
	}
	return false;
}


function getObjArray( obj ) {
 var arrRtn = new Array();
 if ( typeof eval( obj ) == "object" ) {
  if ( typeof eval( obj + ".length" ) == "number" ) {
   for ( var i = 0 ; i < eval( obj ).length ; i++ ) {
    arrRtn[ arrRtn.length ] = eval( obj )[ i ];
   }
  } else {
   arrRtn[ 0 ] = eval( obj );
  }
 }
 return arrRtn;
}

function getSelectRow(obj){

obj.value=obj.parentNode.parentElement.sectionRowIndex;

}

/*****************************************************************************************************/
//open sized window
function openWindow(sHref,strWidth,strHeight) {
  var strLeft=(screen.availWidth-strWidth)/2;
  var strTop=(screen.availHeight-strHeight)/2;
  var strRef="";
  strRef=strRef+"width="+strWidth+"px,height="+strHeight+"px,";
  strRef=strRef+"left="+strLeft+"px,top="+strTop+"px,";
  strRef=strRef+"resizable=yes,scrollbars=yes,status=yes,toolbar=no,systemmenu=no,location=no,borderSize=thin";//channelmode,fullscreen
  var openerobj= window.open(sHref,'newwin',strRef,false);
  openerobj.focus();
}

function  openWindowWithName(sHref,strWidth,strHeight,sName){

  var strLeft=(screen.availWidth-strWidth)/2;
  var strTop=(screen.availHeight-strHeight)/2;
  var strRef="";
  strRef=strRef+"width="+strWidth+"px,height="+strHeight+"px,";
  strRef=strRef+"left="+strLeft+"px,top="+strTop+"px,";
  strRef=strRef+"resizable=no,scrollbars=yes,status=yes,toolbar=no,systemmenu=no,location=no,borderSize=thin";//channelmode,fullscreen

  window.open(sHref,sName,strRef,false);
  
}


function refreshOpener(){

    //opener.location.href=sHref;
    opener.location.reload();
    window.close();
}

//???????

function LTrim(str) 
{ 
var i; 
for(i=0;i<str.length;i++) 
{ 
if(str.charAt(i)!=" "&&str.charAt(i)!=" ")break; 
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









































