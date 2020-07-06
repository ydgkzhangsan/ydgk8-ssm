<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <jsp:include page="/WEB-INF/commons/head.jsp"></jsp:include>
    <%--导入ztree对应的资源--%>
    <link rel="stylesheet" type="text/css" href="ztree/zTreeStyle.css" />
    <script>


        // 指定 zTree 的一些节点信息
        // var zNodes =[
        //     { name:"父节点1 - 展开", open:false,
        //         children: [
        //             { name:"父节点11 - 折叠",
        //                 children: [
        //                     { name:"叶子节点111"},
        //                     { name:"叶子节点112"},
        //                     { name:"叶子节点113"},
        //                     { name:"叶子节点114"}
        //                 ]},
        //             { name:"父节点12 - 折叠",
        //                 children: [
        //                     { name:"叶子节点121"},
        //                     { name:"叶子节点122"},
        //                     { name:"叶子节点123"},
        //                     { name:"叶子节点124"}
        //                 ]},
        //             { name:"父节点13 - 没有子节点", isParent:true}
        //         ]}
        // ];

        // $(document).ready(function(){}); 代表页面加载完毕之后执行function函数
        $(document).ready(function(){
            generatorMenu();

            // 点击保存按钮
            $("#menuSaveBtn").click(function(){
                // ①、获取用户输入的数据  name   url   ico   pId(当前节点的id)
                var name = $("#menuAddModal [name=name]").val();
                var url = $("#menuAddModal [name=url]").val();
                var ico = $("#menuAddModal [name=icon]:checked").val();
                // ②、发送ajax请求执行保存操作
                $.ajax({
                    "url" : "menu/save.json",
                    "type" : "POST",
                    "data" : {
                        "name" : name,
                        "url" : url,
                        "ico" : ico,
                        "pId" : window.id
                    },
                    "success" : function(response){
                        if(response.operationResult == "FAILED"){
                            layer.msg(response.operationMessage,{time:2000, icon:2, shift:6});
                            return;
                        }
                        if(response.operationResult == "SUCCESS"){
                            // 给用户一个提示
                            layer.msg("新增菜单成功",{time:2000, icon:1, shift:4})
                            // ③、需要清空用户输入的数据
                            // 可以使用jquery调用reset按钮达到重置的效果
                            $("#menuResetBtn").click();
                            // ④、隐藏模态框
                            $("#menuAddModal").modal('hide')
                            // ⑤、从新加载整棵树
                            generatorMenu();
                        }
                    },
                    "error" : function(response){
                        layer.msg(response.operationMessage,{time:2000, icon:2, shift:6});
                    }
                });

            });

            // 为 confirmBtn 绑定响应函数
            $("#confirmBtn").click(function(){
                $.ajax({
                    "url" : "menu/delete.json",
                    "type" : "get",
                    "data" : {
                        id : window.id
                    },
                    "dataType" : "json",
                    "success" : function(response){
                        if(response.operationResult == "FAILED"){
                            layer.msg(response.operationMessage,{time:2000, icon:2, shift:6});
                            return;
                        }
                        if(response.operationResult == "SUCCESS"){
                            // 给用户一个提示
                            layer.msg("删除菜单成功",{time:2000, icon:1, shift:4})
                            $("#menuConfirmModal").modal('hide')
                            // ⑤、从新加载整棵树
                            generatorMenu();
                        }
                    },
                    "error" : function(response){
                        layer.msg(response.operationMessage,{time:2000, icon:2, shift:6});
                    }
                })
            });

            //为 # menuEditBtn 绑定响应函数
            $("#menuEditBtn").click(function(){
                //1、获取用户输入的数据
                var name = $("#menuEditModal [name=name]").val();
                var url = $("#menuEditModal [name=url]").val();
                var icon = $("#menuEditModal [name=icon]").val();

                //2、提交数据到后台进行修改
                $.ajax({
                    "url" : "menu/edit.json",
                    "type" : "post",
                    "data" : {
                        "id" : window.id,
                        "name" : name,
                        "url" : url,
                        "ico" : icon
                    },
                    "dataType" : "json",
                    "success" : function(response){
                        if(response.operationResult == "FAILED"){
                            layer.msg(response.operationMessage,{time:2000, icon:2, shift:6});
                            return;
                        }
                        if(response.operationResult == "SUCCESS"){
                            //3、如果成功需要弹出提示框
                            layer.msg("修改菜单成功",{time:2000, icon:1, shift:4})
                            //4、 关闭模态框
                            $("#menuEditModal").modal('hide')
                            //6、 重新加载整颗树
                            generatorMenu();
                        }
                    },
                    "error" : function(response){
                        layer.msg(response.operationMessage,{time:2000, icon:2, shift:6});
                    }
                })

            });

        });
    </script>

    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }

        .tree-closed {
            height: 40px;
        }

        .tree-expanded {
            height: auto;
        }
    </style>
</head>

<body>

<jsp:include page="/WEB-INF/commons/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="/WEB-INF/commons/sidebar.jsp"></jsp:include>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表 <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <%--使用ztree生成树的位置--%>
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%--导入模态框--%>
<jsp:include page="modal-menu-add.jsp"></jsp:include>
<jsp:include page="modal-menu-confirm.jsp"></jsp:include>
<jsp:include page="modal-menu-edit.jsp"></jsp:include>
<script src="jquery/jquery-2.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="script/docs.min.js"></script>
<script src="js/my-menu.js"></script>
<script src="layer/layer.js"></script>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function () {
            if ($(this).find("ul")) {
                $(this).toggleClass("tree-closed");
                if ($(this).hasClass("tree-closed")) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
    });
</script>
</body>
</html>
