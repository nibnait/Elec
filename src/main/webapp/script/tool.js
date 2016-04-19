a="";
		NS4 = (document.layers) ? 1 : 0;
		IE4 = (document.all) ? 1 : 0;
		ver4 = (NS4 || IE4) ? 1 : 0;

		if (ver4) {
			with (document) {
				write("<STYLE TYPE='text/css'>");
				if (NS4) {
					write(".parent {position:absolute; visibility:visible}");
					write(".child {position:absolute; visibility:visible}");
					write(".regular {position:absolute; visibility:visible}")
				}
				else {
					write(".child {display:none}")
				}
				write("</STYLE>");
			}
		}
		function getIndex(el) {
			ind = null;
			for (i=0; i<document.layers.length; i++) {
				whichEl = document.layers[i];
				if (whichEl.id == el) {
					ind = i;
					break;
				}
			}
			return ind;
		}
		function arrange() {
			nextY = document.layers[firstInd].pageY +document.layers[firstInd].document.height;
			for (i=firstInd+1; i<document.layers.length; i++) {
				whichEl = document.layers[i];
				if (whichEl.visibility != "hide") {
					whichEl.pageY = nextY;
					nextY += whichEl.document.height;
				}
			}
		}

		function initIt(){
			if (!ver4) return;
			if (NS4) {
				for (i=0; i<document.layers.length; i++) {
					whichEl = document.layers[i];
					if (whichEl.id.indexOf("Child") != -1) whichEl.visibility = "hide";
			}
				arrange();
			}
			else {
				divColl = document.all.tags("DIV");
				for (i=0; i<divColl.length; i++) {
					whichEl = divColl(i);
					if (whichEl.className == "child") whichEl.style.display = "none";
				}
			}
		}
		function expand(bb)
		{
			
		if(a!="")
		{
		expandIt(a)
		}

		expandIt(bb)

//		a=bb;

		}
		function expandIt(el) {
			if (!ver4) return;
			if (IE4) {
				whichEl = eval(el + "Child");
				//whichimg = eval("img" + el)
				if (whichEl.style.display == "none") {
					whichEl.style.display = "block";
					//whichimg.src="images/open.gif";
				}
				else {
					whichEl.style.display = "none";
					//whichimg.src="images/add.gif";
				}
			}
			else {
				whichEl = eval("document." + el + "Child");
				if (whichEl.visibility == "hide") {
					whichEl.visibility = "show";
				}
				else {
					whichEl.visibility = "hide";
				}
				arrange();
			}
		}
		function linkcolorchange(objLink)
		{
			for(var i=0;i<document.links.length;i++)
			{
				document.links[i].style.color = "" ;
			}
				objLink.style.color = "red" ;
		}
		function backgroundColorChange(objLink,strColor)
		{
			objLink.style.backgroundColor = strColor ;
		}

		onload = initIt;
				function divinit(){
			if (!ver4) return;
			if (NS4) {
				for (i=0; i<document.layers.length; i++) {
					whichEl = document.layers[i];
					if (whichEl.id.indexOf("Child") != -1) whichEl.visibility = "hide";
			}
				arrange();
			}
			else {
				divColl = document.all.tags("DIV");
				
				for (i=0; i<divColl.length; i++) {
					whichEl = divColl(i);
					if (whichEl.className == "child"){ 
					
					  whichEl.style.display = "none";
					  var index =eval(whichEl.id+" ");
					  index = whichEl.id.substring(0,3);

					  expand(index);
					  if(i!=0)
					  expand(index);
					}
				}
			
			}

		}