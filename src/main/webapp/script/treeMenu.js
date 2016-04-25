var menu = {
	setting: {
        isSimpleData: true,
        treeNodeKey: "mid",
        treeNodeParentKey: "pid",
        showLine: true,
        root: {
            isRoot: true,
            nodes: []
        }
    },
	loadMenuTree:function(){
		$.post("elecMenuAction_showMenu.do",{},function(data){
			$("#menuTree").zTree(menu.setting, data);
		});
	}
};

$().ready(function(){
	menu.loadMenuTree();
});
