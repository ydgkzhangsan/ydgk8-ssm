// 生成树形菜单的函数
function generatorMenu(){
    // ztree 的一些设置
    var setting = {
        data: {
            key: {
                url: "xUrl"
            }
        },
        view: {
            // 制定是否可以同时选定多个
            selectedMulti: false,
            /*
            treeNode： 代表当前节点的json数据对象
             */
            addDiyDom: function (treeId, treeNode) {
                var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
                if (treeNode.ico) {
                    icoObj.removeClass("ico_open button ico_docu ").addClass(treeNode.ico).css("background", "");
                }
            },
            // 当鼠标移入到节点之后调用的函数
            addHoverDom: function(treeId, treeNode){
                var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")

                if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;

                var s = '<span id="btnGroup'+treeNode.tId+'">';

                if ( treeNode.level == 0 ) { // 如果是根节点 如何添加控件
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" id="'+treeNode.id+'" onclick="showAddMenu(this)" style="margin-left:10px;padding-top:0px;" href="javaScript:;" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                } else if ( treeNode.level == 1 ) { // 如果是分支节点 如何添加控件
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" id="'+treeNode.id+'" onclick="showEditModal(this)" style="margin-left:10px;padding-top:0px;"  href="javaScript:" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                    if (treeNode.children.length == 0) { // 判断是否有子节点，如果没有子节点，则该节点可以删除
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" id="'+treeNode.id+'" onclick="showDeleteModal(this)" style="margin-left:10px;padding-top:0px;" href="javaScript:" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                    }
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" id="'+treeNode.id+'" onclick="showAddMenu(this)" style="margin-left:10px;padding-top:0px;" href="javaScript:" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                } else if ( treeNode.level == 2 ) { // 如果是叶子节点，如何添加控件
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" id="'+treeNode.id+'" onclick="showEditModal(this)" style="margin-left:10px;padding-top:0px;"  href="javaScript:" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" id="'+treeNode.id+'" onclick="showDeleteModal(this)"  style="margin-left:10px;padding-top:0px;" href="javaScript:">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                }

                s += '</span>';
                aObj.after(s);
            },
            //当鼠标移除之后执行的函数
            removeHoverDom: function(treeId, treeNode){
                // 将加入的控件移除
                $("#btnGroup"+treeNode.tId).remove();
            }

        }
    };
    // 获取数据 通过ajax
    $.ajax({
        "url" : "menu/getAll.json",
        "type" : "get",
        "dataType" : "json",
        "success" : function(response){
            if(response.operationResult == "FAILED"){
                layer.msg(response.operationMessage,{time:2000, icon:2, shift:6});
                return;
            }
            if(response.operationResult == "SUCCESS"){
                // 初始化ztree的js代码
                $.fn.zTree.init($("#treeDemo"), setting,response.queryData);
            }
        },
        "error" : function(response){
            layer.msg(response.operationMessage,{time:2000, icon:2, shift:6});
        }
    })
}

function showAddMenu(obj){
    // 1、显示模态框
    $("#menuAddModal").modal('show');
    // 2、获取当前菜单的id值
    window.id = obj.id;
}

function showDeleteModal(obj){
    // 1、显示模态框
    $("#menuConfirmModal").modal('show');
    // 2、获取当前菜单的id值
    window.id = obj.id;
}

function showEditModal(obj){
    // 1、显示模态框
    $("#menuEditModal").modal('show');
    // 2、数据的回显  数据的获取
    window.id = obj.id;

    // 获取数据
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    var node = treeObj.getNodeByParam("id", window.id, null);

    // 为模态框中赋值
    $("#menuEditModal [name=name]").val(node.name);
    $("#menuEditModal [name=url]").val(node.url);
    // 为单选按钮赋值，  注意，使用val()为单选按钮或复选框赋值时需要传入一个数组对象
    $("#menuEditModal [name=icon]").val([node.ico]);

}

