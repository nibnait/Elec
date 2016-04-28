function  gotopage(path,where){
       var page=document.Form2.pageNO;
       
       if(where=="next"){ 
     
          page.value=document.Form2.nextpageNO.value;
      
       }else if(where=="prev"){
     
          page.value=document.Form2.prevpageNO.value;
       }else if(where=="end"){
     
          page.value=document.Form2.sumPage.value;
       }else if(where=="start"){
          page.value=1;
       }else if(where=="go"){
         var theForm = document.Form2;   
         if(Trim(theForm.goPage.value)=="")
	     {
		     alert("请输入页数"); 
		     theForm.goPage.focus();   
		     return false;
	     }
	     if(!checkNumber(theForm.goPage))
	     {
		     alert("请输入正确页数(数字)"); 
		     theForm.goPage.focus();     
		     return false;
	     }
	     
	     var objgo=parseInt(theForm.goPage.value);
	     var objsum=parseInt(theForm.sumPage.value);
	     if(objgo<=0||objgo>objsum){
	         alert("不存在此页，请重新输入页数"); 
		     theForm.goPage.focus();     
		     return false; 
	     }
         
         page.value=theForm.goPage.value;                
      } 
     
      document.Form1.pageNO.value=document.Form2.pageNO.value;
      Pub.submitActionWithForm('Form2',path,'Form1');       
  }
  
  function gotoquery(path){
      document.Form1.pageNO.value=1;
      Pub.submitActionWithForm('Form2',path,'Form1'); 
  }