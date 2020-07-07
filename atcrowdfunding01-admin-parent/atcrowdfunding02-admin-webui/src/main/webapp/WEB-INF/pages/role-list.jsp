<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <jsp:include page="/WEB-INF/commons/head.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="pagination/pagination.css"/>
    <%--导入ztree对应的资源--%>
    <link rel="stylesheet" type="text/css" href="ztree/zTreeStyle.css" />
    <script type="text/javascript" src="js/my-role.js"></script>
    <script type="text/javascript">
        $(function(){
            // 准备数据  分页必要的几个数据   1、页码  2、页面的大小   3、keyWord
            window.pageNum = 1;
            window.pageSize = 10;
            window.keyWord = "";

            // 调用生成页面信息函数
            generatorPage();

            // 为 queryBtn 绑定单击响应函数
            $("#queryBtn").click(function(){
                // 获取 queryInput 元素的value值
                var keyWord = $("#queryInput").val();
                window.keyWord = keyWord;
                generatorPage();
            });

            // 为 insertBtn 绑定单击响应函数
            $("#insertBtn").click(function(){
                // 显示模态框
                $("#roleInputModal").modal('show');
            });

            // 为 saveBtn 绑定个单机响应函数
            $("#saveBtn").click(function(){
               // 点击保存按钮
               // 获取用户输入的角色信息
               var roleName = $("#roleInputModal [name=roleName]").val();
               // 发送Ajax请求，保存角色信息
                $.ajax({
                    "url" : "role/input.json",
                    "type" : "POST",
                    "data" : {"roleName" : roleName},
                    "dataType": "json",
                    "success" : function(response){
                        if(response.operationResult == "FAILED"){
                            layer.msg(response.operationMessage,{time:2000, icon:2, shift:6});
                            return ;
                        }
                        // 如果成功，
                        // 1、需要将模态框隐藏
                        $("#roleInputModal").modal('hide');
                        // 2、将模态框表单中用户输入的内容清空
                        $("#roleInputModal [name=roleName]").val('')
                        // 3、需要调用 generatorPage 函数重新加载页面信息
                        window.pageNum = 400000;
                        generatorPage();
                        // 4、注意，需要将用户插入的数据显示给用户。
                        // 分析得： 将页面切换到最后一页即可查看刚刚插入的角色信息
                    },
                    "error" : function(){
                        layer.msg("插入数据失败了~",{time:2000, icon:2, shift:6});
                    }
                });
            });

            // 为Class属性为 editBtn 的按钮绑定单击响应函数
            // $(".editBtn").click(function(){
            //
            // });
            // 以上方式进行响应事件的绑定对于使用ajax请求重新插入的元素而言不好用。
            // 可以使用 .on() 为对象绑定函数
            // 为插入元素的容器元素绑定on函数
            $("#roleTbody").on("click",".editBtn",function(){
                // alert("123")
                // 1、显示模态框
                $("#roleEditModal").modal('show');
                // 2、模态框表单中的 roleName 需要回显
                var roleName = $(this).parent().prev().text();
                $("#roleEditModal [name=roleName]").val(roleName);
                // // 获取角色id
                window.roleId = this.id;
            })

            $("#editBtn").click(function(){
                var roleName = $("#roleEditModal [name=roleName]").val();
                // 3、发送Ajax请求进行保存操作
                $.ajax({
                    "url" : "role/edit.json",
                    "type" : "POST",
                    "data" : {"id": window.roleId,"roleName" : roleName},
                    "dataType": "json",
                    "success" : function(response){
                        if(response.operationResult == "FAILED"){
                            layer.msg(response.operationMessage,{time:2000, icon:2, shift:6});
                            return ;
                        }
                        // 如果成功，
                        // 4、需要将模态框隐藏
                        $("#roleEditModal").modal('hide');
                        // 5、 模态框表单中用户输入的内容清空
                        $("#roleEditModal [name=roleName]").val('')
                        //  6、需要调用 generatorPage 函数重新加载页面信息
                        generatorPage();
                    },
                    "error" : function(){
                        layer.msg("插入数据失败了~",{time:2000, icon:2, shift:6});
                    }
                });
            });

            // 为 parentCkbox绑定单击响应函数
            $("#parentCkbox").click(function(){
                // 判断当前这个checkBox的选中状态，如果选中，childBox 都选中，反之一样
                // alert(this.checked)
                var flag = this.checked;
                var childBoxList = $(".childCkbox");
                $.each(childBoxList,function(index,obj){
                    obj.checked = flag
                })
            });

            // 当点击每一个checkBox时需要判断是否每一个都选中了，如果都选中让 parentCkbox 选中，反之则不选中
            $("#roleTbody").on("click",".childCkbox",function(){
                // 当点击时，判断每一个 childCkbox 选中状态，如果都选中 parentCkbox 就选中
                var childBoxList = $(".childCkbox");
                // 在遍历之前，将 parentCkbox 的选中状态置为 true
                $("#parentCkbox")[0].checked = true;
                $.each(childBoxList,function(index,obj){
                    if(!obj.checked){
                        $("#parentCkbox")[0].checked = false;
                    }
                })
            });

            // 为 deleteBtn 绑定一个单机响应函数
            $("#deleteBtn").click(function(){

                // 优化： 点击批量删除按钮，给用户一个提示，是否要删除这些信息。
                //  还要判断用户是否有选中的角色

                // 创建一个数组用于保存选中的roleId
                var roleNameList = [];
                // 获取每一个childCkbox对象
                var childBoxList = $(".childCkbox");
                $.each(childBoxList,function(index,obj){
                    if(obj.checked){
                        // 如果选中了 将 checkbox 的name属性获取，添加到 roleIdList 中
                        roleNameList.push(obj.alt);
                    }
                });

                // 还要判断用户是否有选中的角色
                if(roleNameList.length <= 0){
                    layer.msg("请勾选需要删除的角色！",{time:2000, icon:0, shift:6});
                    return;
                }
                // 给用户一个模态框，用户显示需要删除角色的 roleName
                var str = "";
                for(var i = 0; i <roleNameList.length; i++){
                    str = str + "<div style=\"margin:0 auto;text-align: center\">"+roleNameList[i]+"</div>"
                }
                $("#deleteRoleNameDiv").append(str);
                // 显示模态框
                $("#roleDeleteModal").modal('show');

               // deleteMoreRole(roleIdList);
            });

            // 为 dltBtn 绑定响应函数
            $("#dltBtn").click(function(){
                var roleIdList = [];
                // 获取每一个childCkbox对象
                var childBoxList = $(".childCkbox");
                $.each(childBoxList,function(index,obj){
                    if(obj.checked){
                        // 如果选中了 将 checkbox 的name属性获取，添加到 roleIdList 中
                        var id = obj.name;
                        roleIdList.push(id);
                    }
                });
                deleteMoreRole(roleIdList);
                // 关闭模态框，并将填入的数据清空
                $("#roleDeleteModal").modal('hide');
                $("#deleteRoleNameDiv").empty();
                // 重新加载页面
                generatorPage();
            });


            //单个删除
            $("#roleTbody").on("click",".deleteOneRole",function(){
                var roleName = $(this).parent().prev().text();
                //获取id的值
                var id = this.id;
                layer.confirm("您是否要删除"+roleName+"角色?",  {icon: 3, title:'提示'}, function(cindex){
                    // 将id封装成一个数组
                    var ids = [id];
                    deleteMoreRole(ids);
                    generatorPage();
                    layer.close(cindex);
                }, function(cindex){
                    layer.close(cindex);
                });
            })
            // 为 #assignRoleBtn 绑定单击响应函数
            $("#assignRoleBtn").click(function(){
                // 1、将 authTree 中选中的权限信息获取
                var treeObj = $.fn.zTree.getZTreeObj("authTree");

                var nodes = treeObj.getCheckedNodes(true);

                var authIds = [];
                for(var i = 0; i < nodes.length; i++ ){
                    authIds.push(nodes[i].id)
                }
                // 插入到数据库中的数据实际上只有权限的 id
                // 一、使用拼串的形式进行数据传送
                // var jsonStr = "roleId="+window.id;
                //
                // for( var i = 0; i < nodes.length; i++){
                //     jsonStr += "&authIds="+nodes[i].id
                // }
                // 二、使用json格式的数据传送复杂对象
                var jsonObj = {
                    authIds : authIds,
                    roleId : [window.id]
                }
                // 使用json数据的形式传送数据
                var jsonStr = JSON.stringify(jsonObj);

                // 2、将获取到的信息通过 ajax 请求发送到后台,插入到数据库中
                $.ajax({
                    "url" : "assign/do/assign/auth.json",
                    "type" : "post",
                    "data" : jsonStr,//需要角色 id   需要分配权限的 id
                    "contentType" : "application/json;charset=UTF-8",
                    "success": function(response){
                        if(response.operationResult == "FAILED"){
                            layer.msg(response.operationMessage,{time:2000, icon:2, shift:6});
                            return;
                        };
                        // 隐藏模态框
                        $("#assignRoleModal").modal('hide');
                        // 显示分配成功提示
                        layer.msg("分配权限成功！",{time:4000, icon:1, shift:3});
                    },
                    "error" : function(response){
                        layer.msg(response.operationMessage,{time:2000, icon:2, shift:6});
                    }
                });


                // 3、如果插入成功，关闭模态框，显示消息.
            });
        });

        // 用于将获取的id进行删除的函数
        function deleteMoreRole(roleIdList){
            var jsonStr = JSON.stringify(roleIdList);
            $.ajax({
                "url" : "role/delete.json",
                "type" : "post",
                "data" : jsonStr,
                "contentType" : "application/json",
                "dataType" : "json",
                "success" : function(response){
                    if(response.operationResult == "FAILED"){
                        layer.msg(response.operationMessage,{time:2000, icon:2, shift:6});
                        return ;
                    }
                    layer.msg("批量删除成功！",{time:2000, icon:1, shift:4});
                    // 重新调用 generatorPage 函数
                    generatorPage();
                },
                "error" : function(result){
                    layer.msg(result.operationMessage,{time:2000, icon:2, shift:6});
                }
            });
        }
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
        /*编写一个决绝高度塌陷的问题的样式*/
        .clearfix:before,.clearfix:after{
            content: "";
            display: table;
        }
        .clearfix:after{
            clear: both;
        }
        .clearfix{
            *zoom:1;
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
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="queryInput" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" id="queryBtn" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" id="deleteBtn" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" id="insertBtn" class="btn btn-primary" style="float:right;"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input id="parentCkbox" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="roleTbody">

                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="role-modal-input.jsp"></jsp:include>
<jsp:include page="role-modal-edit.jsp"></jsp:include>
<jsp:include page="role-modal-delete.jsp"></jsp:include>
<jsp:include page="modal-role-assign-auth.jsp"></jsp:include>
<script src="jquery/jquery-2.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="script/docs.min.js"></script>
<script src="layer/layer.js"></script>
<script type="text/javascript" src="pagination/jquery.pagination.js"></script>
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
