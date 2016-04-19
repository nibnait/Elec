

function jsNext(formindex){

     
	 document.forms[formindex].direction.value="NEXT";
	 document.forms[formindex].goPage.value="";
	 //document.forms[formindex].action="changePage.do";
	// document.forms[formindex].submit();
	 
	 Pub.submitActionWithForm(formindex,'changePage.do',formindex);

}
function jsPrevious(formindex){

    
	 document.forms[formindex].direction.value="PREVIOUS";
	 document.forms[formindex].goPage.value="";
	 //document.forms[formindex].action="changePage.do";
	//document.forms[formindex].submit();
 Pub.submitActionWithForm(formindex,'changePage.do',formindex);

}
function jsJumpto(formindex){

     document.forms[formindex].target="";
	
	// if(document.getElementById("goPage").value!=""&&document.getElementById("goPage").value<=document.getElementById("sumPage").value&&
	// document.getElementById("goPage").value>=1)
	// {document.forms[formindex].pageNo.value=document.getElementById("goPage").value;
	// document.forms[formindex].action="changePage.do";
	// document.forms[formindex].submit();
	// Pub.submitActionWithForm(formindex,'changePage.do',formindex);
	//}
	 
	 
	 
	 	 var gopage=parseInt(document.getElementById("goPage").value);
	 var sumpage=parseInt(document.getElementById("sumPage").value);
	
	 if(gopage<=sumpage&&gopage>=1)
	 {
	 	
	 	document.forms[formindex].pageNo.value=document.getElementById("goPage").value;
	 	
	 	 Pub.submitActionWithForm(formindex,'changePage.do',formindex);
	 }
	 

}

function jsJumpToBegin(formindex){
	 document.forms[formindex].pageNo.value="1";
	 //document.forms[formindex].action="changePage.do";
	 //document.forms[formindex].submit();
	  Pub.submitActionWithForm(formindex,'changePage.do',formindex);

}

function jsJumpToEnd(formindex){
	document.forms[formindex].pageNo.value=document.getElementById("sumPage").value;
	//document.forms[formindex].action="changePage.do";
	//document.forms[formindex].submit();
	 Pub.submitActionWithForm(formindex,'changePage.do',formindex);

}
