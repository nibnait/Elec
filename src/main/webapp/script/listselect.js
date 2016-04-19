var MainSel  = null;
var SlaveSel = null;
var Item_org = new Array();

function DoAdd(){
	var this_sel = null;
	for(var i=0;i<MainSel.options.length;i++){
		this_sel = MainSel.options[i];
		if(this_sel.selected){
			SlaveSel.appendChild(this_sel);
			i--;
		}
	}
	sort_Main(SlaveSel);
}

function DoDel(){
	var this_sel = null;
	for(var i=0;i<SlaveSel.options.length;i++){
		this_sel = SlaveSel.options[i];
		if(this_sel.selected){
			MainSel.appendChild(this_sel);
			i--;
		}
	}
	sort_Main(MainSel);
}

function sort_Main(the_Sel){
	var this_sel = null;
	for(var i=0;i<Item_org.length;i++){
		for(var j=0;j<the_Sel.options.length;j++){
			this_sel = the_Sel.options[j];
			if(this_sel.value==Item_org[i][0] && this_sel.text==Item_org[i][1]){
				the_Sel.appendChild(this_sel);
			}
		}
	}
}

window.onload=function(){
	MainSel  = document.form1.select1;
	SlaveSel = document.form1.select2;
	MainSel.ondblclick=DoAdd;
	SlaveSel.ondblclick=DoDel;
	var this_sel = null;
	for(var i=0;i<MainSel.options.length;i++){
		this_sel = MainSel.options[i];
		Item_org.push(new Array(this_sel.value,this_sel.text));
	}
}