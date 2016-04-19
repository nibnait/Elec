/********************************************************************************************

* BlueShoes Framework; This file is part of the php application framework.

* NOTE: This code is stripped (obfuscated). To get the clean documented code goto 

*       www.blueshoes.org and register for the free open source *DEVELOPER* version or 

*       buy the commercial version.

*       

*       In case you've already got the developer version, then this is one of the few 

*       packages/classes that is only available to *PAYING* customers.

*       To get it go to www.blueshoes.org and buy a commercial version.

* 
*/
/**火狐支持begin*/
if(typeof HTMLElement!="undefined" && !HTMLElement.prototype.insertAdjacentElement) 
{ 
     HTMLElement.prototype.insertAdjacentElement = function(where,parsedNode) 
     { 
        switch (where) 
        { 
            case 'beforeBegin': 
                this.parentNode.insertBefore(parsedNode,this) 
                break; 
            case 'afterBegin': 
                this.insertBefore(parsedNode,this.firstChild); 
                break; 
            case 'beforeEnd': 
                this.appendChild(parsedNode); 
                break; 
            case 'afterEnd': 
                if (this.nextSibling) this.parentNode.insertBefore(parsedNode,this.nextSibling); 
                    else this.parentNode.appendChild(parsedNode); 
                break; 
         } 
     } 
     HTMLElement.prototype.insertAdjacentHTML = function (where,htmlStr) 
     { 
         var r = this.ownerDocument.createRange(); 
         r.setStartBefore(this); 
         var parsedHTML = r.createContextualFragment(htmlStr); 
         this.insertAdjacentElement(where,parsedHTML) 
     } 

     HTMLElement.prototype.insertAdjacentText = function (where,txtStr) 
     { 
         var parsedText = document.createTextNode(txtStr) 
         this.insertAdjacentElement(where,parsedText) 
     } 
}
/**火狐支持end*/

if (!Bs_Objects) {
	var Bs_Objects = [];
};
function bs_lt_checkLength() {
	event.srcElement.bsObj.checkLength();
	event.srcElement.bsObj._updateBgColorWarning();
	event.srcElement.bsObj._updateProgressBar();
}
function returnFalse(){
	return false;
}
function getElement(nameOrId,index){
  var obj=null;
  try{
  var i=parseInt(index);
  if(typeof(nameOrId)!='string') 
       return nameOrId;
  if(!obj && document.getElementsByName) 
       obj=document.getElementsByName(nameOrId)[i];
  if(!obj && document.getElementById) 
       obj=document.getElementById(nameOrId);
  if(!obj && document.all) 
       obj=document.all[nameOrId];
  
  return obj;
  }catch(exx){alert(exx.message);}
}

