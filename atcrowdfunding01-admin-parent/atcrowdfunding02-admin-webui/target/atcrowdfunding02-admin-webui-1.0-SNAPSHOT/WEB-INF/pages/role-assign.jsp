<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <jsp:include page="/WEB-INF/commons/head.jsp"></jsp:include>
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
    <script type="text/javascript">
        $(function(){
            // 为 rightToLeft 和 leftToRight 绑定单击响应函数
            $("#leftToRight").click(function(){
                // 获取左边选中的 option
                // eq(0) 表示选中第一个 select
                //selected 表示选中选中对的option
                // prepend() 表示将选中的元素添加到指定的对象中
                // prependTo()
                // append
                // appendTo
                $("select:eq(0)>option:selected").appendTo($("[name=assignedRoleIds]"));
            });

            $("#rightToLeft").click(function(){
                $("[name=assignedRoleIds]>option:selected").appendTo($("select:eq(0)"));
            });

            $("#assignBtn").click(function(){
                // 在提交表单时，需要将所有已分配的option选中，然后再提交
                $("select:eq(1)>option").attr("selected","selected");
            });
        });
    </script>
</head>

<body>

<jsp:include page="/WEB-INF/commons/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="/WEB-INF/commons/sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form id="assignForm" action="assign/do/assign/role.html" method="post" role="form" class="form-inline">
                        <div class="form-group">
                            <label>未分配角色列表</label><br>
                            <select class="form-control" multiple="" size="10" style="width:200px;overflow-y:auto;">
                                <c:forEach items="${requestScope.unassignedList}" var="role">
                                    <option value="${role.id}">${role.roleName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="leftToRight" class="btn btn-default glyphicon glyphicon-chevron-right" ></li>
                                <br>
                                <li id="rightToLeft" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                            </ul>
                        </div>

                        <input type="hidden" name="adminId" value="${requestScope.adminId}" />
                        <input type="hidden" name="pageNum" value="${requestScope.pageNum}" />
                        <input type="hidden" name="keyWord" value="${requestScope.keyWord}" />
                        <div class="form-group" style="margin-left:0px;">
                            <label >已分配角色列表</label><br>
                            <select name="assignedRoleIds" class="form-control" multiple=""  size="10" style="width:200px;overflow-y:auto;">
                                <c:forEach items="${requestScope.assignedList}" var="role">
                                    <option value="${role.id}" >${role.roleName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button id="assignBtn" type="submit" class="btn btn-success">分配角色</button>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<script src="jquery/jquery-2.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="script/docs.min.js"></script>
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
