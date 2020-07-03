// 在这个 js 文件中，会创建生成页面的函数，方便role-list.jsp使用
// 生成页面的函数调用以下函数
function generatorPage(){
    // 获取数据
    var rolePageInfo = getRoleInfoByRemote()
    // 根据获取的数据填充表格
    fullTable(rolePageInfo);
}

// 第一个函数  获取数据的函数
function getRoleInfoByRemote(){
    var roleList = null;
    var ajaxResult = $.ajax({
        "url": "role/get/role-list.json",
        "type" : "post",
        "data" : {
            "keyWord" : window.keyWord,
            "pageNum" : window.pageNum,
            "pageSize": window.pageSize
        },
        "async" : false, // 发送的请求是一个异步的请求
        "dataType" : "json"//指定返回数据的类型
    });
    var result = ajaxResult.responseJSON;
    if(ajaxResult.status != 200){
        // 使用 layer 进行弹窗
        //alert("1234")
        /*
        time属性： 指定弹窗的出现时间
        icon属性： 指定弹窗显示的图标
        shift属性： 指定弹窗的动作
         */
        layer.msg("获取数据失败，请联系管理员！",{time:2000, icon:2, shift:6});
        return;
    }
    // 请求没有问题
    // 请求状态码是200不一定就有数据，可能后台代码出现异常。
    if(result.operationResult == "FAILED"){
        layer.msg("获取数据失败，请联系管理员！",{time:2000, icon:2, shift:6});
        return;
    }
    return result.queryData;
}

// 填充表格的函数
function fullTable(rolePageInfo){
    $("#roleTbody").empty();
    // 获取tbody对象
    var str = "";
    for(var i = 0; i <rolePageInfo.list.length; i++){
        var role = rolePageInfo.list[i];
        var roleId = role.id;
        var roleName = role.roleName;
        str = str + " <tr>" +
            "                                <td>1</td>" +
            "                                <td><input type=\"checkbox\"></td>" +
            "                                <td>"+roleName+"</td>" +
            "                                <td>" +
            "                                    <button type=\"button\" class=\"btn btn-success btn-xs\"><i class=\" glyphicon glyphicon-check\"></i></button>" +
            "                                    <button type=\"button\" class=\"btn btn-primary btn-xs\"><i class=\" glyphicon glyphicon-pencil\"></i></button>" +
            "                                    <button type=\"button\" class=\"btn btn-danger btn-xs\"><i class=\" glyphicon glyphicon-remove\"></i></button>" +
            "                                </td>" +
            "                            </tr>"
    }
    $("#roleTbody").append(str);

    // 初始化导航条
    var maxentries = rolePageInfo.total;
    $("#Pagination").pagination(maxentries,{
        num_edge_entries: 3,                    //边缘页数
        num_display_entries: 5,                 //主体页数
        callback: pageSelectCallback,
        items_per_page:10 ,                       //每页显示10项
        current_page: rolePageInfo.pageNum - 1, // 当前页数
        prev_text: "上一页",
        next_text: "下一页"
    })
}

// pagination 回调函数   点击分页按钮时触发的
function pageSelectCallback(pageNum,jquery){
    // 切换页码
    window.pageNum = pageNum + 1;
    generatorPage();
    // 取消分页的默认行为
    return false;
}















//
// function getRoleInfoByRemote(){
//     var roleList = null;
//     var ajaxResult = $.ajax({
//         "url": "role/get/role-list.json",
//         "type" : "post",
//         "data" : {
//             "keyWord" : window.keyWord,
//             "pageNum" : window.pageNum,
//             "pageSize": window.pageSize
//         },
//         "async" : false, // 发送的请求是一个异步的请求
//         "dataType" : "json",//指定返回数据的类型
//         "success" : function(response){
//             console.log(response)
//             if(response.operationResult == "FAILED"){
//                 layer.msg("获取数据失败，请联系管理员！",{time:2000, icon:2, shift:6});
//                 return;
//             }
//             roleList =  response.queryData;
//         },
//         "error" : function(response){
//             layer.msg("获取数据失败，请联系管理员！",{time:2000, icon:2, shift:6});
//         }
//     });
//     console.log(typeof ajaxResult)
//     console.log(ajaxResult.status)
//
//     return roleList;
//     // var ajaxResult = JSON.parse(ajaxResult.toString());
//     // console.log(ajaxResult.status);
//     // if(ajaxResult.status != 200){
//     //     // 使用 layer 进行弹窗
//     //     //alert("1234")
//     //     /*
//     //     time属性： 指定弹窗的出现时间
//     //     icon属性： 指定弹窗显示的图标
//     //     shift属性： 指定弹窗的动作
//     //      */
//     //     layer.msg("获取数据失败，请联系管理员！",{time:2000, icon:2, shift:6});
//     //     return;
//     // }
//     // 请求没有问题
//
// }