function Bs_LimitedTextarea(elementId, maxLength) {
	var a = arguments;
	var arrayIndex=0;
	if (a.length==2) {
		elementId = a[0];
		maxLength = a[1];
        arrayIndex = 0;
	}
	if (a.length==3) {
		elementId = a[0];
		maxLength = a[1];
	    arrayIndex = a[2];
	}
	this._objectId;
	this.infolineText = '\u5269\u4f59\u5b57\u6570:';
	this.infolineCssClass;
	this.infolineCssStyle;
	this.numberCssClass;
	this.numberCssStyle;
	this._elementId  = elementId;
	this._element    = getElement(elementId,arrayIndex);//document.getElementById(elementId);modify by  shb
	this._maxLength = maxLength;
	this.useProgressBar = false;
	this._lastNumProgressBars
	this._constructor = function() {
		  this._id = Bs_Objects.length;
		  Bs_Objects[this._id] = this;
		  this._objectId = "Bs_LimitedTextarea_"+this._id;
	}
	this.draw = function() {
			if (isNaN(this._maxLength)) return;
			if (this._maxLength <= 0) return;
			this._element.bsObj = this;
			try {
				var htmlStr = '<br>';
				if (this.useProgressBar) {
					htmlStr += '<div id="' + this._objectId + '_progess" style="overflow:hidden; width:' ;
					htmlStr += this._element.offsetWidth + 'px; height:18px; border:2px inset;';
					htmlStr += '">';
					htmlStr += '<img src="/_bsImages/spacer.gif" width="1" height="1" border="0">';
					htmlStr += '</div>';
				}
				htmlStr += '<div';
				if (typeof(this.infolineCssClass) == 'string') 
					htmlStr += ' class="' + this.infolineCssClass + '"';
				if (typeof(this.infolineCssStyle) == 'string')
					htmlStr += ' style="' + this.infolineCssStyle + '"';
				htmlStr += '>' + this.infolineText + ' <div ';
				htmlStr += ' style="display:inline;';
				if (typeof(this.numberCssStyle) == 'string')
					htmlStr += this.numberCssStyle;
				htmlStr += '"';
				if (typeof(this.numberCssClass) == 'string') 
					htmlStr += ' class="' + this.numberCssClass + '"';
				htmlStr += 'id="' + this._objectId + '_no"></div>';
				htmlStr += '</div>';
				this._element.insertAdjacentHTML('afterEnd', htmlStr);
				} catch (e) {}
		try {
			/**火狐浏览器*/
			if(typeof window.addEventListener==="function")
			{
				this._element.addEventListener('change', bs_lt_checkLength,false);
				this._element.addEventListener('keyup',  bs_lt_checkLength,false);
				this._element.addEventListener('paste',  bs_lt_checkLength,false);
				this._element.addEventListener('drag',  bs_lt_checkLength,false);
				this._element.addEventListener('dragenter',returnFalse,false);
			}
			/**IE浏览器*/
			else
			{
				this._element.attachEvent('onchange', bs_lt_checkLength);
				this._element.attachEvent('onkeyup',  bs_lt_checkLength);
				this._element.attachEvent('onpaste',  bs_lt_checkLength);
				this._element.attachEvent('ondrag',  bs_lt_checkLength);
				this._element.attachEvent('ondragenter',returnFalse);
			} 
			
			} catch (e) {}
		this.checkLength();
		this._updateBgColorWarning();
		this._updateProgressBar();
        }
this.setValue = function(val) {
		this._element.value = val;
		this.checkLength();
}
this.getValue = function() {
		this.checkLength();
		return this._element.value;
}
this.setMaxLength = function(maxLength) {
	this._maxLength = maxLength;this.checkLength();
}
this.getMaxLength = function() {
	return this._maxLength;
}
this.getCurrentLength = function() {
	return this._element.value.length;
}
this.checkLength = function() {
	if (this._element.value.length > this._maxLength)
		this._element.value = this._element.value.substr(0, this._maxLength);
	try {
		document.getElementById(this._objectId + '_no').innerHTML = this._maxLength - this._element.value.length;
	} catch (e) {}
}
this.setBgColorWarning = function(kickInValue, kickInType, endColor) {
	if (typeof(hexdec) == 'undefined') {
		alert("Webmaster: please include the library: /_bsJavascript/core/gfx/Bs_ColorUtil.lib.js. Otherwise the 'setBgColorWarning()' feature won't work.");
	}
	this._bgColorWarning = new Object;if (bs_isNull(kickInValue)) {
		kickInValue = 80;
		kickInType  = '%';
	}
	if ((kickInType == '%') || (bs_isNull(kickInType))) {
		kickInValue = parseInt(this._maxLength / 100 * kickInValue);
	}
	this._bgColorWarning.kickInValue = kickInValue;
	this._bgColorWarning.endColor    = (!bs_isEmpty(endColor)) ? endColor : 'FF4040';
	if (moz) {
		if (!bs_isEmpty(this._element.currentStyle.backgroundColor)) {
			var startColor = this._element.currentStyle.backgroundColor;
		} else if (!bs_isEmpty(this._element.style.backgroundColor)) {
			var startColor = this._element.style.backgroundColor;
		}
		if ((typeof(startColor) != 'undefined') && (startColor.substr(0, 4) == 'rgb(')) {
			var csvString = startColor.substring(4, startColor.length -1);
			var csvJunks  = csvString.split(', ');
			startColor = '';
			for (var i=0; i<3; i++) {
				startColor += parseInt(csvJunks[i]).toString(16);
			}
		}
	} else {
		try {
			var startColor = this._element.currentStyle.backgroundColor;
		} catch (e) {
			try {
				var startColor = this._element.style.backgroundColor;
			} catch (ee) {
				var startColor = 'ffffff';
			}
		}
	}
	if (typeof(startColor) != 'string') startColor = 'ffffff';
	if (startColor.substr(0,1) == '#') startColor = startColor.substr(1);
	if (startColor.length != 6) startColor = 'ffffff';
	this._bgColorWarning.startColor = startColor;
}
this._updateBgColorWarning = function() {
	if (typeof(this._bgColorWarning) == 'undefined') return;
	var startWarningAt = this._bgColorWarning.kickInValue;
	var startColor = this._bgColorWarning.startColor;
	var endColor   = this._bgColorWarning.endColor;
	var startRed   = hexdec(startColor.substr(0,2));
	var startGreen = hexdec(startColor.substr(2,2));
	var startBlue  = hexdec(startColor.substr(4,2));
	var endRed     = hexdec(endColor.substr(0,2));
	var endGreen   = hexdec(endColor.substr(2,2));
	var endBlue    = hexdec(endColor.substr(4,2));
	try {
		var lengthNow = this._element.value.length;
		lengthUse = lengthNow - startWarningAt;
		if (lengthUse <= 0) {
			var color = startColor;
		} else {
			var percent = lengthUse * 100 / (this._maxLength - startWarningAt);
			var newRed   = this._mixColor(startRed,   endRed,   percent);
			var newGreen = this._mixColor(startGreen, endGreen, percent);
			var newBlue  = this._mixColor(startBlue,  endBlue,  percent);
			var color = '#' + newRed.toString(16) + newGreen.toString(16) + newBlue.toString(16);
		}
		this._element.style.backgroundColor = color;
	} catch (e) {}
}
this._mixColor = function(startColor, endColor, percent) {
	var diffColor = endColor - startColor;
	if (diffColor > 0) {
		return startColor + parseInt(diffColor / 100 * percent);
	} else if (diffColor < 0) {
		return startColor + parseInt(diffColor / 100 * percent);
	} else {
		return startColor;
	}
}
this._updateProgressBar = function() {
	if (!this.useProgressBar) return;
	var progressDiv   = document.getElementById(this._objectId + '_progess');
	var progressWidth = progressDiv.offsetWidth;var totalBars     = parseInt(progressWidth / 7);
	var lengthNow = this._element.value.length;
	if (lengthNow == 0) {
		var numBars = 0;
	} else {
		var percent = lengthNow * 100 / this._maxLength;
		var numBars = Math.ceil(totalBars / 100 * percent);
	}
	if (this._lastNumProgressBars == numBars) return;
	this._lastNumProgressBars = numBars;
	var newHtml       = '<img src="/_bsImages/spacer.gif" width="1" height="1" border="0">';
	var progressJunk  = '<div style="display:inline; width:5px; height:8px; background-color:#08246B; margin-top:3px; margin-bottom:3px; margin-left:1px; margin-right:1px;"><img src="/_bsImages/spacer.gif" width="5" height="8" border="0"></div>';
	for (var i=0; i<numBars; i++) {
		newHtml += progressJunk;
	}
	var progressDiv   = document.getElementById(this._objectId + '_progess');
	progressDiv.innerHTML = newHtml;
}
this._constructor();
}
