
function OpenModalWindow(url,width,height)
{
	var vReturnValue = window.showModalDialog(url,null,"dialogWidth:"+width+";dialogHeight:"+height+";location=no,directories=no,resizable=yes,status=no,menubar=no,scrollbars=yes;");
	
	window.opener = null ;
	location.reload();
	
	
}


var RemindWindow
function OpenWindow(name,url,width,height)
{
	//debugger ;
	if(! RemindWindow || RemindWindow.closed)
	{
		var left=0,top=0;
//		if(screen.width > "800")
//		{
			left = (parseInt(screen.availWidth)-parseInt(width))/2;
//		}
//		if(screen.height > "600")
//		{
			top = (parseInt(screen.availHeight)-parseInt(height))/2;
//		}

		RemindWindow = window.open( "",name,"toolbar=no,top="+ top +",left="+ left +",width="+width+",height="+height+",location=no,directories=no,resizable=yes,status=no,menubar=no,scrollbars=yes" );
		
		if(width == "0" && height == "0")
		{
			RemindWindow.moveTo(0,0);
			RemindWindow.resizeTo(screen.availWidth,screen.availHeight);
		}
		RemindWindow.location.href = url;
	}
	else
	{
		RemindWindow.focus();
		RemindWindow.location.href = url;
	}
}


function AddItems(selectField,targetField)
{
	for(var i=0;i<selectField.options.length;i++)
	{	
		if(selectField.options[i].selected==true)
		{
			
			targetField.options[targetField.options.length]=new Option(selectField.options[i].text);
			targetField.options[targetField.options.length-1].value=selectField.options[i].value;
			selectField.options.remove(i);
		
		}
	}
}


function RemoveAllItems(targetField)
{

	targetField.options.length=0;

}


function RemoveItem(targetField)
{
	for(var i=0;i<targetField.options.length;i++)
	{	
		
		if(targetField.options[i].selected==true)
		{
			targetField.options[i]=null;
			
		}
	}
}


function getListBoxValue(targetTextBox,targetFieldName)
{
	var strValue = "";
	
	for(var i=0;i<document.all(targetFieldName).options.length;i++)
	{	
		if(strValue != "")
		{
			strValue += "|"
		}
		strValue += document.all(targetFieldName).options[i].value;
	}
	document.all(targetTextBox).value = strValue;

}


function ExitPage()
{
	if(window.confirm("Do you sure exit??"))
	{
		window.opener = null;
		window.close();
	}
}


function JumpToNextCell(id,strLen,NextTextId)
{
	if(id.value.length == strLen)
	{
		//????????????Tab????????
		if(event.keyCode != 9)
		{
			document.getElementById(NextTextId).focus() ;
		}
	}
}


function formatNumberToStand(str)
{
	var strNumber = "" ;
	var strPointNumber = "" ;

	while(str.indexOf(",") != -1)
	{
		str = str.replace(",","") ;
	}
	
	if(str.lastIndexOf(".") != -1)
	{
		strNumber = str.substring(0,str.lastIndexOf(".")) ;
		strPointNumber = str.substring(str.lastIndexOf(".")) ;
	}
	else
	{
		strNumber = str ;
	}
	if(strNumber.lastIndexOf(".") != -1)
	{
		alert("Please input right numeric");
		return false ;
	}
	var iLoopCount = 0 ;
	var iLen = strNumber.length ;
	
	while(iLen > 0)
	{
		if(iLen > 3)
		{
			iLoopCount ++ ;
		}
		iLen = iLen - 3 ;
	}
	
	for(var i=0;i<iLoopCount;i++)
	{
		strNumber = strNumber.substring(0,strNumber.length-(i+1)*3-i) + "," + strNumber.substring(strNumber.length-(i+1)*3-i) ;
		
	}

	var strAll = strNumber + strPointNumber ;

	return strAll ;

	
}


function formatNumberToDecimal(str)
{
	while(str.indexOf(",") != -1)
	{
		str = str.replace(",","") ;
	}
	return str ;
}


function formatString(TextObject)
{
	var strTmpValue = formatNumberToStand(TextObject.value) ;
	TextObject.value = strTmpValue ;
}


function setStandNumberToDecimal(strControlID)
{
	var strValue = document.getElementById(strControlID).value ;
	var strDecimal = formatNumberToDecimal(strValue) ;
	document.getElementById(strControlID).value = strDecimal ;
}


function CloneSelect(oldSelectObject)
{

	var newSelectObject = oldSelectObject.cloneNode();

	for(var i=0;i<oldSelectObject.options.length;i++)
	{
		var newOption = oldSelectObject.options[i].cloneNode();
		newOption.text = oldSelectObject.options[i].text;
		newOption.value = oldSelectObject.options[i].value;
		newSelectObject.add(newOption) ;
	}
	return newSelectObject ;
}


function findRow(e)
{
	if(e.tagName != "TR")
	{
		return findRow(e.parentElement);
	}
	else if(e.tagName == "BODY")
	{
		return null;
	}
	else
	{
		return e ;
	}
}


function findCell(e) 
{
  if (e.tagName == "TD")
  {
    return e;
  }
  else if(e.tagName == "BODY")
  {
    return null;
  }
  else
  {
    return findCell(e.parentElement);
  }
}

//Depend on table's id and checkbox's name to Delete checked Rows
/*
	Create By zhangjiang 2004-10-29
*/
function delRow(tableID,checkBoxName)
{
	var objBox = document.all(checkBoxName) ;
	if(objBox != null)
	{
		if(objBox.length != null)
		{
			for(var i=0;i<objBox.length;i++)
			{
				if(objBox[i].checked == true)
				{
					if( i == 0)
					{
						alert("The First Row Must Be Keep !") ;
						break ;
					}
					else
					{
						document.getElementById(tableID).deleteRow(i+1);
						delRow(tableID,checkBoxName);
					}
					
				}
			}
		}
		else
		{
			if(objBox.checked == true)
			{
				//document.getElementById(tableID).deleteRow(0);
				alert("The First Row Must Be Keep !") ;
			}
		}
	}
}


function CloneTable(objTable)
{
	var newTable = objTable.cloneNode() ;
	
	//var newTable = document.createElement("<table name='oTable' cellSpacing='0' cellPadding='0' border='0'></table>");
	
	for(var i=0;i<objTable.rows.length;i++)
	{
		var oldRow = objTable.rows[i] ;
		var newRow = oldRow.cloneNode() ;
		
		for(var j=0;j<oldRow.cells.length;j++)
		{
			var newCell = oldRow.cells[j].cloneNode() ;

			if(oldRow.cells[j].children.length > 0)
			{
				var newControl = oldRow.cells[j].children[0].cloneNode() ;
				if(newControl.tagName == "INPUT" || newControl.tagName == "TEXTAREA" )
				{
					newControl.value = "";
					if(newControl.readOnly == false)
					{
						newControl.readOnly = true ;
						newControl.style.backgroundColor = "#cccccc" ;
					}
				}
				newCell.appendChild(newControl);
			}
			else
			{
				newCell.innerHTML = oldRow.cells[j].innerHTML ;
			}
			
			newRow.appendChild(newCell) ;
		}
		
		newTable.appendChild(newRow) ;
		
	
	}

	return newTable ;
}




