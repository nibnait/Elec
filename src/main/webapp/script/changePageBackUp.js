


//////////
function jsNext(){

	 document.Form1.direction.value="NEXT";
	 document.Form1.goPage.value="";
	 document.Form1.action="changePage.do";
	 document.Form1.submit();

}
function jsPlanNext(){

    
	 document.Form1.direction.value="";
	 document.Form1.goPage.value="";
	 document.Form1.action="devicePlanNextDate.do";
	 document.Form1.submit();

}
function jsDPToD(){

    
	 document.Form1.direction.value="";
	 document.Form1.goPage.value="";
	 document.Form1.action="devicePlanToDevice.do";
	 document.Form1.submit();

}
function jsPrevious(){

    	
	 document.Form1.direction.value="PREVIOUS";
	 document.Form1.goPage.value="";
	 document.Form1.action="changePage.do";
	 document.Form1.submit();

}
function jsJumpto(){

    
	 var gopage=parseInt(document.getElementById("goPage").value);
	 var sumpage=parseInt(document.getElementById("sumPage").value);
	
	 if(gopage<=sumpage&&gopage>=1)
	 {
	 	
	 	document.Form1.pageNo.value=document.getElementById("goPage").value;
	 	document.Form1.action="changePage.do";
	 	document.Form1.submit();
	 }
	 
	 

}

function jsJumpToBegin(){
	 document.Form1.pageNo.value="1";
	 document.Form1.action="changePage.do";
	 document.Form1.submit();

}

function jsJumpToEnd(){
	document.Form1.pageNo.value=document.getElementById("sumPage").value;
	document.Form1.action="changePage.do";
	document.Form1.submit();

}
//



function jsNextX(){

	 document.Form1.direction.value="NEXT";
	 document.Form1.goPage.value="";
	 document.Form1.action="changePageDeviceX.do";
	 document.Form1.submit();

}

function jsPreviousX(){

    	
	 document.Form1.direction.value="PREVIOUS";
	 document.Form1.goPage.value="";
	 document.Form1.action="changePageDeviceX.do";
	 document.Form1.submit();

}
function jsJumptoX(){

    
	 var gopage=parseInt(document.getElementById("goPage").value);
	 var sumpage=parseInt(document.getElementById("sumPage").value);
	
	 if(gopage<=sumpage&&gopage>=1)
	 {
	 	
	 	document.Form1.pageNo.value=document.getElementById("goPage").value;
	 	document.Form1.action="changePageDeviceX.do";
	 	document.Form1.submit();
	 }
	 
	 

}

function jsJumpToBeginX(){
	 document.Form1.pageNo.value="1";
	 document.Form1.action="changePageDeviceX.do";
	 document.Form1.submit();

}

function jsJumpToEndX(){
	document.Form1.pageNo.value=document.getElementById("sumPage").value;
	document.Form1.action="changePageDeviceX.do";
	document.Form1.submit();

}




//
function jsNextDevice(){

    
	 document.Form1.direction.value="NEXT";
	 document.Form1.goPage.value="";
	 document.Form1.action="changePageDevice.do";
	 document.Form1.submit();

}
function jsPlanNextDevice(){

    
	 document.Form1.direction.value="";
	 document.Form1.goPage.value="";
	 document.Form1.action="devicePlanNextDate.do";
	 document.Form1.submit();

}
function jsDPToDDevice(){

    
	 document.Form1.direction.value="";
	 document.Form1.goPage.value="";
	 document.Form1.action="devicePlanToDevice.do";
	 document.Form1.submit();

}
function jsPreviousDevice(){

    	
	 document.Form1.direction.value="PREVIOUS";
	 document.Form1.goPage.value="";
	 document.Form1.action="changePageDevice.do";
	 document.Form1.submit();

}
function jsJumptoDevice(){

    
	 var gopage=parseInt(document.getElementById("goPage").value);
	 var sumpage=parseInt(document.getElementById("sumPage").value);
	
	 if(gopage<=sumpage&&gopage>=1)
	 {
	 	
	 	document.Form1.pageNo.value=document.getElementById("goPage").value;
	 	document.Form1.action="changePageDevice.do";
	 	document.Form1.submit();
	 }
	 
	 

}

function jsJumpToBeginDevice(){
	 document.Form1.pageNo.value="1";
	 document.Form1.action="changePageDevice.do";
	 document.Form1.submit();

}

function jsJumpToEndDevice(){
	document.Form1.pageNo.value=document.getElementById("sumPage").value;
	document.Form1.action="changePageDevice.do";
	document.Form1.submit();

}

///////////////////////////////////////

