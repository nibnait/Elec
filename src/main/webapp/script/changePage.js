

function jsNext(form1){

     alert(form1);
	 form1.direction.value="NEXT";
	 form1.goPage.value="";
	 form1.action="changePage.do";
	 form1.submit();

}
function jsPrevious(form1){

    
	form1.direction.value="PREVIOUS";
	form1.goPage.value="";
	form1.action="changePage.do";
	form1.submit();

}
function jsJumpto(from1){

   
	 var gopage=parseInt(form1.getElementById("goPage").value);
	 var sumpage=parseInt(form1.getElementById("sumPage").value);
	alert(gopage);
	alert(sumpage);
	alert(gopage<=sumpage&&gopage>=1)
	 if(gopage<=sumpage&&gopage>=1)
	 {
	 	
	 	form1.pageNo.value=form1.getElementById("goPage").value;
	 	form1.action="changePage.do";
	 	form1.submit();
	 }
	 
	 

}

function jsJumpToBegin(form1){
	 form1.pageNo.value="1";
	 form1.action="changePage.do";
	 form1.submit();

}

function jsJumpToEnd(form1){
	form1.pageNo.value=form1.getElementById("sumPage").value;
	form1.action="changePage.do";
	form1.submit();

}
