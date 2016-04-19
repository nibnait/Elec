<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>导出设置</title>   
<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
<script type="text/javascript" >
  
  /**
  function Add() {
			  
  		allRoles = document.getElementById("colname1");
  		selectRoles = document.getElementById("colname2");
  		
  		for(var i =0;i<allRoles.options.length;i++){
  		  if(allRoles.options[i].selected==true){
  			  selectRoles.options.add( new Option(allRoles.options[i].text,allRoles.options[i].value));
  			  allRoles.remove(i);
  			  i--;
  	      }
  	   }
  	   				
	}
  
    function Remove() {
			
	     allRoles = document.getElementById("colname1");
  		 selectRoles = document.getElementById("colname2");
  		    
  		 for(var i =0;i<selectRoles.options.length;i++){
  			if(selectRoles.options[i].selected==true){
  				allRoles.options.add( new Option(selectRoles.options[i].text,selectRoles.options[i].value));
  				selectRoles.remove(i);
  				i--;
  			}
  		 }
    }
    function upcol() {
		  
			var rightcol = document.getElementById("colname2");           
        	var selectflag=0;
        
			for(var i =0;i<rightcol.options.length;i++){
							
				if(rightcol.options[i].selected==true && i!=0){
					
					var temptext=rightcol.options[i].text;
					var tempvalue=rightcol.options[i].value;
					
					rightcol.options[i].value=rightcol.options[i-1].value;
					rightcol.options[i].text=rightcol.options[i-1].text;
					rightcol.options[i-1].value=tempvalue;
					rightcol.options[i-1].text=temptext;
					
					selectflag=i-1;
					break; //这个标志表明目前只能一次移一行，不支持多选
				}
			}
			
			for(var i =0;i<rightcol.options.length;i++){
			   rightcol.options[i].selected=false;
			}
			rightcol.options[selectflag].selected=true;	
	}
    function downcol() {
		  
			var rightcol = document.getElementById("colname2");             
        	var selectflag=0;
        
			for(var i=0;i<rightcol.options.length;i++){

				if(rightcol.options[i].selected==true && i!=rightcol.options.length-1){
						
					var temptext=rightcol.options[i].text;
					var tempvalue=rightcol.options[i].value;
					
					rightcol.options[i].value=rightcol.options[i+1].value;
					rightcol.options[i].text=rightcol.options[i+1].text;
					rightcol.options[i+1].value=tempvalue;
					rightcol.options[i+1].text=temptext;
					
					selectflag=i+1;
					break; //这个标志表明目前只能一次移一行，不支持多选
				}
			}
			
			if(selectflag!=0){ //如果此标志为0，说明光标已经移到最下边，没有发生向下交换的行动
			    for(var i =0;i<rightcol.options.length;i++){
			      rightcol.options[i].selected=false;
			    }
			    rightcol.options[selectflag].selected=true;
			}
	}
  */
  
  function Add() {
  	   $("#colname1 option:selected").appendTo($("#colname2"));		
  }
	
  function Remove() {
	   $("#colname2 option:selected").appendTo($("#colname1"));	
  }
     
     
  function upcol() {
	   //获取选中的右侧option元素
	   var rightcol = $("#colname2 option:selected"); 
	   //option的第一个元素无法上移，rightcol.get(0)表示选中的option对象，rightcol.get(0).index表示option对象的位置索引，从0开始
       if(rightcol.get(0).index!=0){  
    	    rightcol.each(function(){  
	   			$(this).prev().before($(this));  //在当前选中对象的前面插入该对象
	   			//$(this).insertBefore($(this).prev());//等同于
	   		});  
	   }  	
  }
  function downcol() {
	   //选择所有的对象
	   var allrightcol = $("#colname2 option");  
	   //选择被选中的对象
	   var rightcol = $("#colname2 option:selected");  
	   //option的最后一个元素无法下移
	   if(rightcol.get(rightcol.length-1).index!=allrightcol.length-1){  
		    //循环选中的对象
		    for(i=rightcol.length-1;i>=0;i--){  
		    	//获取选中的对象
		    	var item = $(rightcol.get(i));  
		  		item.insertAfter(item.next());  //将选中的对象插入到下一个对象的后面
		  		//item.next().after(item);//等同于
		    }  
	  } 
  }  
  	
   function setValue(){

  	   				
   
            var rightcol = document.getElementById("colname2");
            var leftcol = document.getElementById("colname1");
            
       		selectid="";		
  			selectname="";
  			noselectid="";
  			noselectname="";
  							
  			for(var m =0;m<rightcol.options.length;m++){
  				 if(m==rightcol.options.length-1){
  				   selectid+=rightcol.options[m].value;
  				   selectname+=rightcol.options[m].text;
  				   }else{
  				   selectid+=rightcol.options[m].value +"#"; 
  				    selectname+=rightcol.options[m].text +"#";   
  				 }			
  			}
  			   
  			for(var m =0;m<leftcol.options.length;m++){
  				if(m==leftcol.options.length-1){
  				  noselectid+=leftcol.options[m].value;
  				  noselectname+=leftcol.options[m].text;
  				 }else{
  				   noselectid+=leftcol.options[m].value +"#"; 
  				   noselectname+=leftcol.options[m].text +"#";   
  				 }			
  			}
  				
  			document.Form1.expNameList.value=selectname;
  			document.Form1.expFieldName.value=selectid;
  			document.Form1.noExpNameList.value=noselectname;
  			document.Form1.noExpFieldName.value=noselectid;
  			 			
     }	
     
     function checksubmit(){
       setValue();	
       if(document.Form1.expNameList.value=="" || document.Form1.expFieldName.value==""){
       
          alert("请至少选择一列作为导出列");
          return;
       }
     
       document.Form1.action="elecExportFieldsAction_saveSetExportExcel.do";
       document.Form1.submit();      
     }
     
     </script>
  </head>
  
  <body onload="setValue()">
   <FORM id="Form1" name="Form1"  method="POST" >
   
  <br>
   <table border="0" width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
			<font face="宋体" size="2"><strong>导出字段设置</strong></font>
		</td>
	</tr>
	<tr height=10><td></td></tr>
	
    <tr>
    <td width="100%">
      <table border="0" width="100%" cellspacing="3" cellpadding="0">
        <tr height=10>
        	<TD width="1%"></TD>
        	<TD width="30%" class="DropShadow" align="left" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15">未导出字段列表</TD>
        	<td width="16%" >
        	<TD width="34%" class="DropShadow" align="left" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15">导出字段列表</TD>
        	<td width="19%">
        </tr>
        
        <tr>
          <td width="1%"></td>
          <td width="84%" colspan="4">      
           <table border="0" width="100%" cellspacing="0" cellpadding="0">
               <tr>
                   <td width="30%" rowspan="4">
                  
                   <select size="15" name="colname1" multiple style="width:200px" id="colname1" ondblclick="JavaScript:Add('colname1','colname2','colname')">
						<option value="stationRun" >站点运行情况</option>
                   </select>
                   </td> 
                   <td width="15%"></td>
                   <td width="35%" rowspan="4" id="colnameDiv">
                   
                  
                   <select size="15" name="colname2" id="colname2" multiple style="width:200px" ondblclick="JavaScript:Remove('colname1','colname2','colname')">
                   		<option value="devRun" >设备运行情况</option>
                   		<option value="createDate" >创建日期</option>
                   </select>
                   </td>  
                   
                   <td width="20%"></td>
                </tr>  
             
                  <tr>                  
	                   <td width="90" align="center">
	                   <input name="DoAddb" type="button" value=">>" onClick="JavaScript:Add()" class=btn_mouseout onmouseover="this.className='btn_mouseover'" onmouseout="this.className='btn_mouseout'" style="width: 30px; font-size:12px; color:black; height=22">
	                   </td>
	                   <td width="120" align="center">
	                   <input name="doup" type="button" value="向上" onClick="upcol()" class=btn_mouseout onmouseover="this.className='btn_mouseover'" onmouseout="this.className='btn_mouseout'" style="width: 50px; font-size:12px; color:black; height=22">
	                   </td>
                  </tr>
                  <tr>
	                  <td width="90" align="center">
	                  <input name="DoDelb" type="button" value="<<"  onClick="JavaScript:Remove()" class=btn_mouseout onmouseover="this.className='btn_mouseover'" onmouseout="this.className='btn_mouseout'" style="width: 30px; font-size:12px; color:black; height=22">
	                  </td>
	                  <td width="120" align="center">
	                  <input name="dodown" type="button" value="向下"  onClick="downcol()" class=btn_mouseout onmouseover="this.className='btn_mouseover'" onmouseout="this.className='btn_mouseout'" style="width: 50px; font-size:12px; color:black; height=22">
	                  </td>
                  </tr>
  
                  <tr><td width="73">
                  	   <INPUT type="hidden"  name="belongTo"  id="belongTo"  value="5-3">
	                   <INPUT type="hidden"  name="expNameList"  id="expNameList"  value="">
	                   <INPUT type="hidden"  name="expFieldName" id="expFieldName"  value="">
	                   <INPUT type="hidden"  name="noExpNameList"  id="noExpNameList"  value="">
	                   <INPUT type="hidden"  name="noExpFieldName" id="noExpFieldName"  value="">
                  </td></tr>     
           </table>  
          </td>
        </tr>
      </table> 
      </td>
      </tr>
      <tr height=10><td></td></tr>
      <tr height=20><td background="${pageContext.request.contextPath }/images/b-info.gif"></td></tr>
      <tr height=10><td></td></tr>
      <tr><td align="center"><INPUT type="button"  name="save" id="save"  value="保存" onclick="checksubmit()" style="width: 60px; font-size:12px; color:black; height=22">
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<input style="width: 60px; font-size:12px; color:black; height=22" type="reset" value="关闭" id="Reset1" name="Reset1" onclick="window.close();"></td></tr>
   </table>	
 </FORM>
  </body>
</html>
