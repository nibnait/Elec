/*-------------------------------------------\
|      Simple Cross Browser Menu Script      |
|--------------------------------------------|
|        Author:        Emil A. Eklund       |
|        First Created: May 19, 2000         |
|        Last Updated:  Aug 17, 2000         |
|--------------------------------------------|
|     Created to work with ie4+ and ns4+     |
\-------------------------------------------*/

menuPrefix = 'menu';  // Prefix that all menu layers must start with
                      // All layers with this prefix will be treated
                      // as a part of the menu system.

var menuTree, mouseMenu, hideTimer, doHide;

function init() {
  ie4 = (document.all)?true:false;
  ns4 = (document.layers)?true:false;
  document.onmousemove = mouseMove;
  if (ns4) { document.captureEvents(Event.MOUSEMOVE); }
}

function expandMenu(menuContainer,subContainer,menuLeft,menuTop) {
	// Hide all submenus thats's not below the current level
	doHide = false;
  if (menuContainer != menuTree) {
	  if (ie4) {
      var menuLayers = document.all.tags("DIV");
      for (i=0; i<menuLayers.length; i++) {
        if ((menuLayers[i].id.indexOf(menuContainer) != -1) && (menuLayers[i].id != menuContainer)) {
          hideObject(menuLayers[i].id);
        }
      }
    }
    else if (ns4) {
      for (i=0; i<document.layers.length; i++) {
        var menuLayer = document.layers[i];
        if ((menuLayer.id.indexOf(menuContainer) != -1) && (menuLayer.id != menuContainer)) {
          menuLayer.visibility = "hide";
        }
      }
    }
  }
  // If this is item has a submenu, display it, or it it's a toplevel menu, open it
  if (subContainer) {
    if ((menuLeft) && (menuTop)) {
    	positionObject(subContainer,menuLeft,menuTop);
    	hideAll();
    }
    else {
      if (ie4) {
      	positionObject(subContainer, document.all[menuContainer].offsetWidth + document.all[menuContainer].style.pixelLeft - 10, mouseY);
      }
      else {
      	positionObject(subContainer, document.layers[menuContainer].document.width + document.layers[menuContainer].left + 50, mouseY);
      }
    }
    showObject(subContainer);
    menuTree = subContainer;
  }
}

function showObject(obj) {
  if (ie4) { document.all[obj].style.visibility = "visible"; }
  else if (ns4) { document.layers[obj].visibility = "show";  }
}

function hideObject(obj) {
  if (ie4) { document.all[obj].style.visibility = "hidden"; }
  else if (ns4) { document.layers[obj].visibility = "hide"; }
}

function positionObject(obj,x,y) {
  if (ie4) {
    var foo = document.all[obj].style;
    foo.left = x;
    foo.top = y;
  }
  else if (ns4) {
    var foo = document.layers[obj];
    foo.left = x;
    foo.top = y;
   }
}

function hideAll() {
 if (ie4) {
    var menuLayers = document.all.tags("DIV");
    for (i=0; i<menuLayers.length; i++) {
      if (menuLayers[i].id.indexOf(menuPrefix) != -1) {
        hideObject(menuLayers[i].id);
      }
    }
  }
  else if (ns4) {
    for (i=0; i<document.layers.length; i++) {
      var menuLayer = document.layers[i];
      if (menuLayer.id.indexOf(menuPrefix) != -1) {
        hideObject(menuLayer.id);
      }
    }
  }
}

function hideMe(hide) {
	if (hide) {
		if (doHide) { hideAll(); }
	}
	else {
		doHide = true;
		hideTimer = window.setTimeout("hideMe(true);", 2000);
	}
}

function mouseMove(e) {
  if (ie4) { mouseY = window.event.y; }
  if (ns4) { mouseY = e.pageY; }
}

function itemHover(obj,src,text,style) {
  if (ns4) {
    var text = '<nobr><a href="' + src + '" class="' + style + '">' + text + '<\/a><\/nobr>'
    obj.document.open();
    obj.document.write(text);
    obj.document.close();
  }
}

onload = init;